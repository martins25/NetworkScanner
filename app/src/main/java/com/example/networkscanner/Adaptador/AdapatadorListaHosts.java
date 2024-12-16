package com.example.networkscanner.Adaptador;

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
    TextView ipHost, mac;



    public AdapatadorListaHosts(@NonNull Context context, ArrayList<Host> hosts) {
        super(context, R.layout.adaptador_lista_hosts, hosts);
        this.context = context;
        this.hosts = hosts;
    }

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

        // Configurar los valores de los TextView
        ipHost.setText(hosts.get(position).getIp());
        mac.setText(hosts.get(position).getMac());

        // Cambiar el color de fondo en función de la posición
        if (position % 2 != 0) {
            convertView.setBackgroundColor(Color.rgb(37, 87, 163));
        } else {
            convertView.setBackgroundColor(Color.TRANSPARENT); // Fondo predeterminado
        }

        return convertView;
    }

}
