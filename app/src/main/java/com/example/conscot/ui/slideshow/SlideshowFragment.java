package com.example.conscot.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conscot.R;
import com.example.conscot.tareas_components.tareas_adapter;
import com.example.conscot.tareas_components.tareas_datos_rv;
import com.example.conscot.ui.Usuarios;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.example.conscot.ui.*;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    public RecyclerView recyclertareas;
    public ArrayList<tareas_datos_rv> listaTareas;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);

        View root = inflater.inflate(R.layout.fragment_tustareas, container, false);

        listaTareas=new ArrayList<>();

        llenarlista();
        recyclertareas= (RecyclerView) root.findViewById(R.id.recycler);
        recyclertareas.setLayoutManager(new LinearLayoutManager(getContext()));



        tareas_adapter adapter=new tareas_adapter(listaTareas);
        recyclertareas.setAdapter(adapter);


        View root = inflater.inflate(R.layout.fragment_tareas, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        Button guardar = root.findViewById(R.id.guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        return root;
    }

    private void llenarlista() {
        try {

            String SQL = "SELECT Nombre_de_la_tarea, Tipo_de_tarea, Descripcion_de_la_tarea FROM Tareas_usuarios;";

            Statement st = new Conexion().conexion().createStatement();
            ResultSet rs = st.executeQuery(SQL);


            while (rs.next()) {
                listaTareas.add(new tareas_datos_rv(rs.getString("Nombre_de_la_tarea"), rs.getString("Tipo_de_tarea"), rs.getString("Descripcion_de_la_tarea") ) );
            }
            rs.close();
            st.close();
        } catch (Exception e) {

        }

    }
}