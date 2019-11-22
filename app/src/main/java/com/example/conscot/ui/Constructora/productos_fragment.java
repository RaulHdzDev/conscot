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
    public static String Constructora_seleccionada=null;
    private Connection conexion;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cat_product, container, false);
        conexion=new Conexion().conexion();
        final RecyclerView lista_productos = root.findViewById(R.id.Lista_productos);
        lista_productos.setLayoutManager(new LinearLayoutManager(getContext()));
        //mandando datos al recycler view
        lista_productos.setAdapter(new productos_adapter(obtenerProdcutosBD()));
        lista_productos.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                lista_productos.getAdapter().getItemId(3);
                return false;
            }
        });
        return root;
    }
    //Metodo para obtener los productos de la bd
    private ArrayList<descripcio_productos> obtenerProdcutosBD(){
        ArrayList<descripcio_productos>lista= new ArrayList<>();
        try {
            //Aqui ocupo que dependiendo de que categoria se cliqueo me lo traiga para aca
            //por el momento le puse ese de cementos
            //Las categorias existentes en la bd son: Aceros, Cemento, Otros materiales. Asi como los escribi
            String SQL = "SELECT Productos.Producto,Productos.Precio from Productos\n" +
                    "inner join tipo on Productos.id_tipo=tipo.id_tipo\n" +
                    "inner join Constructoras on Productos.id_Constructora=Constructoras.id_constructora\n" +
                    "where tipo.tipo='"+categoriaSeleccionada+"' and Constructoras.Nombre_constructora='"+Constructora_seleccionada+"';";
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
