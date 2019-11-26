package com.example.conscot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.conscot.Utilities.Conexion;
import com.example.conscot.Utilities.SaveSharedPreference;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class fragment_tareas extends Fragment {
    EditText nombre, tipo, mensaje;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_tareas, container, false);
        nombre = root.findViewById(R.id.nombreNota);
        tipo =  root.findViewById(R.id.tipoNota);
        mensaje = root.findViewById(R.id.mensajeNota);
        Button guarda = root.findViewById(R.id.guardar);
        conexion = new Conexion().conexion();
        guarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insercion();
            }
        });

        ejecutarConsulta();
        return root;
    }
//actaulizado xdxd
    //xdddd
    //ei

    private Connection conexion = null;

    public void insercion(){
String user;
        GregorianCalendar fechaactual = new GregorianCalendar();

        int dia, mes, año;
        dia = fechaactual.get(Calendar.DAY_OF_MONTH);
        mes = fechaactual.get(Calendar.MONTH)+1;
        año = fechaactual.get(Calendar.YEAR);
        String fecha = año+"/"+mes+"/"+dia;

        Toast.makeText(this.getContext(), "fecha: "+ fecha, Toast.LENGTH_LONG).show();

        if(nombre.getText()!=null || tipo.getText() != null || mensaje.getText() != null){


user = SaveSharedPreference.getUserId(getContext());

            String consulta = "INSERT INTO Tareas_usuarios (id_Usuario, Nombre_de_la_tarea, Tipo_de_tarea, Descripcion_de_la_tarea, Fecha) values ( "+user+", '"+nombre.getText()+"', '"+tipo.getText()+"', '"+mensaje.getText()+"','"+fecha+"')";

            try {

                Statement st = conexion.createStatement();

                st.executeUpdate(consulta);
                Toast.makeText(getContext(),"Insertado", Toast.LENGTH_SHORT).show();
                st.close();
                conexion.close();



            } catch (SQLException e) {
                Toast.makeText(getContext(),"No insertado", Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext() , e.getMessage(), Toast.LENGTH_SHORT).show();

            }

        }else{
            Toast.makeText(this.getContext(), "Rellene todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
    String usersid = "";

    public void ejecutarConsulta() {
        try {

            String SQL = "SELECT id FROM Usuarios;";

            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                usersid = rs.getString("id");
            }

            rs.close();
            st.close();

        } catch (Exception e) {
        }
    }
}
