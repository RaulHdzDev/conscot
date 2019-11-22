package com.example.conscot.tareas_components;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conscot.R;

import java.util.ArrayList;

public class tustareasFragment extends Fragment {

    public RecyclerView recyclertareas;
    public ArrayList<tareas_datos_rv> listaTareas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  vista = inflater.inflate(R.layout.fragment_tustareas, container, false);


        listaTareas=new ArrayList<>();
        listaTareas.add(new tareas_datos_rv("jose","medellin","lala"));
        recyclertareas= (RecyclerView) vista.findViewById(R.id.recycler);
        recyclertareas.setLayoutManager(new LinearLayoutManager(getContext()));

        tareas_adapter adapter=new tareas_adapter(listaTareas);
        recyclertareas.setAdapter(adapter);

        return vista;
    }

    private void llenarLista() {

    }


}
