package com.example.conscot.ui.Constructora;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conscot.R;
import com.example.conscot.tareas_components.tareas_datos_rv;

import java.util.ArrayList;


public class productos_adapter extends RecyclerView.Adapter<productos_adapter.TareasViewHolder>{

    ArrayList<descripcio_productos> lista_productos;

    public productos_adapter(ArrayList<descripcio_productos> listaProductos) {
        this.lista_productos=listaProductos;
    }

    @Override
    public TareasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto,null,false);
        return new TareasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TareasViewHolder holder, int position) {
        holder.Descripcion.setText(lista_productos.get(position).getCaracteristicas());
        holder.precio.setText(lista_productos.get(position).getPrecio());

    }



    @Override
    public int getItemCount() {
        return lista_productos.size();
    }

    public class TareasViewHolder extends RecyclerView.ViewHolder {
        TextView Descripcion,precio;

        public TareasViewHolder(@NonNull View itemView) {
            super(itemView);
            Descripcion=itemView.findViewById(R.id.descripcion);
            precio=itemView.findViewById(R.id.precio);
        }



    }
}
