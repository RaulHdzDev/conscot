package com.example.conscot.ui.Constructora;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conscot.R;
import com.example.conscot.tareas_components.tareas_datos_rv;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class productos_adapter extends RecyclerView.Adapter<productos_adapter.TareasViewHolder> {
    static ArrayList<descripcio_productos_dialog> productos_seleccionados= new ArrayList<>();
    ArrayList<descripcio_productos> lista_productos;

    public productos_adapter(ArrayList<descripcio_productos> listaProductos) {
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
        //Se aplica el LongClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se crea el alert dialog
                final AlertDialog.Builder build = new AlertDialog.Builder(v.getContext());
                //Se infla el layout que se usa para darle diseño al dialog
                View vw = View.inflate(v.getContext(), R.layout.dialogo_cantidad_productos, null);
                final EditText Cantidad = vw.findViewById(R.id.Cantidad_dialog);
                //se le dan las caracteristicas y los botones de aceptar o cancelar
                build.setView(vw).setTitle(lista_productos.get(position).getCaracteristicas())
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                productos_seleccionados.add(new descripcio_productos_dialog(lista_productos.get(position).getPrecio()
                                ,lista_productos.get(position).getCaracteristicas(),Integer.parseInt(Cantidad.getText().toString())));
                            }
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                build.create().show();
            }
        });


    }


    @Override
    public int getItemCount() {
        return lista_productos.size();
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
