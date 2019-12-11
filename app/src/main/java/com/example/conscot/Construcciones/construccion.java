package com.example.conscot.Construcciones;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.conscot.R;
import com.example.conscot.ui.home.HomeFragment;

public class construccion extends Fragment {
    FrameLayout piso, loza, pared;
    TextView regresar;
   public static String Construccion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_construccion, container, false);
        regresar =v.findViewById(R.id.Regresar_a_home);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                HomeFragment fragment = new HomeFragment();
                fragmentTransaction.replace(R.id.container_home, fragment);
                fragmentTransaction.commit();
            }
        });
        piso= v.findViewById(R.id.botoPiso);
        piso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Construccion="Piso";
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                Calculos fragment = new Calculos();
                fragmentTransaction.replace(R.id.container_home, fragment);
                fragmentTransaction.commit();
            }
        });
        loza=v.findViewById(R.id.botonloza);
        loza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Construccion="Losa";
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                Calculos fragment = new Calculos();
                fragmentTransaction.replace(R.id.container_home, fragment);
                fragmentTransaction.commit();
            }
        });

        pared = v.findViewById(R.id.botonpared);
        pared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Construccion="Pared";
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                Calculos fragment = new Calculos();
                fragmentTransaction.replace(R.id.container_home, fragment);
                fragmentTransaction.commit();
            }
        });
        return v;
    }
}
