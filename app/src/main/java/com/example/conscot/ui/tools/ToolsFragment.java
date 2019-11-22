package com.example.conscot.ui.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.conscot.R;
import com.example.conscot.ui.Constructora.categorias_fragment;
import com.example.conscot.ui.Constructora.productos_fragment;
import com.example.conscot.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_constructoras, container, false);
        FrameLayout construrama = root.findViewById(R.id.construrama_bt);
        FrameLayout martinez = root.findViewById(R.id.Martinez_bt);
        FrameLayout la_brocha = root.findViewById(R.id.Brocha_bt);
        FrameLayout dimarsa = root.findViewById(R.id.Dimarsa_bt);
        FloatingActionButton mapa = root.findViewById(R.id.Mapa);
        //Regresar a home
        TextView regresar = root.findViewById(R.id.Regresar_a_home);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                HomeFragment Home = new HomeFragment();
                fragmentTransaction.replace(R.id.container_home, Home);
                fragmentTransaction.commit();
            }
        });

        //Click a martinez_constructora
        construrama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productos_fragment.Constructora_seleccionada="Construrama";
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                categorias_fragment categorias = new categorias_fragment();
                fragmentTransaction.replace(R.id.container_home, categorias);
                fragmentTransaction.commit();
            }
        });
        //Click a martinez_constructora
        la_brocha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Constructora en mantenimiento",Toast.LENGTH_LONG).show();
               /* productos_fragment.Constructora_seleccionada="Construrama";
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                categorias_fragment categorias = new categorias_fragment();
                fragmentTransaction.replace(R.id.container_home, categorias);
                fragmentTransaction.commit();*/
            }
        });
        //Click a la_brocha_constructora
        martinez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Constructora en mantenimiento",Toast.LENGTH_LONG).show();
               /* productos_fragment.Constructora_seleccionada="Construrama";
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                categorias_fragment categorias = new categorias_fragment();
                fragmentTransaction.replace(R.id.container_home, categorias);
                fragmentTransaction.commit();*/
            }
        });
        //Click en dimarsa
        dimarsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Constructora en mantenimiento",Toast.LENGTH_LONG).show();
               /* productos_fragment.Constructora_seleccionada="Construrama";
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                categorias_fragment categorias = new categorias_fragment();
                fragmentTransaction.replace(R.id.container_home, categorias);
                fragmentTransaction.commit();*/
            }
        });
        return root;
    }
}