package com.example.conscot.ui.Constructora;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conscot.R;
import com.example.conscot.ui.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class productos_fragment extends Fragment {

    //Variable estática en la que se va a guardar el valor de la categoría que se seleccionó en el
    //fragment anterior
    public static String categoriaSeleccionada = null;

    private Connection conexion;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cat_product, container, false);
        conexion=new Conexion().conexion();
        RecyclerView lista_productos = root.findViewById(R.id.Lista_productos);
        lista_productos.setLayoutManager(new LinearLayoutManager(getContext()));
        //mandando datos al recycler view
        lista_productos.setAdapter(new productos_adapter(obtenerProdcutosBD()));

        return root;
    }
    //IGNORAR ESTO
    private  ArrayList<descripcio_productos>obtenerProductos(){
        ArrayList<descripcio_productos>lista= new ArrayList<>();
        lista.add(new descripcio_productos("300","Cemento vergas"));
        lista.add(new descripcio_productos("300","Cemento vergas"));
        lista.add(new descripcio_productos("300","Cemento vergas"));
        lista.add(new descripcio_productos("300","Cemento vergas"));
        lista.add(new descripcio_productos("300","Cemento vergas"));
        lista.add(new descripcio_productos("300","Cemento vergas"));
        lista.add(new descripcio_productos("300","Cemento vergas"));
        return lista;
    }
    //Metodo para obtener los productos de la bd
    private ArrayList<descripcio_productos> obtenerProdcutosBD(){
        ArrayList<descripcio_productos>lista= new ArrayList<>();
        try {
            //Aqui ocupo que dependiendo de que categoria se cliqueo me lo traiga para aca
            //por el momento le puse ese de cementos
            //Las categorias existentes en la bd son: Aceros, Cemento, Otros materiales. Asi como los escribi
            String categoria="Cemento";
            String SQL = "select Producto,Precio from Productos\n" +
                    "inner join tipo on Productos.id_sub_tipo=tipo.id_tipo\n" +
                    "where tipo.tipo='"+categoriaSeleccionada+"';";
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
