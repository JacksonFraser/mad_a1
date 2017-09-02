package com.example.s3529589.mad_a1.Controller.meetingControllers;

import android.view.View;

import com.example.s3529589.mad_a1.Model.CustomEditMeetingDetailsAlertDialog;
import com.example.s3529589.mad_a1.Model.CustomMeetingDetailsArrayAdapter;

/**
 * Created by supriya on 2/09/17.
 */

public class MeetingDisplayLongClickController implements View.OnLongClickListener {
    private int id;
    private CustomMeetingDetailsArrayAdapter customMeetingDetailsArrayAdapter;

    public MeetingDisplayLongClickController(int id, CustomMeetingDetailsArrayAdapter customMeetingDetailsArrayAdapter) {
    }

    @Override
    public boolean onLongClick(View v) {
        CustomEditMeetingDetailsAlertDialog editMeetingDialog = new CustomEditMeetingDetailsAlertDialog(this.customMeetingDetailsArrayAdapter, this.id);


        return true;
    }
}
