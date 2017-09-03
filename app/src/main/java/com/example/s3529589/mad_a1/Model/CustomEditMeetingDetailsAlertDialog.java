package com.example.s3529589.mad_a1.Model;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s3529589.mad_a1.Controller.meetingControllers.MeetingFriendEditController;
import com.example.s3529589.mad_a1.Controller.meetingControllers.MeetingTimeEditController;
import com.example.s3529589.mad_a1.Exceptions.InvalidMeetingInput;
import com.example.s3529589.mad_a1.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

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

        SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-yyyy, h:mm aaa");

        final View editMeetingView = factory.inflate(R.layout.schedule_meeting, null);

        final EditText editTitle = (EditText) editMeetingView.findViewById(R.id.meetingTitle);
        editTitle.setHint(DataSingleton.getInstance().getMeetingById(id).getTitle());

        final TextView startTimeTV = (TextView) editMeetingView.findViewById(R.id.startTimeLbl);
        Date startAsDate = DataSingleton.getInstance().getMeetingById(id).getStartTime();
        String start = formatter.format(startAsDate);
        startTimeTV.setHint(start);

        final TextView endTimeTV = (TextView) editMeetingView.findViewById(R.id.finishTimeLbl);
        Date finishAsDate = DataSingleton.getInstance().getMeetingById(id).getStartTime();
        String finish = formatter.format(startAsDate);
        endTimeTV.setHint(finish);



        final Button editStartTime = (Button) editMeetingView.findViewById(R.id.startTimeBtn);
        final Button editEndTime = (Button) editMeetingView.findViewById(R.id.finishTimeBtn);
        final Button editFriend = (Button) editMeetingView.findViewById(R.id.selectFriendsBtn);
        editFriend.setText("edit friends");


        // Controllers to set meeting Start and Finish
        editStartTime.setOnClickListener(new MeetingTimeEditController(id, customMeetingDetailsArrayAdapter, startTimeTV));
        editEndTime.setOnClickListener(new MeetingTimeEditController(id,customMeetingDetailsArrayAdapter,endTimeTV));
        editFriend.setOnClickListener(new MeetingFriendEditController(id,customMeetingDetailsArrayAdapter));


        AlertDialog.Builder alert = new AlertDialog.Builder(customMeetingDetailsArrayAdapter.getContext());
        alert.setTitle("Edit details");
        alert.setView(editMeetingView);

        // Edit meeting details on 'Confirm'
        alert.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title = editTitle.getText().toString();

                editMeetingDetails(id, title,startTimeTV,endTimeTV);
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

    private void editMeetingDetails(int id, String title, TextView startTimeTV, TextView endTimeTV) {
        DateFormat d = new SimpleDateFormat("d-MMM-yyy, h:mm a");

        try{
            for(Meeting m : DataSingleton.getInstance().getMeetingList())
                if (m.getId() == id) {
                    // Edit meeting name
                    if (!title.isEmpty()) {
                        m.setTitle(title);
                        customMeetingDetailsArrayAdapter.notifyDataSetChanged();
                    }
                    // Edit meeting start
                    if (!startTimeTV.getText().toString().matches("")) {
                        String startTimeString = startTimeTV.getText().toString();

                        Date startDate = null;
                        startDate = d.parse(startTimeString);

                        try{
                            DataSingleton.getInstance().getMeetingById(id).setStartTime(startDate);
                            System.out.println(DataSingleton.getInstance().getMeetingById(id).getStartTime());

                            customMeetingDetailsArrayAdapter.notifyDataSetChanged();
                        }catch(InvalidMeetingInput e){
                            Toast.makeText(customMeetingDetailsArrayAdapter.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                    // Edit meeting finish
                    if (!endTimeTV.getText().toString().matches("")) {
                        String endTimeString = endTimeTV.getText().toString();

                        Date endDate = null;
                        endDate = d.parse(endTimeString);

                        try{
                            DataSingleton.getInstance().getMeetingById(id).setFinishTime(endDate);
                            System.out.println(DataSingleton.getInstance().getMeetingById(id).getFinishTime());

                            customMeetingDetailsArrayAdapter.notifyDataSetChanged();
                        }catch(InvalidMeetingInput e){
                            Toast.makeText(customMeetingDetailsArrayAdapter.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

        } catch(Exception e) {

        }

    }


}
