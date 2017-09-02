package com.example.s3529589.mad_a1.Model;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.s3529589.mad_a1.R;


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
        try{
            for(Meeting m : DataSingleton.getInstance().getMeetingList()){
                if(m.getId() ==  id){
                    DataSingleton.getInstance().getMeetingList().remove(m);
                    customMeetingDetailsArrayAdapter.notifyDataSetChanged();
                    Toast.makeText(customMeetingDetailsArrayAdapter.getContext(), "Meeting Removed", Toast.LENGTH_LONG).show();
                }
            }
        }catch (Exception e){

        }
    }
    private void editMeeting(){
        String choices[] = {"Cancel", "Confirm"};
        LayoutInflater factory = LayoutInflater.from(customMeetingDetailsArrayAdapter.getContext());

        final View editMeetingView = factory.inflate(R.layout.schedule_meeting, null);

        final EditText editTitle = (EditText) editMeetingView.findViewById(R.id.meetingTitle);

        final Button editStartTime = (Button) editMeetingView.findViewById(R.id.startTimeBtn);

        final Button editEndTime = (Button) editMeetingView.findViewById(R.id.finishTimeBtn);



        AlertDialog.Builder alert = new AlertDialog.Builder(customMeetingDetailsArrayAdapter.getContext());
        alert.setTitle("Edit details");
        alert.setView(editMeetingView);

        // Edit friend details on 'Confirm'
        alert.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //String name = editName.getText().toString();
                //String email = editEmail.getText().toString();
                //editFriendDetails(id, name, email);

                Toast.makeText(customMeetingDetailsArrayAdapter.getContext(), R.string.friend_updated_toast, Toast.LENGTH_LONG).show();
            }
        });

        // Close alert on 'Cancel'
        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        alert.show();
    }



}
