package com.example.conscot.ui.Constructora;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conscot.R;

import java.util.ArrayList;


public class productos_cotizados_adapter extends RecyclerView.Adapter<productos_cotizados_adapter.TareasViewHolder> {
    ArrayList<descripcio_productos_dialog> lista_productos;
    static ArrayList<Double> total = new ArrayList<>();
   static ArrayList<Double> Lista_precio= new ArrayList<>();
    public productos_cotizados_adapter(ArrayList<descripcio_productos_dialog> listaProductos) {
        this.lista_productos = listaProductos;
    }

    @Override
    public TareasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto_cotizado, null, false);
        return new TareasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TareasViewHolder holder, final int position) {
        holder.Descripcion.setText(lista_productos.get(position).getCaracteristicas());
        double precio=Double.parseDouble(lista_productos.get(position).getPrecio())*lista_productos.get(position).getCantidad();
        holder.precio.setText("$"+precio);
        holder.Cantidad.setText(lista_productos.get(position).getCantidad()+"");
        holder.Constructora.setText(productos_fragment.Constructora_seleccionada);
        Lista_precio.add(precio);
        total.add(precio);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                //POP up menu para eliminar productos seleccionados
                PopupMenu popupMenu= new PopupMenu(v.getContext(),v);
                MenuInflater inflater=popupMenu.getMenuInflater();
                inflater.inflate(R.menu.menu_productos,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        lista_productos.remove(position);
                        Lista_precio.remove(position);
                        total.remove(position);
                        Toast.makeText(v.getContext(),"Se elimino",Toast.LENGTH_LONG).show();
                        notifyItemRemoved(position);
                        return true;
                    }
                });
                popupMenu.show();
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return lista_productos.size();
    }

    public class TareasViewHolder extends RecyclerView.ViewHolder {
        TextView Descripcion, precio,Constructora,Cantidad;

        public TareasViewHolder(@NonNull View itemView) {
            super(itemView);
            Cantidad=itemView.findViewById(R.id.cantida_c);
            Descripcion = itemView.findViewById(R.id.descripcion_c);
            precio = itemView.findViewById(R.id.precio_c);
            Constructora=itemView.findViewById(R.id.constructora_c);

        }


    }
}
