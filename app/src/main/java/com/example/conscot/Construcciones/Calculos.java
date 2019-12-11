package com.example.conscot.Construcciones;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conscot.Construcciones.lista_materiales.materiales_adapter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conscot.Construcciones.lista_materiales.materiales_para_construrir;
import com.example.conscot.R;

import java.util.ArrayList;

import static com.example.conscot.Construcciones.construccion.Construccion;


public class Calculos extends Fragment {
    RecyclerView lista_materiales;
    public static ArrayList<materiales_para_construrir> lista_calculos;
    EditText largo,ancho,altura;
    TextView regresar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_calculos, container, false);
        lista_materiales=v.findViewById(R.id.Lista_materiales);
        largo = v.findViewById(R.id.Largo);
        ancho = v.findViewById(R.id.Ancho);
        altura = v.findViewById(R.id.Altura);
        lista_materiales.setLayoutManager(new LinearLayoutManager(v.getContext()));
        final Button consultar= v.findViewById(R.id.bt_consultar);
        Button calcular = v.findViewById(R.id.bt_calcular);
        regresar= v.findViewById(R.id.Regresar_a_construccion);
        regresar.setOnClickListener(new View.OnClickListener() {
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
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(largo.getText().toString().trim().length() == 0 || largo.getText().toString().equals("0")){
                    Toast.makeText(getContext(), "Llene todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(altura.getText().toString().trim().length() == 0 || altura.getText().toString().equals("0")){
                    Toast.makeText(getContext(), "Llene todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                if((ancho.getText().toString().trim().length() == 0 || ancho.getText().toString().equals("0")) && !Construccion.equals("Pared")){
                    Toast.makeText(getContext(), "Llene todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                lista_materiales.setAdapter(new materiales_adapter(Calcular_materiales()));
                consultar.setEnabled(true);
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
    private ArrayList<materiales_para_construrir> Calcular_materiales(){
        ArrayList<materiales_para_construrir>lista= new ArrayList<>();
        double cantidad,cantidad_c,cantidad_a,cantidad_g;
            switch (Construccion) {
                case "Piso":
                    //sacar los datos de las cantidades
                    /*
                    Cemento: 35 kg
                    Arena:=0.0515m3. Peso aprox 72 kg
                    Grava= 0.09 m3. Peso aprox= 160 kg
                    Agua: 15 Litros
                    */
                    cantidad = (Double.parseDouble(largo.getText().toString()) *
                            Double.parseDouble(ancho.getText().toString()));
                    cantidad_c = cantidad * 35;
                    cantidad_g = (cantidad * (Double.parseDouble(altura.getText().toString()))) * 0.0515;
                    cantidad_a = (cantidad * (Double.parseDouble(altura.getText().toString()))) * 0.09;
                    lista.add(new materiales_para_construrir("Cemento", "" + String.format("%.2g%n", cantidad_c)));
                    lista.add(new materiales_para_construrir("Arena", "" + String.format("%.2g%n", cantidad_g)));
                    lista.add(new materiales_para_construrir("Grava", "" + String.format("%.2g%n", cantidad_a)));
                    lista.add(new materiales_para_construrir("Agua", "" + String.format("%.2g%n", cantidad * 35)));
                    break;
                case "Losa":
                    ancho.setEnabled(false);
                    cantidad = (Double.parseDouble(largo.getText().toString()) *
                            Double.parseDouble(ancho.getText().toString()));
                    cantidad_c = cantidad * 325;
                    cantidad_g = (cantidad * Double.parseDouble(altura.getText().toString())) * 0.50;
                    cantidad_a = (cantidad * Double.parseDouble(altura.getText().toString())) * 0.70;
                    double cantidad_v = (cantidad * Double.parseDouble(altura.getText().toString())) * 1;
                    /*
                    Cemento: 325 kg
                    Arena:=0.5 m3.
                    Grava= 0.7 m3.
                    Varilla 1 x m3
                    Agua: 2000 Litros
                    */
                    lista.add(new materiales_para_construrir("Cemento", "" + String.format("%.2g%n", cantidad_c)));
                    lista.add(new materiales_para_construrir("Arena", "" + String.format("%.2g%n", cantidad_g)));
                    lista.add(new materiales_para_construrir("Grava", "" + String.format("%.2g%n", cantidad_a)));
                    lista.add(new materiales_para_construrir("Varilla", "" + String.format("%.2g%n", cantidad_v)));
                    lista.add(new materiales_para_construrir("Agua", "" + String.format("%.2g%n", cantidad * 200)));
                    break;
                case "Pared":
                    ancho.setText("0.40");
                    ancho.setEnabled(false);
                    cantidad = (Double.parseDouble(largo.getText().toString()) *
                            Double.parseDouble(altura.getText().toString()));
                    cantidad_c = cantidad * 9;
                    cantidad_g = cantidad * 0.023;
                    cantidad_a = cantidad * 0.032;
                    /*
                    Cemento: 9 kg
                    Arena:=0.032 m2.
                    Grava= 0.0.23 m2.
                    Agua: 8 Litros

                    */
                    lista.add(new materiales_para_construrir("Cemento", "" + String.format("%.2g%n", cantidad_c)));
                    lista.add(new materiales_para_construrir("Arena", "" + String.format("%.2g%n", cantidad_g)));
                    lista.add(new materiales_para_construrir("Grava", "" + String.format("%.2g%n", cantidad_a)));
                    lista.add(new materiales_para_construrir("Agua", "" + String.format("%.2g%n", cantidad * 9)));

                    break;
            }
            lista=lista;
        return lista;
    }
}
