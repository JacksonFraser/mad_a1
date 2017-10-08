package com.example.s3529589.mad_a1.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.s3529589.mad_a1.CustomDialogs.MeetingServiceAlertDialog;


public class CreateMeetingPromptActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        MeetingServiceAlertDialog meetingServiceAlertDialog = new MeetingServiceAlertDialog(this);
        meetingServiceAlertDialog.show();
    }

}
