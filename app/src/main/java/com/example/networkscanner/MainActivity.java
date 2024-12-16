package com.example.networkscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView ipUser;
    EditText ipscanner;
    Button  buscar;
    ListView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ipUser = findViewById(R.id.ipUser);
        ipscanner = findViewById(R.id.ipscanner);
        buscar = findViewById(R.id.buscar);
        lista = findViewById(R.id.resultado);

        ipUser.setText(" "+getIpAddress());
        buscar.setOnClickListener(this);

    }

    private String getIpAddress() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ip = wifiInfo.getIpAddress(); // Convertir la dirección IP a formato legible

        // Convertimos la dirección IP a formato legible
        @SuppressLint("DefaultLocale") String ipAddress = String.format("%d.%d.%d.%d",
                (ip & 0xff),
                (ip >> 8 & 0xff),
                (ip >> 16 & 0xff),
                (ip >> 24 & 0xff));
        return ipAddress;
    }

    ArrayList<String> listaHost = new ArrayList<>();

    @Override
    public void onClick(View v) {
        IPUtils scanner = new IPUtils();

        scanner.scanNetwork("192.168.1", new IPUtils.ScanCallback(){
            @Override
            public void onHostFound(String host) {
                runOnUiThread(() -> {
                    listaHost.add(host);
                });
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaHost);
        lista.setAdapter(adapter);


    }
}