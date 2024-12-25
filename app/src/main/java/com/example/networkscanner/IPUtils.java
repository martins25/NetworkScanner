package com.example.networkscanner;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.example.networkscanner.entidades.Host;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//ESTA CLASE SOLO ESTA PREPARADA PARA DIRECCIONES IPV4 Y CON MASCARA 255.255.255.0
public class IPUtils {
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    public ArrayList<Host> scanNetwork(String subnet, ScanCallback callback) {

        ArrayList<Host> listaHost = new ArrayList<Host>();

        //Quitamos de la ip la parte que corresponde a host
        int lastDotIndex = subnet.lastIndexOf(".");
        subnet = subnet.substring(0, lastDotIndex);

        CountDownLatch latch = new CountDownLatch(254);
        for (int i = 1; i < 255; i++) {
            final String host = subnet + "." + i;
            executorService.submit(() -> {
                try {
                    InetAddress inetAddress = InetAddress.getByName(host);
                    if (inetAddress.isReachable(1000)) {
                        //callback.onHostFound(host, getMacAddress(host));
                        listaHost.add(new Host(host));
                        System.out.println("\n\n\n\nENTRAMOS"+ host +"\n\n\n\n");

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            });
        }
        new Thread(() -> {
            try {
                latch.await();
                new Handler(Looper.getMainLooper()).post(callback::onScanComplete);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        return listaHost;
    }

    public interface ScanCallback {
        void onScanComplete();
    }

    public String getMacAddress(String ipAddress) {
        try {
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(inetAddress);
            byte[] macBytes = networkInterface.getHardwareAddress();
            if (macBytes == null) {
                return null;
            }
            StringBuilder macAddress = new StringBuilder();
            for (byte b : macBytes) {
                macAddress.append(String.format("%02X:", b));
            }
            if (macAddress.length() > 0) {
                macAddress.deleteCharAt(macAddress.length() - 1);
            }
            return macAddress.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
