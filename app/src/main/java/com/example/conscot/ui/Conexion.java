package com.example.conscot.ui;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Conexion {

    public ResultSet Consulta(String query){
        ResultSet rs = null;

        try{
            Statement st = this.conexion().createStatement();

            rs = st.executeQuery(query);

        } catch (Exception sqle){
        }
        return rs;
    }

    public Connection conexion(){
        Connection conn = null;

        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();

            conn = DriverManager.getConnection("jdbc:jtds:sqlserver://sql5047.site4now.net:1433;databaseName=" +
                    "DB_A4F16C_conscot;user=DB_A4F16C_conscot_admin;" +
                    "password=Joaquin4562#;integratedSecurity=true;");

        } catch (Exception e){
        }

        return conn;
    }

}
