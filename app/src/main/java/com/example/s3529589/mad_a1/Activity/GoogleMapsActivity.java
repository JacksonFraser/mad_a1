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
        System.out.println("asdasdasdasd + " + meetingId);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        MeetingTable meetingTable = new MeetingTable();
        for(Meeting m : meetingTable.getAllMeetings()){
            if(m.getId().equals(meetingId)){
                // parse the id into long and lat
                String location = m.getLocation();
                String[] delims = location.split(",");

                latitude = Double.parseDouble(delims[0]);
                longitude = Double.parseDouble(delims[1]);
            }
        }

        // RMIT
        // -37.808943, 144.965117

        // Midbetween A1 and Crown
        // -37.792657500000004, 144.96047349999998

        // GET THE LONGITUDE AND LATITUDE OF THE MEETING
        // AND THEN DISPLAY IT'S LOCATION

        System.out.println("asdadasd" + this.latitude);
        System.out.println("hey got here " + this.longitude);

        LatLng location = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(location).title("Meeting location"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
    }
}
