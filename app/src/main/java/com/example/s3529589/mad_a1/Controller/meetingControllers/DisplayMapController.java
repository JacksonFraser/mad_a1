package com.example.s3529589.mad_a1.Controller.meetingControllers;

import android.content.Intent;
import android.view.View;

import com.example.s3529589.mad_a1.Activity.meetingActivities.GoogleMapsActivity;
import com.example.s3529589.mad_a1.Adapter.CustomMeetingDetailsArrayAdapter;

import java.util.UUID;

public class DisplayMapController implements View.OnClickListener {
    private UUID id;
    private CustomMeetingDetailsArrayAdapter customMeetingDetailsArrayAdapter;

    public DisplayMapController(UUID id, CustomMeetingDetailsArrayAdapter customMeetingDetailsArrayAdapter) {
        this.id = id;
        this.customMeetingDetailsArrayAdapter = customMeetingDetailsArrayAdapter;
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(customMeetingDetailsArrayAdapter.getContext(), GoogleMapsActivity.class);
        customMeetingDetailsArrayAdapter.getContext().startActivity(it);
    }
}
