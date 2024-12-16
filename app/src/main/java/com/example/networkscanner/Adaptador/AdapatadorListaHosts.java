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
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = super.getView(position, convertView, parent);

        ipHost = view.findViewById(R.id.ipHost);
        mac = view.findViewById(R.id.mac);

        ipHost.setText(hosts.get(position).getIp());
        mac.setText(hosts.get(position).getMac());

        if(position%2!=0){
            view.setBackgroundColor(Color.rgb(37,87,163));
        }

        return view;
    }
}
