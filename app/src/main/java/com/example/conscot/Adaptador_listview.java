package com.example.conscot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador_listview extends BaseAdapter {
    ArrayList<descripcio_productos>lista_productos= new ArrayList<>();
    Context context;

    public Adaptador_listview(ArrayList<descripcio_productos> lista_productos, Context context) {
        this.lista_productos = lista_productos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lista_productos.size();
    }

    @Override
    public Object getItem(int position) {
        return lista_productos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        descripcio_productos item= (descripcio_productos)getItem(position);
        convertView= LayoutInflater.from(context).inflate(R.layout.item_producto,null);
        TextView descripcion=convertView.findViewById(R.id.descripcion);
        TextView precio=convertView.findViewById(R.id.precio);
        descripcion.setText(item.getCaracteristicas());
        precio.setText(item.getPrecio());
        return convertView;
    }
}
