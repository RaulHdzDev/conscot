package com.example.conscot.Construcciones;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.conscot.R;

public class construccion extends Fragment {
    FrameLayout piso, loza, pared;
    static String Construccion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_construccion, container, false);
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
                Construccion="Loza";
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
