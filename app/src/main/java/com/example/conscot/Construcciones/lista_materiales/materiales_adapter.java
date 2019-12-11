package com.example.conscot.Construcciones.lista_materiales;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conscot.R;

import java.util.ArrayList;


public class materiales_adapter extends RecyclerView.Adapter<materiales_adapter.TareasViewHolder> {
    ArrayList<materiales_para_construrir> lista_productos;

    public materiales_adapter(ArrayList<materiales_para_construrir> listaProductos) {
        this.lista_productos = listaProductos;
    }

    @Override
    public TareasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_material, null, false);
        return new TareasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TareasViewHolder holder, final int position) {
        holder.material.setText(lista_productos.get(position).getMaterial());
        holder.cantidad.setText(lista_productos.get(position).getCantidad());
    }


    @Override
    public int getItemCount() {
        return lista_productos.size();
    }

    public class TareasViewHolder extends RecyclerView.ViewHolder {
        TextView material, cantidad;

        public TareasViewHolder(@NonNull View itemView) {
            super(itemView);
            material = itemView.findViewById(R.id.material);
            cantidad = itemView.findViewById(R.id.cantidad);
        }


    }
}
