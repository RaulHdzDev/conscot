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

import com.example.conscot.Construcciones.lista_materiales.materiales_para_construrir;
import com.example.conscot.R;
import com.example.conscot.ui.Constructora.descripcio_productos_dialog;
import com.example.conscot.ui.Constructora.productos_fragment;

import java.util.ArrayList;


public class productos_constructoras_adapter extends RecyclerView.Adapter<productos_constructoras_adapter.TareasViewHolder> {
    ArrayList<productos_constructoras> lista_productos;

    public productos_constructoras_adapter(ArrayList<productos_constructoras> listaProductos) {
        this.lista_productos = listaProductos;
    }

    @Override
    public TareasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto_constructora, null, false);
        return new TareasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TareasViewHolder holder, final int position) {
        holder.material.setText(lista_productos.get(position).getMaterial());
        holder.precio.setText("$"+lista_productos.get(position).getPrecio());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog.Builder build = new AlertDialog.Builder(v.getContext());
                //Se infla el layout que se usa para darle diseño al dialog
                View vw = View.inflate(v.getContext(), R.layout.dialogo_cantidad_productos, null);
                final EditText Cantidad = vw.findViewById(R.id.Cantidad_dialog);
                //se le dan las caracteristicas y los botones de aceptar o cancelar
                build.setView(vw).setTitle(lista_productos.get(position).getMaterial())
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (!Cantidad.getText().toString().equals("")) {
                                    Cotizacion.productos_seleccionados.add(new productos_constructoras_dialog(lista_productos.get(position).getPrecio()
                                            ,lista_productos.get(position).getMaterial(), Integer.parseInt(Cantidad.getText().toString()),lista_productos.get(position).getConstructoras()));
                                    Toast.makeText(build.getContext(), "Se añadio", Toast.LENGTH_LONG
                                    ).show();
                                }else{
                                    dialog.dismiss();
                                }
                            }
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                build.create().show();
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return lista_productos.size();
    }

    public class TareasViewHolder extends RecyclerView.ViewHolder {
        TextView material, precio;

        public TareasViewHolder(@NonNull View itemView) {
            super(itemView);
            material = itemView.findViewById(R.id.material_p);
            precio = itemView.findViewById(R.id.precio_p);
        }


    }
}
