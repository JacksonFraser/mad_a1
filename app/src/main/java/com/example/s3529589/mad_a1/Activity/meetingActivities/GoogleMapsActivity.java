package com.example.s3529589.mad_a1.Activity.meetingActivities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.s3529589.mad_a1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_activity);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        LatLng RMIT = new LatLng(-37.808943, 144.965117);
        mMap.addMarker(new MarkerOptions().position(RMIT).title("RMIT"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(RMIT));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));
    }
}