package com.example.s3529589.mad_a1.CustomDialogs;

import android.app.Activity;
import android.os.Bundle;

import com.example.s3529589.mad_a1.R;


public class TestDialogActivity extends Activity{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomMeetingServiceAlertDialog alertDialog = new CustomMeetingServiceAlertDialog(this);
        alertDialog.setTitle("your title");
        alertDialog.setMessage("your message");
        alertDialog.show();
    }
}
