package com.example.conscot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.conscot.ui.Conexion;
import com.example.conscot.ui.Usuarios;

import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //Instancia del boton para agregar funcionalidad
        Button btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        //Agrega el evento de clic para ejecutar el inicio de sesion
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(view.getContext(), Menuslide.class);
                view.getContext().startActivity(intent);
                //iniciarSesion();

            }
        });

        //Verifica que no haya una sesión iniciada
        /*if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
            Intent intent = new Intent(getApplicationContext(), inicio_activity.class);
            startActivity(intent);
            finish();
        }*/

    }


    public void registrarme(View view) {
        Intent intent = new Intent(inicio.this, registro.class);
        startActivity(intent);
    }

    public void recuperar(View view) {
        Intent intent = new Intent(inicio.this, recuperar.class);
        startActivity(intent);
    }

    private void iniciarSesion(){

        //Instancia de los componentes para el Login
        EditText etUsuario = findViewById(R.id.etUsuarioIS);
        EditText etContrasena = findViewById(R.id.etContrasenaIS);

        //Valores String para hacer el login
        String usuarioStr = etUsuario.getText().toString();
        String contrasenaStr = etContrasena.getText().toString();

        //Verifica que los campos no estén vacíos
        if (usuarioStr.equals("")){
            Toast.makeText(this, "Ingrese un usuario válido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (contrasenaStr.equals("")){
            Toast.makeText(this, "Ingrese una contraseña válida", Toast.LENGTH_SHORT).show();
            return;
        }

        //Instancia de la clase para hacer el login en background
        new HacerEnBack(this).execute(usuarioStr, contrasenaStr);

    }

    //Clase interna para hacer las operaciones en background y así no se vean reflejadas en el
    //sistema en sí
    public static class HacerEnBack extends AsyncTask<String, Void, String> {

        //Variables globales de la clase
        //Guarda el contexto de la aplicación para poder usar los "Toast"
        private WeakReference<Context> context;
        //Para establecer una conexión con la BD
        private Connection conexion = null;
        //Constructor de la clase
        public HacerEnBack(Context context){
            this.context = new WeakReference<>(context);
        }
        //Método para ejecutar las operaciones en Background
        protected String doInBackground(String... params) {
            //Variable para guardar el usuario a buscar
            Usuarios user;

            //Se asignan los parámetros a su String correspondiente
            String usuarioStr = params[0];
            String contrasenaStr = params[1];

            //Se crea una nueva conexión con la BD
            conexion = new Conexion().conexion();

            //Llama al método para ver si el usuario ya está registrado
            //en caso de estarlo retorna el usuario para verificar el login
            //en caso contrario retorna null
            user = ejecutarConsulta(usuarioStr);

            //Verifica si el usuario existe
            if (user == null){
                return "A";
            }
            //Verifica que la contraseña sea correcta
            if (!contrasenaStr.equals(user.getContrasena())){
                return "B";
            }
            try {
                //Cierra la conexión
                conexion.close();
                return "C";
            } catch (SQLException e) {
                //Retorno en caso de error
                return null;
            }
        }

        //Este método obtiene todos los registros de la tabla usuarios para verificar
        public Usuarios ejecutarConsulta(String usuario) {
            //Variable para guardar los resultados
            Usuarios user = null;
            try {
                String SQL = "SELECT Usuario, Contraseña FROM Usuarios WHERE Usuario = '"+usuario+"';";
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                while (rs.next()) {
                    //Se guarda el resultado
                    user = new Usuarios(rs.getString("Usuario"), rs.getString("Contraseña"));
                }
                //Se cierran conexiones
                rs.close();
                st.close();
            } catch (Exception e) {
            }
            return user;
        }

        //Este método se ejecuta después de "doInBackground" recibe un String indicando el tipo de salida que dio el método de registro
        public void onPostExecute(String resultado){
            switch (resultado){
                case "A":
                    //No encontró el usuario
                    Toast.makeText(context.get(), "Usuario no registrado/incorrecto", Toast.LENGTH_SHORT).show();
                    break;
                case "B":
                    //La contraseña está mal
                    Toast.makeText(context.get(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    break;
                case "C":
                    //Establece que se ha iniciado una sesión y debe mantenerla abierta
                    /*SaveSharedPreference.setLoggedIn(context.get(), true);*/
                    //Login exitoso -> pasa a la pantalla de inicio y cierra la actual
                    Intent  intent = new Intent(context.get(), Menuslide.class);
                    context.get().startActivity(intent);
                    //Cierra esta pantalla
                    ((Activity)context.get()).finish();
                    break;
                default:
                    //Error en el login
                    Toast.makeText(context.get(), "Algo salió mal", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }

}
