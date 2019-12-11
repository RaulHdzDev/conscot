package com.example.conscot;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class mapas2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapas2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //
        LatLng construrama = new LatLng(22.732869, -98.966804);
        mMap.addMarker(new MarkerOptions().position(construrama).title("Materiales Construrama CD MANTE"));

        LatLng dimarsa = new LatLng(22.733009, -98.964430);
        mMap.addMarker(new MarkerOptions().position(dimarsa).title("Materiales Dimarsa CD MANTE"));


        LatLng martinez = new LatLng(22.7475647, -98.9985209);
        mMap.addMarker(new MarkerOptions().position(martinez).title("Materiales Martinez CD MANTE"));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(construrama, 15));
    }
}
