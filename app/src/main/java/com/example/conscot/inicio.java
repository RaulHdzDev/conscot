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
import com.example.conscot.ui.SaveSharedPreference;
import com.example.conscot.ui.Usuarios;

import com.example.conscot.Utilities.Conexion;
import com.example.conscot.Utilities.SaveSharedPreference;
import com.example.conscot.Utilities.Usuarios;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class inicio extends AppCompatActivity {

    public String userid="";


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
                iniciarSesion();




            }
        });

        //Verifica que no haya una sesión iniciada
        if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
            Intent intent = new Intent(getApplicationContext(), Menuslide.class);
            startActivity(intent);
            finish();
        }

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
        String usuarioStr = String.valueOf(etUsuario.getText());
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

        //Verifica que el telefono tenga conexión a internet
        if (!isOnline()){
            Toast.makeText(this, "Verifique su conexión a internet", Toast.LENGTH_SHORT).show();
            return;
        }

        //Instancia de la clase para hacer el login en background
        new HacerEnBack(this).execute(usuarioStr, contrasenaStr);

    }

    //Método para verificar la conexión a internet
    public boolean isOnline() {
        //Obtiene el runtime del dispositivo
        Runtime runtime = Runtime.getRuntime();
        try {
            //Ejecuta un ping al servidor DNS de Google
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            //Guarda el valor de la espera del ping
            int exitValue = ipProcess.waitFor();
            //Retorna si obtuvo respuesta
            return (exitValue == 0);
        }
        catch (Exception e){
        }
        //Retorno en caso de aparecer una excepcion
        return false;
    }

    //Clase interna para hacer las operaciones en background y así no se vean reflejadas en el
    //sistema en sí
    public static class HacerEnBack extends AsyncTask<String, Void, String> {

        //Variables globales de la clase
        //Guarda el contexto de la aplicación para poder usar los "Toast"
        private WeakReference<Context> context;
        //Para establecer una conexión con la BD
        private Connection conexion = null;


        private String usuario = null;
        private String correo = null;


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

        public  void saveUserId( Context ctx, String idUser) {
            FileOutputStream fos;
            try {
                fos = ctx.openFileOutput("usuario.txt", Context.MODE_PRIVATE);
                fos.write(idUser.getBytes());
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
        }


        //Este método obtiene todos los registros de la tabla usuarios para verificar
        public Usuarios ejecutarConsulta(String usuario) {
            //Variable para guardar los resultados
            Usuarios user = null;
            try {

                String SQL = "SELECT id, Usuario, Contraseña FROM Usuarios WHERE Usuario = '"+usuario+"';";

                String SQL = "SELECT Usuario, Contraseña, Correo FROM Usuarios WHERE Usuario = '"+usuario+"';";

                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                while (rs.next()) {
                    //Se guarda el resultado

                    user = new Usuarios(rs.getString("Usuario"), rs.getString("Contraseña"));


                    saveUserId( context.get(), rs.getString("id"));


                    this.usuario = rs.getString("Usuario");
                    this.correo = rs.getString("Correo");

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
                    //Guarda el valor del usuario y correo del usuario loggeado
                    SaveSharedPreference.setPreferences(context.get(), true, usuario, correo);
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
