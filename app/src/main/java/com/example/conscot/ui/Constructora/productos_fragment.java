package com.example.conscot.ui.Constructora;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conscot.R;
import com.example.conscot.Utilities.Conexion;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class productos_fragment extends Fragment {

    //Variable estática en la que se va a guardar el valor de la categoría que se seleccionó en el
    //fragment anterior
    productos_adapter adapter;
    static ArrayList<descripcio_productos_dialog> productos_seleccionados=new ArrayList<>();
    public static String categoriaSeleccionada = null;
    public static String Constructora_seleccionada=null;
    static FloatingActionButton Conscot;
    private Connection conexion;
    RecyclerView lista_productos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cat_product, container, false);
        conexion=new Conexion().conexion();

        lista_productos = root.findViewById(R.id.Lista_productos);
        //Regresa a categorias
        TextView regresar=root.findViewById(R.id.Regresar_a_categorias);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                categorias_fragment categorias = new categorias_fragment();
                fragmentTransaction.replace(R.id.container_home, categorias);
                fragmentTransaction.commit();
            }
        });
       Conscot = root.findViewById(R.id.Cotizar);
       Conscot.setEnabled(false);
        Conscot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                Corizacion_fragment cotizacion = new Corizacion_fragment();
                fragmentTransaction.replace(R.id.container_home, cotizacion);
                fragmentTransaction.commit();
            }
        });
        lista_productos.setLayoutManager(new LinearLayoutManager(getContext()));
        //mandando datos al recycler view
        adapter = new productos_adapter(obtenerProdcutosBD());
        lista_productos.setAdapter(adapter);
        EditText buscar = root.findViewById(R.id.Buscar);
        buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filtrar(s.toString());
            }
        });
        return root;
    }

    private void filtrar(String filter){
        ArrayList<descripcio_productos> todos = obtenerProdcutosBD();
        ArrayList<descripcio_productos> filtrados = new ArrayList<descripcio_productos>();

        for(descripcio_productos item : todos){
            if (item.getCaracteristicas().toLowerCase().startsWith(filter.toLowerCase()))
                filtrados.add(item);
        }
        adapter.filterList(filtrados);

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
