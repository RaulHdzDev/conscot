package com.example.conscot.ui.Constructora;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conscot.R;

import java.util.ArrayList;


public class productos_cotizados_adapter extends RecyclerView.Adapter<productos_cotizados_adapter.TareasViewHolder> {
    ArrayList<descripcio_productos_dialog> lista_productos;

    public productos_cotizados_adapter(ArrayList<descripcio_productos_dialog> listaProductos) {
        this.lista_productos = listaProductos;
    }

    @Override
    public TareasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto_cotizado, null, false);
        return new TareasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TareasViewHolder holder, final int position) {
        holder.Descripcion.setText(lista_productos.get(position).getCaracteristicas());
        holder.precio.setText("$"+Integer.parseInt(lista_productos.get(position).getPrecio())*
                lista_productos.get(position).getCantidad());
        holder.Constructora.setText(productos_fragment.Constructora_seleccionada);
    }


    @Override
    public int getItemCount() {
        return lista_productos.size();
    }

    public class TareasViewHolder extends RecyclerView.ViewHolder {
        TextView Descripcion, precio,Constructora;

        public TareasViewHolder(@NonNull View itemView) {
            super(itemView);
            Descripcion = itemView.findViewById(R.id.descripcion_c);
            precio = itemView.findViewById(R.id.precio_c);
            Constructora=itemView.findViewById(R.id.constructora_c);

        }


    }
}
