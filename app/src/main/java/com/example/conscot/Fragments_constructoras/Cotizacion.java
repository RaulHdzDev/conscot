package com.example.conscot.Fragments_constructoras;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conscot.R;
import com.example.conscot.ui.Constructora.descripcio_productos_dialog;
import com.example.conscot.ui.Constructora.productos_cotizados_adapter;
import com.example.conscot.ui.Constructora.productos_fragment;

import java.util.ArrayList;

public class Cotizacion extends Fragment {
    static ArrayList<productos_constructoras_dialog> productos_seleccionados=new ArrayList<>();
    RecyclerView lista_productos;
    boolean click=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v=inflater.inflate(R.layout.fragment_cotizacion_construccion, container, false);
        lista_productos=v.findViewById(R.id.productos_cotizados_construccion);
        lista_productos.setLayoutManager(new LinearLayoutManager(getContext()));
        lista_productos.setAdapter(new productos_construccion_cotizados_adapter(Cotizacion.productos_seleccionados));
        Button cotizar = v.findViewById(R.id.Cotizar_C);
        final TextView total = v.findViewById(R.id.precio_total_C);
        Button limpiar = v.findViewById(R.id.Limpiar_C);
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cotizacion.productos_seleccionados.clear();
                lista_productos.setAdapter(new productos_construccion_cotizados_adapter(Cotizacion.productos_seleccionados));
                productos_construccion_cotizados_adapter.Lista_precio.clear();
                productos_construccion_cotizados_adapter.total = 0.0;
                productos_construccion_cotizados_adapter.Lista_precio.clear();
                click = true;
                total.setText("$0.0");
            }
        });
        cotizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!click){
                    total.setText("$"+String.valueOf(productos_construccion_cotizados_adapter.total));
                    click=false;
                }
            }
        });
        return v;
    }

}
