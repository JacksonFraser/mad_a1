package com.example.s3529589.mad_a1.Activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.s3529589.mad_a1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_maps_activity);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // RMIT
        // -37.808943, 144.965117

        // Midbetween A1 and Crown
        // -37.792657500000004, 144.96047349999998

        // GET THE LONGITUDE AND LATITUDE OF THE MEETING
        // AND THEN DISPLAY IT'S LOCATION
        LatLng location = new LatLng(-37.79265752390738, 144.9604744938475);
        mMap.addMarker(new MarkerOptions().position(location).title("Meeting location"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
    }
}