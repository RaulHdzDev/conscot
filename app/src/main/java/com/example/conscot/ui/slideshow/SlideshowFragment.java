package com.example.conscot.ui.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conscot.R;
import com.example.conscot.Utilities.Conexion;
import com.example.conscot.Utilities.SaveSharedPreference;
import com.example.conscot.tareas_components.RecyclerItemTouchHelper;
import com.example.conscot.tareas_components.tareas_adapter;
import com.example.conscot.tareas_components.tareas_datos_rv;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SlideshowFragment extends Fragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private SlideshowViewModel slideshowViewModel;
    public RecyclerView recyclertareas;
    public ArrayList<tareas_datos_rv> listaTareas;
    private tareas_adapter adapter;
    public String iduser;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);

        View  vista = inflater.inflate(R.layout.fragment_tustareas, container, false);


        listaTareas=new ArrayList<>();


        llenarlista();

        //listaTareas.add(new tareas_datos_rv("jose","medellin","lala"));
        recyclertareas= (RecyclerView) vista.findViewById(R.id.recycler);
        recyclertareas.setLayoutManager(new LinearLayoutManager(getContext()));



        adapter=new tareas_adapter(listaTareas);
        recyclertareas.setAdapter(adapter);


        ItemTouchHelper.SimpleCallback simpleCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.RIGHT, this);

        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclertareas);


        return vista;

    }





    private void llenarlista() {
        try {

            iduser = SaveSharedPreference.getUserId(getContext());


            String SQL = "SELECT Nombre_de_la_tarea, Tipo_de_tarea, Descripcion_de_la_tarea, fecha_creacion FROM Notas_usuarios WHERE id_Usuario='"+iduser+"';";

           // String SQL = "SELECT Nombre_de_la_tarea, Tipo_de_tarea, Descripcion_de_la_tarea FROM Tareas_usuarios;";

            Statement st = new Conexion().conexion().createStatement();
            ResultSet rs = st.executeQuery(SQL);


            while (rs.next()) {
                listaTareas.add(new tareas_datos_rv(rs.getString("Nombre_de_la_tarea"), rs.getString("Tipo_de_tarea"), rs.getString("Descripcion_de_la_tarea"), rs.getString("fecha_creacion")));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onSwipe(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof  tareas_adapter.TareasViewHolder){
            iduser = SaveSharedPreference.getUserId(getContext());
            try{

                String descaux = listaTareas.get(viewHolder.getAdapterPosition()).getDesc();

                String SQL = "DELETE  FROM Notas_usuarios WHERE id_Usuario='"+iduser+"' and Descripcion_de_la_tarea='"+descaux+"';";
                Statement st = new Conexion().conexion().createStatement();
                st.executeUpdate(SQL);
                st.close();

            }catch (Exception e){
                e.printStackTrace();
            }
            adapter.remover(viewHolder.getAdapterPosition());
        }
    }
}