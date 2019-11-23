package com.example.conscot.ui.Constructora;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conscot.R;

import java.util.ArrayList;

public class Corizacion_fragment extends Fragment {
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cotizacion, container, false);
        final RecyclerView  recyclerView = v.findViewById(R.id.productos_cotizados);
        Button Cotizar= v.findViewById(R.id.Cotizar);

        TextView regresar = v.findViewById(R.id.Regresar_a_productos);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                productos_fragment productos = new productos_fragment();
                fragmentTransaction.replace(R.id.container_home, productos);
                fragmentTransaction.commit();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final TextView total = v.findViewById(R.id.total_cotizacion);
        recyclerView.setAdapter(new productos_cotizados_adapter(productos_fragment.productos_seleccionados));

        Cotizar.setOnClickListener(new View.OnClickListener() {
            double total_cot=0;
            @Override
            public void onClick(View v) {

                for (Double total:productos_cotizados_adapter.Lista_precio) {
                    total_cot+=total;
                }
                total.setText("$"+String.valueOf(total_cot));
            }
        });

        Button Limpiar= v.findViewById(R.id.Limpiar);
        Limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productos_fragment.productos_seleccionados.clear();
                recyclerView.setAdapter(new productos_cotizados_adapter(productos_fragment.productos_seleccionados));
                productos_cotizados_adapter.Lista_precio.clear();
                total.setText("$0.0");
            }
        });
        //aqui el mismo problema quiero traer la lista que se genera en el productos_adapter que es donde se esta añadiendo cada producto seleccionado

        return v;
    }
}
