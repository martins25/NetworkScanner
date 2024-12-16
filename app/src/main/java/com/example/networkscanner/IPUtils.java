package com.example.networkscanner;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.InetAddress;
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
                        callback.onHostFound(host);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public interface ScanCallback {
        void onHostFound(String host);
        void onScanComplete();
    }

}
