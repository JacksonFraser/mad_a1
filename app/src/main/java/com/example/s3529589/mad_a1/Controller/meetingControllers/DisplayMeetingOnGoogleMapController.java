package com.example.s3529589.mad_a1.Controller.meetingControllers;

import android.content.Intent;
import android.view.View;

import com.example.s3529589.mad_a1.Activity.meetingActivities.MeetingOnGoogleMapActivity;
import com.example.s3529589.mad_a1.Adapter.MeetingArrayAdapter;

import java.util.UUID;

public class DisplayMeetingOnGoogleMapController implements View.OnClickListener {
    private UUID id;
    private MeetingArrayAdapter meetingArrayAdapter;

    public DisplayMeetingOnGoogleMapController(UUID id, MeetingArrayAdapter meetingArrayAdapter) {
        this.id = id;
        this.meetingArrayAdapter = meetingArrayAdapter;
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(meetingArrayAdapter.getContext(), MeetingOnGoogleMapActivity.class);
        String meetingIDString = String.valueOf(this.id);
        it.putExtra("id_of_meeting", meetingIDString);
        meetingArrayAdapter.getContext().startActivity(it);
    }
}
