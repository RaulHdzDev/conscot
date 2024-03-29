package com.example.conscot.Construcciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.conscot.Construcciones.lista_materiales.materiales_para_construrir;
import com.example.conscot.Fragments_constructoras.Construrama;
import com.example.conscot.Fragments_constructoras.Cotizacion;
import com.example.conscot.Fragments_constructoras.Dimarsa;
import com.example.conscot.Fragments_constructoras.Martinez;
import com.example.conscot.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
public class materiales_construccion extends AppCompatActivity {
    private TabLayout tab;
    private String titles[]={"Construrama","Martinez","Dimarsa","Cotización"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materiales_construccion);
        ViewPager viewPager = findViewById(R.id.ViewPager);
        LoadViewPager(viewPager);

        tab = findViewById(R.id.Tab);
        tab.setupWithViewPager(viewPager);
        TabTitles();

    }
    private void LoadViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Construrama());
        adapter.addFragment(new Martinez());
        adapter.addFragment(new Dimarsa());
        adapter.addFragment(new Cotizacion());
        viewPager.setAdapter(adapter);

    }
    private void TabTitles() {
        for (int i = 0; i<4; i++) {
            tab.getTabAt(i).setText(titles[i]);

        }

    }
}
