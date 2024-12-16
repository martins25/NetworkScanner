package com.example.networkscanner.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.networkscanner.R;
import com.example.networkscanner.entidades.Host;

import java.util.ArrayList;

public class AdapatadorListaHosts extends ArrayAdapter {

    Context context;
    ArrayList<Host> hosts;


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


        return view;
    }
}
