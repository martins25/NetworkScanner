package com.example.networkscanner;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IPUtils {
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void scanNetwork(String subnet, ScanCallback callback) {
        for (int i = 1; i < 255; i++) {
            final String host = subnet + "." + i;
            executorService.submit(() -> {
                try {
                    InetAddress inetAddress = InetAddress.getByName(host);
                    if (inetAddress.isReachable(1000)) {
                        callback.onHostFound(host, getMacAddress(host));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public interface ScanCallback {
        void onHostFound(String host, String mac);
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
