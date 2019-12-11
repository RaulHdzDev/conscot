package com.example.conscot.Fragments_constructoras;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.conscot.Construcciones.lista_materiales.materiales_para_construrir;
import com.example.conscot.R;
import com.example.conscot.Utilities.Conexion;
import com.example.conscot.ui.Constructora.productos_cotizados_adapter;

import java.sql.ResultSet;
import java.util.ArrayList;

import static com.example.conscot.Construcciones.Calculos.lista_calculos;
import static com.example.conscot.Construcciones.construccion.Construccion;

public class Construrama extends Fragment {
    RecyclerView lista_productos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_construrama, container, false);
        lista_productos = v.findViewById(R.id.productos_construrama);
        lista_productos.setLayoutManager(new LinearLayoutManager(getContext()));
        lista_productos.setAdapter(new productos_constructoras_adapter(obtenerDatos()));
        return v;
    }

    private ArrayList<productos_constructoras> obtenerDatos() {
        ArrayList<productos_constructoras> lista = new ArrayList<>();
        ResultSet rs=null;
        try {
            switch (Construccion){
                case "Piso":
                     rs = new Conexion().Consulta("Select Productos.Producto, Productos.Precio from Productos\n" +
                            "inner join tipo on Productos.id_tipo = tipo.id_tipo \n" +
                            "where  (tipo.tipo='Cemento' Or  Productos.Producto='grava 1 m3' or Productos.Producto='Arena 1 m3') and Productos.id_Constructora=1;");
                    break;
                case "Losa":
                     rs = new Conexion().Consulta("Select Productos.Producto, Productos.Precio from Productos\n" +
                             "inner join tipo on Productos.id_tipo = tipo.id_tipo \n" +
                             "where  (tipo.tipo='Cemento' Or  Productos.Producto='grava 1 m3'or Productos.id_sub_tipo=2 or Productos.Producto='arena 1 m3') and Productos.id_Constructora=1;");

                    break;
                case "Pared":
                    rs = new Conexion().Consulta("Select Productos.Producto, Productos.Precio from Productos\n" +
                            "inner join tipo on Productos.id_tipo = tipo.id_tipo \n" +
                            "where  (tipo.tipo='Cemento' Or  Productos.Producto='grava 1 m3' or Productos.Producto='Arena 1 m3') and Productos.id_Constructora=1;");
                    break;
            }
            while (rs.next()) {
                lista.add(new productos_constructoras("" + rs.getString("Producto"), "" + rs.getString("Precio"),"Construrama"));
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return lista;
    }
}
