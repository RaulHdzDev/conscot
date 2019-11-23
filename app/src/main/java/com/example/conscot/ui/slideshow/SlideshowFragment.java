package com.example.conscot.ui.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conscot.R;
import com.example.conscot.Utilities.Conexion;
import com.example.conscot.tareas_components.tareas_adapter;
import com.example.conscot.tareas_components.tareas_datos_rv;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    public RecyclerView recyclertareas;
    public ArrayList<tareas_datos_rv> listaTareas;

    public String iduser;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);

        View  vista = inflater.inflate(R.layout.fragment_tustareas, container, false);


        listaTareas=new ArrayList<>();

        readUserId();
        llenarlista();

        //listaTareas.add(new tareas_datos_rv("jose","medellin","lala"));
        recyclertareas= (RecyclerView) vista.findViewById(R.id.recycler);
        recyclertareas.setLayoutManager(new LinearLayoutManager(getContext()));



        tareas_adapter adapter=new tareas_adapter(listaTareas);
        recyclertareas.setAdapter(adapter);


        return vista;

    }


    public void readUserId(){
        try
        {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    getContext().openFileInput("usuario.txt")));
            iduser=(fin.readLine()).trim();
            Toast.makeText(getContext(), iduser, Toast.LENGTH_SHORT).show();
            fin.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }
      
        Button guardar = root.findViewById(R.id.guardar);
        return root;

    }


    private void llenarlista() {
        try {

            String SQL = "SELECT Nombre_de_la_tarea, Tipo_de_tarea, Descripcion_de_la_tarea FROM Tareas_usuarios WHERE id_Usuario='"+iduser+"';";

            String SQL = "SELECT Nombre_de_la_tarea, Tipo_de_tarea, Descripcion_de_la_tarea FROM Tareas_usuarios;";

            Statement st = new Conexion().conexion().createStatement();
            ResultSet rs = st.executeQuery(SQL);


            while (rs.next()) {
                listaTareas.add(new tareas_datos_rv(rs.getString("Nombre_de_la_tarea"), rs.getString("Tipo_de_tarea"), rs.getString("Descripcion_de_la_tarea")));
            }
            rs.close();
            st.close();
        } catch (Exception e) {

        }

    }
}