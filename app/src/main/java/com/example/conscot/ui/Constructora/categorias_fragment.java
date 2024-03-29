package com.example.conscot.ui.Constructora;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.conscot.R;
import com.example.conscot.ui.tools.ToolsFragment;

public class categorias_fragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cons_categories, container, false);
        Button cementos= root.findViewById(R.id.btn_cemento);
        Button aceros = root.findViewById(R.id.btAceros);
        Button Ferreteria = root.findViewById(R.id.Ferreteria_bt);
        TextView regresar=root.findViewById(R.id.Regresar_a_constructoras);
        Button otros_productos = root.findViewById(R.id.btOtros);
        //Regresa a constructora
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                ToolsFragment Constructoras = new ToolsFragment();
                fragmentTransaction.replace(R.id.container_home, Constructoras);
                fragmentTransaction.commit();
            }
        });
        //Click otros productos
        otros_productos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cambio el valor de la variable estática a la categoría seleccionada
                productos_fragment.categoriaSeleccionada = "Otros materiales";

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                productos_fragment productos_fragment = new productos_fragment();
                fragmentTransaction.replace(R.id.container_home, productos_fragment);
                fragmentTransaction.commit();
            }
        });
        //Click Ferreteria
        Ferreteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cambio el valor de la variable estática a la categoría seleccionada
                productos_fragment.categoriaSeleccionada = "Ferreteria";

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                productos_fragment productos_fragment = new productos_fragment();
                fragmentTransaction.replace(R.id.container_home, productos_fragment);
                fragmentTransaction.commit();
            }
        });
        //click en la categorias aceros
        aceros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Cambio el valor de la variable estática a la categoría seleccionada
                productos_fragment.categoriaSeleccionada = "Aceros";
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                productos_fragment productos_fragment = new productos_fragment();
                fragmentTransaction.replace(R.id.container_home, productos_fragment);
                fragmentTransaction.commit();
            }
        });
        //click en la categoria cementos
        cementos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aqui antes de que se cambie el fragment mandar que categoria se presiono

                //Cambio el valor de la variable estática a la categoría seleccionada
                productos_fragment.categoriaSeleccionada = "Cemento";

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                productos_fragment productos_fragment = new productos_fragment();
                fragmentTransaction.replace(R.id.container_home, productos_fragment);
                fragmentTransaction.commit();
            }
        });
        return root;
    }
}
