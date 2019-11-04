package com.example.conscot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.conscot.ui.Conexion;
import com.example.conscot.ui.Usuarios;

import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Se crea una instancia del botón de registro para poder usarlo
        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        //Se le asigna un evento de clic para hacer el registro
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro();
            }
        });

    }

    private void registro() {

        //Se crea una referencia de los componentes de layout en la clase java
        EditText etNombre = findViewById(R.id.etNombreReg);
        EditText etApellido = findViewById(R.id.etApellidoReg);
        EditText etUsuario = findViewById(R.id.etUsuarioReg);
        EditText etCorreo = findViewById(R.id.etCorreoReg);
        EditText etTelefono = findViewById(R.id.etTelefonoReg);
        EditText etContrasena = findViewById(R.id.etContrasenaReg);
        EditText etContrasena2 = findViewById(R.id.etContrasena2Reg);

        //Se obtienen todos los datos de los componentes
        String nombreStr = etNombre.getText().toString();
        String apellidoStr = etApellido.getText().toString();
        String usuarioStr = etUsuario.getText().toString();
        String correoStr = etCorreo.getText().toString();
        String telefonoStr = etTelefono.getText().toString();
        String contrasenaStr = etContrasena.getText().toString();
        String contrasena2Str = etContrasena2.getText().toString();

        //Valida que ningún campo esté vacío
        if (nombreStr.equals("")){
            Toast.makeText(this, "Ingrese el nombre", Toast.LENGTH_SHORT).show();
            return;
        }
        if (apellidoStr.equals("")){
            Toast.makeText(this, "Ingrese el apellido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (usuarioStr.equals("")){
            Toast.makeText(this, "Ingrese el usuario", Toast.LENGTH_SHORT).show();
            return;
        }
        if (correoStr.equals("")){
            Toast.makeText(this, "Ingrese el correo", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!verificarCorreo(correoStr)){
            Toast.makeText(this, "Formato de correo inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (telefonoStr.equals("")){
            Toast.makeText(this, "Ingrese el telefono", Toast.LENGTH_SHORT).show();
            return;
        }
        if (telefonoStr.length() != 10){
            Toast.makeText(this, "Ingrese un teléfono válido", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            long prueba = Long.parseLong(telefonoStr);
        } catch (Exception e){
            Toast.makeText(this, "Ingrese un teléfono válido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (contrasenaStr.equals("")){
            Toast.makeText(this, "Ingrese la contraseña", Toast.LENGTH_SHORT).show();
            return;
        }
        if (contrasena2Str.equals("")){
            Toast.makeText(this, "Ingrese la confirmación de la contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        //Se crea una nueva instancia de la clase "HacerEnBack" para poder user el método execute
        //que hará el registro en el background de la aplicación
        //Recibe todos los datos que se van a registrar
        new HacerEnBack(this).execute(nombreStr, apellidoStr, usuarioStr, correoStr, telefonoStr, contrasenaStr, contrasena2Str);

    }

    private boolean verificarCorreo(String correoStr) {

        if (!correoStr.contains("@") || correoStr.contains("\\|") || correoStr.contains("#")
                || correoStr.contains("¿") || correoStr.contains("?") || correoStr.contains(",")
                || correoStr.contains("¡") || correoStr.contains("!") || correoStr.contains("%")
                || correoStr.contains("$") || correoStr.contains("\"") || correoStr.contains("=")
                || correoStr.contains("(") || correoStr.contains(")") || correoStr.contains("\'")
                || correoStr.contains("\\*") || correoStr.contains("\\/") || correoStr.contains("\\+")
                || correoStr.contains("[") || correoStr.contains("]") || correoStr.contains(";")
                || correoStr.contains("{") || correoStr.contains("}") || correoStr.contains("°")
                || correoStr.contains("¬") || correoStr.contains("^") || correoStr.contains("¨")
                || correoStr.contains("´") || correoStr.contains("~") || correoStr.contains("`")
                || correoStr.contains("<") || correoStr.contains(">") || correoStr.contains(":")
                || correoStr.contains("&") || correoStr.contains("\\") || correoStr.contains(" ")
                || correoStr.contains("\n")) {
            return false;
        }

        String cadenas[] = correoStr.split("@");
        if (cadenas.length != 2){
            return false;
        }

        if (cadenas[1].contains("-") || cadenas[1].contains("_")) {
            return false;
        }

        int puntos = 0;
        for (int i = 0; i < cadenas[1].length(); i++){
            if (cadenas[1].charAt(i) == '.'){
                puntos++;
                if (i == cadenas[1].length() - 1){
                    return false;
                }
            }
        }
        if (puntos > 2 || puntos == 0){
            return false;
        }
        return true;
    }

    //Clase interna para hacer las operaciones en background y así no se vean reflejadas en el
    //sistema en sí
    public static class HacerEnBack extends AsyncTask<String, Void, String> {

        //Variables globales de la clase
        //Guarda el contexto de la aplicación para poder usar los "Toast"
        private WeakReference<Context> context;
        //Para establecer una conexión con la BD
        private Connection conexion = null;
        //Aquí se guardan todos los usuarios
        private List<Usuarios> users = new ArrayList<Usuarios>();

        //Constructor de la clase
        public HacerEnBack(Context context){
            this.context = new WeakReference<>(context);
        }

        //Método para ejecutar las operaciones en Background
        protected String doInBackground(String... params) {

            //Se asignan los parámetros a su String correspondiente
            String nombreStr = params[0];
            String apellidoStr = params[1];
            String usuarioStr = params[2];
            String correoStr = params[3];
            String telefonoStr = params[4];
            String contrasenaStr = params[5];
            String contrasena2Str = params[6];

            //Se crea una nueva conexión con la BD
            conexion = new Conexion().conexion();

            //Llama al método para obtener todos los usuarios
            ejecutarConsulta();

            //Se recorre el ArrayList de usuarios para hacer las verificaciones
            //Cada if retorna un carácter específico según el caso en el que entre
            //Estos retornos serán de utilidad en el método "onPostExecute"
            for (Usuarios user : users){
                //Verifica que no esté ocupado el usuario
                if (user.getUsuario().equals(usuarioStr)) {
                    return "A";
                }
                //Verifica que no esté ocupado el correo
                if (user.getCorreo().equals(correoStr)) {
                    return "B";
                }
                //Verifica que no esté ocupado el teléfono
                if (user.getTelefono().equals(telefonoStr)) {
                    return "C";
                }
            }
            //Verifica que las contraseñas sean iguales
            if (!contrasenaStr.equals(contrasena2Str)){
                return "D";
            }

            //Se define la consulta con los parámetros ingresados
            String consulta = "INSERT INTO Usuarios (Nombre, Apellido, Usuario, Correo, Telefono, Contraseña) values ('"+nombreStr+"', '"+apellidoStr
                    + "', '"+usuarioStr+"', '"+correoStr+"', "+Long.parseLong(telefonoStr)+", '"+contrasenaStr+"')";

            try {

                //En esta parte se ejecuta la consulta de inserción en la BD
                Statement st = conexion.createStatement();

                st.executeUpdate(consulta);
                st.close();
                conexion.close();

                return "E";

            } catch (SQLException e) {
                return null;
            }

        }

        //Este método obtiene todos los registros de la tabla usuarios para verificar
        public void ejecutarConsulta() {
            try {
                String arreglo[];
                String linea;
                String SQL = "SELECT Usuario, Correo, Telefono FROM Usuarios;";

                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                while (rs.next()) {
                    //Se guardan instancias de la clase Usuarios en el ArrayList
                    users.add(new Usuarios(rs.getString("Usuario"), rs.getString("Correo"), rs.getString("Telefono")));
                }

                rs.close();
                st.close();

            } catch (Exception e) {
            }
        }

        //Este método se ejecuta después de "doInBackground" recibe un String indicando el tipo de salida que dio el método de registro
        public void onPostExecute(String resultado){
            switch (resultado){
                case "A":
                    //El usuario está en uso
                    Toast.makeText(context.get(), "El usuario ya está registrado", Toast.LENGTH_SHORT).show();
                    break;
                case "B":
                    //El email esta en uso
                    Toast.makeText(context.get(), "El correo ya está en uso", Toast.LENGTH_SHORT).show();
                    break;
                case "C":
                    //El teléfono está en uso
                    Toast.makeText(context.get(), "El teléfono ya está en uso", Toast.LENGTH_SHORT).show();
                    break;
                case "D":
                    //Las contraseñas ingresadas no son iguales;
                    Toast.makeText(context.get(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    break;
                case "E":
                    //Registro exitoso
                    Toast.makeText(context.get(), "Usuario registrado con exito", Toast.LENGTH_SHORT).show();
                    ((Activity)context.get()).finish();
                    break;
                default:
                    //Error en el registro
                    Toast.makeText(context.get(), "Algo salió mal", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }

}
