package com.example.conscot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }


    public void registrarme(View view) {
        Intent intent = new Intent(inicio.this, registro.class);
        startActivity(intent);
    }

    public void recuperar(View view) {
        Intent intent = new Intent(inicio.this, recuperar.class);
        startActivity(intent);
    }
    public void iniciar(View view) {
        Intent intent = new Intent(inicio.this,Menuslide.class);
        startActivity(intent);
    }


}
