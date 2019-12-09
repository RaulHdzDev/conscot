package com.example.conscot.Construcciones;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conscot.R;
import com.example.conscot.ui.Constructora.descripcio_productos;
import com.example.conscot.ui.Constructora.descripcio_productos_dialog;
import com.example.conscot.ui.Constructora.productos_fragment;

import java.util.ArrayList;


public class materiales_adapter extends RecyclerView.Adapter<materiales_adapter.TareasViewHolder> {
    ArrayList<descripcio_productos> lista_productos;

    public materiales_adapter(ArrayList<descripcio_productos> listaProductos) {
        this.lista_productos = listaProductos;
    }

    @Override
    public TareasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, null, false);
        return new TareasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TareasViewHolder holder, final int position) {
        holder.Descripcion.setText(lista_productos.get(position).getCaracteristicas());
        holder.precio.setText("$"+lista_productos.get(position).getPrecio());
    }


    @Override
    public int getItemCount() {
        return lista_productos.size();
    }

    public void filterList(ArrayList<descripcio_productos> lista_filtrada) {
        lista_productos=lista_filtrada;
        notifyDataSetChanged();
    }


    public class TareasViewHolder extends RecyclerView.ViewHolder {
        TextView Descripcion, precio;

        public TareasViewHolder(@NonNull View itemView) {
            super(itemView);
            Descripcion = itemView.findViewById(R.id.descripcion);
            precio = itemView.findViewById(R.id.precio);
        }


    }
}
