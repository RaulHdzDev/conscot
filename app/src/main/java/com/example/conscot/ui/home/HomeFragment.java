package com.example.conscot.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.conscot.R;
import com.example.conscot.fragment_tareas;


import com.example.conscot.mapas;
import com.example.conscot.ui.slideshow.SlideshowFragment;
import com.example.conscot.ui.tools.ToolsFragment;

public class HomeFragment extends Fragment {

    ViewFlipper v_flipper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //SLIDE

        int imagenes[] = {R.drawable.slide1, R.drawable.slide22, R.drawable.slide3, R.drawable.Slide44, R.drawable.slide55};

        v_flipper = root.findViewById(R.id.v_flipper);

        for(int image:imagenes){
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

        TextView map = root.findViewById(R.id.mapa);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), mapas.class);
                startActivity(intent);
            }
        });
        return root;

    }

    public void flipperImages(int image){

        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(getContext(), android.R.anim.slide_in_left);
        v_flipper.setInAnimation(getContext(), android.R.anim.slide_out_right);
    }

}