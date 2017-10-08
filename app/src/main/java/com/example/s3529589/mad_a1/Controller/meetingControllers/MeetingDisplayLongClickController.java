package com.example.s3529589.mad_a1.Controller.meetingControllers;

import android.view.View;

import com.example.s3529589.mad_a1.CustomDialogs.EditMeetingAlertDialog;
import com.example.s3529589.mad_a1.Adapter.MeetingArrayAdapter;

import java.util.UUID;


public class MeetingDisplayLongClickController implements View.OnLongClickListener {
    private UUID id;
    private MeetingArrayAdapter meetingArrayAdapter;

    public MeetingDisplayLongClickController(UUID id, MeetingArrayAdapter meetingArrayAdapter) {
        this.id = id;
        this.meetingArrayAdapter = meetingArrayAdapter;
    }

    @Override
    public boolean onLongClick(View v) {
        EditMeetingAlertDialog editMeetingDialog = new EditMeetingAlertDialog(this.meetingArrayAdapter, this.id);
        editMeetingDialog.show();

        return true;
    }
}
