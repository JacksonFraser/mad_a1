package com.example.s3529589.mad_a1.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.s3529589.mad_a1.CustomDialogs.CustomMeetingServiceAlertDialog;


public class JobSchedulerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        CustomMeetingServiceAlertDialog customMeetingServiceAlertDialog = new CustomMeetingServiceAlertDialog(this);
        customMeetingServiceAlertDialog.show();
    }

}
