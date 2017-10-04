package com.example.s3529589.mad_a1.Controller.meetingControllers;

import android.view.View;

import com.example.s3529589.mad_a1.Adapter.CustomEditMeetingDetailsAlertDialog;
import com.example.s3529589.mad_a1.Adapter.CustomMeetingDetailsArrayAdapter;

import java.util.UUID;


public class MeetingDisplayLongClickController implements View.OnLongClickListener {
    private UUID id;
    private CustomMeetingDetailsArrayAdapter customMeetingDetailsArrayAdapter;

    public MeetingDisplayLongClickController(UUID id, CustomMeetingDetailsArrayAdapter customMeetingDetailsArrayAdapter) {
        this.id = id;
        this.customMeetingDetailsArrayAdapter = customMeetingDetailsArrayAdapter;
    }

    @Override
    public boolean onLongClick(View v) {
        CustomEditMeetingDetailsAlertDialog editMeetingDialog = new CustomEditMeetingDetailsAlertDialog(this.customMeetingDetailsArrayAdapter, this.id);
        editMeetingDialog.show();

        return true;
    }
}
