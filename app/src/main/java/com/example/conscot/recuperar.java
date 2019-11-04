package com.example.conscot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.conscot.ui.Conexion;
import com.example.conscot.ui.Recuperacion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;
import javax.mail.Message;

public class recuperar extends AppCompatActivity {

    String correor="arkteam.help@gmail.com", contrasena="A19r73K93", conR;//declarar correo y contraseña
    Button Recuperar;

    EditText email;
    String correo ;
    private List<Recuperacion> Usuarios = new ArrayList<>();

    Connection conexion;
    Boolean bol;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);
        email = findViewById(R.id.txt_email);

        Recuperar = (Button) findViewById(R.id.btn_recu);
        Conexion o= new Conexion();
        conexion= o.conexion();
        Recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodo();

            }
        });

    }

    public void metodo(){

        new Conexion().conexion();
        conexion = new Conexion().conexion();
        ejecutarConsulta(conexion);
        bol = false;
        boolean bol2=false;
        correo= email.getText().toString();
        for(int i = 0; i < Usuarios.size(); i++) {
            if(correo.equals(Usuarios.get(i).getCorreo())){
                conR = Usuarios.get(i).getContraseña();
                MandarM();
                bol2=true;
                break;
            }
        }
        if (bol2 ==false){
            Toast toast = Toast.makeText(getApplicationContext(),"Correo no existente", Toast.LENGTH_SHORT);
            toast.show();
        }
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

    public void recuperar(){

        if (correo.equals("")){
            Toast.makeText(this, "Ingrese el correo", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!verificarCorreo(correo)){
            Toast.makeText(this, "Formato de correo inválido", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void ejecutarConsulta(Connection con) {
        try {

            String SQL = "SELECT Correo, Contraseña FROM Usuarios;";

            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(SQL);


            while (rs.next()) {

                Usuarios.add(new Recuperacion(rs.getString("Correo"), rs.getString("Contraseña") ));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    /*public class SendMail extends AsyncTask<Void,Void,Void> {

        //Declaring Variables
        private Session session;

        //Progressdialog to show while sending email
        private ProgressDialog progressDialog;

        //Class Constructor
        public SendMail(){
            //Initializing variables

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Showing progress dialog while sending email

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //Dismissing the progress dialog
            progressDialog.dismiss();
            //Showing a success message

        }

        @Override
        protected Void doInBackground(Void... params) {
            //Creating properties
            Properties props = new Properties();

            //Configuring properties for gmail
            //If you are not using gmail you may need to change the values
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            //Creating a new session
            session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        //Authenticating the password
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(correor, contrasena);
                        }
                    });

            try {
                //Creating MimeMessage object
                MimeMessage mm = new MimeMessage(session);

                //Setting sender address
                mm.setFrom(new InternetAddress(correor));
                //Adding receiver
                mm.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
                //Adding subject
                mm.setSubject("RECUPERACION DE CONTRASEÑA");
                //Adding message
                mm.setText("Favor de no responder a este mensaje\nLamentamtos el incoveniente \n" +
                        "Su contraseña es: \""+conR+"\"");

                //Sending email
                Transport.send(mm);

                Toast toast = Toast.makeText(getApplicationContext(),"Un Correo ha sido enviado a su e-mail con su Contraseña", Toast.LENGTH_SHORT);
                toast.show();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }*/

    public void MandarM(){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.googlemail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");


    /*    properties.put("mail.smtp.host", "smtp.googlemail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");*/


        try{

            Session mailSession = Session.getDefaultInstance(properties, new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(correor, contrasena);
                }

            });


            /*session = Session.getDefaultInstance(properties, new Authenticator()  {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(correor,contrasena);
                }
            });*/
            /*session= javax.mail.Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(correor,contraseña);
                }
            });*/
            if (mailSession!=null){
                MimeMessage message = new MimeMessage(mailSession);
                message.setFrom(new InternetAddress(correor));
                message.setSubject("RECUPERACION DE CONTRASEÑA");
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
                message.setText("Favor de no responder a este mensaje\nLamentamtos el incoveniente \n" +
                        "Su contraseña es: \""+conR+"\"");
                Transport.send(message);
                Toast toast = Toast.makeText(getApplicationContext(),"Un Correo ha sido enviado a su e-mail con su Contraseña", Toast.LENGTH_SHORT);
                toast.show();
            }
        }catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(),"No se pudo enviar", Toast.LENGTH_SHORT);
            toast.show();
            e.printStackTrace();
        }

    }




}
