package com.example.s3529589.mad_a1.Model;

import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by supriya on 2/09/17.
 */

public class CustomEditMeetingDetailsAlertDialog extends AlertDialog.Builder{
    private CustomMeetingDetailsArrayAdapter customMeetingDetailsArrayAdapter;
    private int id;

    public CustomEditMeetingDetailsAlertDialog(CustomMeetingDetailsArrayAdapter customMeetingDetailsArrayAdapter, int id) {
        super(customMeetingDetailsArrayAdapter.getContext());
        this.customMeetingDetailsArrayAdapter = customMeetingDetailsArrayAdapter;
        this.id = id;

        final String[] holdOptions = {
                "Edit", "Delete"
        };

        setTitle("Choose an option");

        setItems(holdOptions, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        editMeeting();
                        break;
                    case 1:
                        removeMeeting();
                        break;
                }
            }
        });


    }

    private void removeMeeting() {
    }
    private void editMeeting(){

    }
}