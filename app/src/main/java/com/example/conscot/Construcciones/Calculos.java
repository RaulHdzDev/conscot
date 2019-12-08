package com.example.conscot.Construcciones;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.conscot.Utilities.Conexion;

import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conscot.R;
import com.example.conscot.ui.Constructora.descripcio_productos;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static com.example.conscot.Construcciones.construccion.Construccion;


public class Calculos extends Fragment {
    RecyclerView lista_materiales;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_calculos, container, false);
        lista_materiales=v.findViewById(R.id.Lista_materiales);
        lista_materiales.setLayoutManager(new LinearLayoutManager(v.getContext()));
        lista_materiales.setAdapter(new materiales_adapter(obtenerProdcutosBD()));
        Button consultar= v.findViewById(R.id.bt_consultar);
        Button calcular = v.findViewById(R.id.bt_calcular);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),materiales_construccion.class);
                startActivity(intent);
            }
        });
        return v;
    }
    private ArrayList<descripcio_productos> obtenerProdcutosBD(){
        ArrayList<descripcio_productos>lista= new ArrayList<>();
        try {
            Statement st = new Conexion().conexion().createStatement();
            String SQL="";
            ResultSet rs=null;
            switch (Construccion){
                case "Piso":
                     SQL = "";
                    break;
                case "Loza":
                    SQL = "jh";
                    break;
                case "Pared":
                    SQL = "j";
                    break;
            }
            rs = st.executeQuery(SQL);
            while (rs.next()) {
                lista.add(new descripcio_productos(rs.getString("Precio"),rs.getString("Producto")));
            }
            //Se cierran conexiones
            rs.close();
            st.close();

        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return lista;
    }
}
