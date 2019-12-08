package com.example.conscot.tareas_components;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conscot.R;

import java.util.ArrayList;





public class tareas_adapter extends RecyclerView.Adapter<tareas_adapter.TareasViewHolder>{

    ArrayList<tareas_datos_rv> listaTarea;

    public tareas_adapter(ArrayList<tareas_datos_rv> listaPersonaje) {
        this.listaTarea=listaPersonaje;
    }

    @Override
    public TareasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarea,null,false);
        return new TareasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TareasViewHolder holder, int position) {
        holder.txtNombre.setText(listaTarea.get(position).getNom());
        holder.txtInformacion.setText(listaTarea.get(position).getDesc());
        holder.txtTipo.setText(listaTarea.get(position).getTipo());
        holder.txtFecha.setText(listaTarea.get(position).getFecha());
    }



    @Override
    public int getItemCount() {
        return listaTarea.size();
    }

    public class TareasViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre,txtInformacion,txtTipo, txtFecha;

        public RelativeLayout del;

        public TareasViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre= (TextView) itemView.findViewById(R.id.txt_nom);
            txtInformacion= (TextView) itemView.findViewById(R.id.txt_desc);
            txtTipo= (TextView) itemView.findViewById(R.id.txt_tipo);
            txtFecha = (TextView) itemView.findViewById(R.id.txt_fecha);
            del = (RelativeLayout) itemView.findViewById(R.id.eliminar);
        }
    }

    /*ELIMINAR*/
    public void remover(int position){

        listaTarea.remove(position);
        notifyItemRemoved(position);
    }

}
