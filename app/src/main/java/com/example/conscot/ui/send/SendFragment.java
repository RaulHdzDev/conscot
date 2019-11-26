package com.example.conscot.ui.send;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.conscot.R;
import com.example.conscot.Utilities.SaveSharedPreference;
import com.example.conscot.inicio;

public class SendFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cerrarsesion, container, false);
        SaveSharedPreference.setPreferences(getContext(), false, "", "", "");
        startActivity(new Intent(getContext(), inicio.class));
        getActivity().finish();

        return root;
    }
}