package com.example.networkscanner.Adaptador;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.networkscanner.R;
import com.example.networkscanner.entidades.Host;

import java.util.ArrayList;

public class AdapatadorListaHosts extends ArrayAdapter<Host> {

    Context context;
    ArrayList<Host> hosts;
    TextView ipHost, mac, so, fabricante;



    public AdapatadorListaHosts(@NonNull Context context, ArrayList<Host> hosts) {
        super(context, R.layout.adaptador_lista_hosts, hosts);
        this.context = context;
        this.hosts = hosts;
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Reutilizar la vista existente si no es nula
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.adaptador_lista_hosts, parent, false);
        }

        // Obtener referencias a los TextView del diseño
        ipHost = convertView.findViewById(R.id.ipHost);
        mac = convertView.findViewById(R.id.mac);
        so = convertView.findViewById(R.id.so);
        fabricante = convertView.findViewById(R.id.fabricante);

        // Configurar los valores de los TextView
        ipHost.setText(hosts.get(position).getIp());
        mac.setText(hosts.get(position).getMac());
        so.setText(hosts.get(position).getOperatingSystem());
        fabricante.setText(hosts.get(position).getFabricante());

        // Cambiar el color de fondo en función de la posición
        if (position % 2 != 0) {
            convertView.setBackgroundColor(R.color.azulBrillante);
        } else {
            convertView.setBackgroundColor(R.color.azulBrillante2); // Fondo predeterminado
        }

        return convertView;
    }

}
