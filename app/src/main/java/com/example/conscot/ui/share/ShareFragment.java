package com.example.conscot.ui.share;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.conscot.R;
import com.example.conscot.acercadeDev;
import com.example.conscot.mapas;

public class ShareFragment extends Fragment {


    TextView creditos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_acercade, container, false);

        creditos = vista.findViewById(R.id.creditos);
        creditos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), acercadeDev.class);
                startActivity(i);
            }
        });


        return vista;
    }
}