package com.example.conscot.Fragments_constructoras;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conscot.R;
import com.example.conscot.ui.Constructora.descripcio_productos_dialog;
import com.example.conscot.ui.Constructora.productos_fragment;

import java.util.ArrayList;


public class productos_construccion_cotizados_adapter extends RecyclerView.Adapter<productos_construccion_cotizados_adapter.TareasViewHolder> {
    ArrayList<productos_constructoras_dialog> lista_productos;
    static Double total=0.0;
   static ArrayList<Double> Lista_precio= new ArrayList<>();
   AlertDialog.Builder build;
    public productos_construccion_cotizados_adapter(ArrayList<productos_constructoras_dialog> listaProductos) {
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
        holder.Constructora.setText(lista_productos.get(position).getConstructora());
        Lista_precio.add(precio);
        total+=precio;

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
                        //Se crea el dialogo
                       build= new AlertDialog.Builder(v.getContext());
                        View vw = View.inflate(v.getContext(), R.layout.dialogo_eliminar_cantidad_productos, null);
                        final EditText Cantidad = vw.findViewById(R.id.cantidad_d_p);
                        build.setView(vw).setTitle(lista_productos.get(position).getCaracteristicas())
                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (!Cantidad.getText().toString().equals("")) {
                                        if(Integer.parseInt(Cantidad.getText().toString())>lista_productos.get(position).getCantidad()){
                                            Toast.makeText(build.getContext(),"La cantidad es mayor",Toast.LENGTH_LONG).show();
                                            dialog.dismiss();
                                        }else{
                                            if (Integer.parseInt(Cantidad.getText().toString())==lista_productos.get(position).getCantidad()){
                                                lista_productos.remove(position);
                                                Lista_precio.remove(position);
                                                total=0.0;
                                                Toast.makeText(build.getContext(),"Se elimino el producto",Toast.LENGTH_LONG).show();
                                            }else {
                                                if (Integer.parseInt(Cantidad.getText().toString()) < lista_productos.get(position).getCantidad()) {
                                                    lista_productos.get(position).setCantidad(lista_productos.get(position).getCantidad()
                                                            - Integer.parseInt(Cantidad.getText().toString()));

                                                    double precio = lista_productos.get(position).getCantidad() - (Double.parseDouble(lista_productos.get(position).getPrecio()) * Integer.parseInt(Cantidad.getText().toString()));
                                                    holder.precio.setText(String.valueOf(precio));
                                                    total=total-precio;
                                                    Toast.makeText(build.getContext(), "Se elimino la cantidda de: " + Cantidad.getText().toString(), Toast.LENGTH_LONG).show();
                                                } else {
                                                    dialog.dismiss();
                                                }
                                            }
                                            }
                                        }else{
                                            dialog.dismiss();
                                        }

                                        notifyItemRemoved(position);
                                    }
                                })
                                .setNegativeButton("", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        build.create().show();
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
            Constructora=itemView.findViewById(R.id.constructora_);

        }


    }
}
