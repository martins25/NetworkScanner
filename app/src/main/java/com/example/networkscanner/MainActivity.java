package com.example.networkscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
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

        //Sobreescribimos el metodo CharSquence para permitir lo que nosotros queramos en el editText
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                // Permitir solo números y puntos
                for (int i = start; i < end; i++) {
                    if (!Character.isDigit(source.charAt(i)) && source.charAt(i) != '.') {
                        return "";
                    }
                }
                return null;
            }
        };

        // Aplicamos el filtro al EditText
        ipscanner.setFilters(new InputFilter[]{filter});

    }


    //Metodo para extraer nuestra direccion IP
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

    //Metodo para comprobar si es una direccion valida o no
    public boolean isValidIPAddress(String ip) {
        String regex = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        return ip.matches(regex);
    }


    ArrayList<String> listaHost = new ArrayList<>();

    @Override
    public void onClick(View v) {

        //Spiteamos la direccion
        String[] direccion = ipscanner.getText().toString().split("\\.");

        //Creamos el dialogo de progreso que este congela la aplicacion mientras escanea la red
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Escaneando la red...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        if (isValidIPAddress(ipscanner.getText().toString())&&direccion.length==4){

            //Creamos el rango de ip para pasaserlo al escaner
            String ip = direccion[0]+"."+direccion[1]+"."+direccion[2];
            ipscanner.setBackgroundColor(0);

            IPUtils scanner = new IPUtils();
            scanner.scanNetwork(ip, new IPUtils.ScanCallback(){
                //Implementamos este metodo de la interfaz entoces todos los host que vaya encontrando los va mostrando en la lista
                @Override
                public void onHostFound(String host) {
                    runOnUiThread(() -> {
                        listaHost.add(host);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listaHost);
                        lista.setAdapter(adapter);
                    });
                }

                //Este metodo que hemos implementado de la interfaz se ejecuta cuando se acaba el proceso
                @Override public void onScanComplete() {
                    runOnUiThread(() -> {
                        progressDialog.dismiss();
                    });
                }
            });

        }else{
            ipscanner.setBackgroundColor(Color.rgb(255,0,0));
        }
    }
}