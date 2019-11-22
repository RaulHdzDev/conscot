package com.example.conscot.ui.Constructora;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conscot.R;

public class Corizacion_fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cotizacion, container, false);
        RecyclerView  recyclerView = v.findViewById(R.id.productos_cotizados);
        recyclerView.setAdapter(new productos_cotizados_adapter(productos_adapter.productos_seleccionados));
        return v;
    }
}

