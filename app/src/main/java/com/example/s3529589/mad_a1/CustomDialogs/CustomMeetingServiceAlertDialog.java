package com.example.s3529589.mad_a1.CustomDialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.WindowManager;

/**
 * Created by supriya on 7/10/17.
 */

public class CustomMeetingServiceAlertDialog extends AlertDialog.Builder {


    public CustomMeetingServiceAlertDialog(Context context) {
        super(context);

        final String[] holdOptions = {
                "Edit", "Delete"
        };

        setTitle("Choose an option");

        setItems(holdOptions, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                       // editMeeting();
                        break;
                    case 1:
                      //  removeMeeting();
                        break;
                }
            }
        });
        create();
     //  super.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        show();

    }
}
