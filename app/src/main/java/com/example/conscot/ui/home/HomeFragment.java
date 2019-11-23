package com.example.conscot.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.conscot.R;
import com.example.conscot.fragment_tareas;

import com.example.conscot.ui.slideshow.SlideshowFragment;
import com.example.conscot.ui.tools.ToolsFragment;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //Click para abrir fragment de tareas
        TextView Tareas = root.findViewById(R.id.tareas);
        Tareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                fragment_tareas fragment = new fragment_tareas();
                fragmentTransaction.replace(R.id.container_home, fragment);
                fragmentTransaction.commit();
            }
        });

        TextView planos= root.findViewById(R.id.planos);
        planos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment planos
            }
        });

        //se añade el fragment contenedor donde se estan pasando los demas fragments
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_home,new HomeFragment());

        //Click para abrir fragment de constructoras
        TextView constructoras = root.findViewById(R.id.constructora);
        constructoras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                ToolsFragment fragment = new ToolsFragment();
                fragmentTransaction.replace(R.id.container_home, fragment);
                fragmentTransaction.commit();
            }
        });
        return root;
    }
}