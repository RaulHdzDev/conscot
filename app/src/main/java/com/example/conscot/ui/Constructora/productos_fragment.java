package com.example.conscot.ui.Constructora;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.conscot.Adaptador_listview;
import com.example.conscot.R;
import com.example.conscot.descripcio_productos;
import com.example.conscot.ui.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class productos_fragment extends Fragment {
    static class categorias{
        static String categorias;


        public static String getCategorias() {
            return categorias;
        }

        public static void setCategorias(String cat) {
            categorias = cat;
        }
    }
    private Connection conexion;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cons_categories, container, false);
        conexion=new Conexion().conexion();
        ListView lista_productos = root.findViewById(R.id.Lista_productos);
        lista_productos.setAdapter(new Adaptador_listview(obtenerProdcutosBD(),root.getContext()));

        return root;
    }
    private ArrayList<descripcio_productos> obtenerProdcutosBD(){
        ArrayList<descripcio_productos>lista= new ArrayList<>();
        try {
            String categoria= categorias.getCategorias();
            String SQL = "select Producto,Precio from Productos\n" +
                    "inner join tipo on Productos.id_sub_tipo=tipo.id_tipo\n" +
                    "where tipo.tipo='"+categoria+"';";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(SQL);
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
