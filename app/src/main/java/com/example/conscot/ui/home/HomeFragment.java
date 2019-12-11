package com.example.conscot.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.conscot.Construcciones.construccion;
import com.example.conscot.R;
import com.example.conscot.fragment_tareas;


import com.example.conscot.mapas2;
import com.example.conscot.ui.slideshow.SlideshowFragment;
import com.example.conscot.ui.tools.ToolsFragment;

public class HomeFragment extends Fragment {
    ViewFlipper v_flipper;
    TextView map;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        int imagenes[] = {R.drawable.slide1, R.drawable.slide22, R.drawable.slide3, R.drawable.slide44, R.drawable.slide55};

        v_flipper = root.findViewById(R.id.v_flipper);
        map = root.findViewById(R.id.Mapa);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), mapas2.class);
                startActivity(i);
            }
        });
        for (int image : imagenes) {
            flipperImages(image);
        }

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

        //se añade el fragment contenedor donde se estan pasando los demas fragments
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_home, new HomeFragment());

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
        //Click para abrir las construcciones
        TextView construye = root.findViewById(R.id.construye);
        construye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                construccion fragment = new construccion();
                fragmentTransaction.replace(R.id.container_home, fragment);
                fragmentTransaction.commit();
            }
        });
        return root;
    }

    public void flipperImages(int image) {

        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(3000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(getContext(), android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(getContext(), android.R.anim.slide_out_right);
    }
}
