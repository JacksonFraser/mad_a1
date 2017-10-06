package com.example.s3529589.mad_a1.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.s3529589.mad_a1.Database.MeetingTable;
import com.example.s3529589.mad_a1.Model.Meeting;
import com.example.s3529589.mad_a1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String meetingId;

    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_maps_activity);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // receive intents from DisplayMapActivity
        Intent incomingIntent = getIntent();
        meetingId = incomingIntent.getStringExtra("id_of_meeting");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        MeetingTable meetingTable = new MeetingTable();

        for(Meeting m : meetingTable.getAllMeetings()){
            if(m.getId().toString().equals(meetingId)){
                // parse string into latitude and longitude
                String location = m.getLocation();
                String[] delims = location.split(",");

                latitude = Double.parseDouble(delims[0]);
                longitude = Double.parseDouble(delims[1]);
            }
        }

        LatLng location = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(location).title("Meeting location"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
    }
}
