package com.example.s3529589.mad_a1.CustomDialogs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s3529589.mad_a1.Adapter.MeetingArrayAdapter;
import com.example.s3529589.mad_a1.Controller.meetingControllers.MeetingSelectFriendsController;
import com.example.s3529589.mad_a1.Controller.meetingControllers.MeetingTimeEditController;
import com.example.s3529589.mad_a1.Database.MeetingTable;
import com.example.s3529589.mad_a1.Exceptions.InvalidMeetingInput;
import com.example.s3529589.mad_a1.Model.Meeting;
import com.example.s3529589.mad_a1.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class EditMeetingAlertDialog extends AlertDialog.Builder {

    private MeetingTable meetingTable = new MeetingTable();
    private MeetingArrayAdapter meetingArrayAdapter;
    private UUID id;

    public EditMeetingAlertDialog(MeetingArrayAdapter meetingArrayAdapter, UUID id) {
        super(meetingArrayAdapter.getContext());
        this.meetingArrayAdapter = meetingArrayAdapter;
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
        try {
            for (Meeting m : meetingTable.getAllMeetings()) {
                if (m.getId().equals(id)) {
                    meetingTable.deleteMeeting(m);
                    meetingArrayAdapter.updateItems(meetingTable.getAllMeetings());
                    Toast.makeText(meetingArrayAdapter.getContext(), "Meeting Removed", Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e) {

        }
    }

    private void editMeeting() {

        Meeting meetingDetailsForHints = getMeetingForPopulatingHints();
        String choices[] = {"Cancel", "Confirm"};
        LayoutInflater factory = LayoutInflater.from(meetingArrayAdapter.getContext());

        SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-yyyy, h:mm:ss a");

        final View editMeetingView = factory.inflate(R.layout.schedule_meeting, null);

        final EditText editTitle = (EditText) editMeetingView.findViewById(R.id.meetingTitle);
        editTitle.setHint(meetingDetailsForHints.getTitle());

        final TextView startTimeTV = (TextView) editMeetingView.findViewById(R.id.startTimeLbl);
        Date startAsDate = meetingDetailsForHints.getStartTime();
        String start = formatter.format(startAsDate);
        startTimeTV.setText(start);

        final TextView endTimeTV = (TextView) editMeetingView.findViewById(R.id.finishTimeLbl);
        Date finishAsDate = meetingDetailsForHints.getFinishTime();
        String finish = formatter.format(finishAsDate);
        endTimeTV.setText(finish);

        final Button editStartTime = (Button) editMeetingView.findViewById(R.id.startTimeBtn);
        final Button editEndTime = (Button) editMeetingView.findViewById(R.id.finishTimeBtn);
        final Button editFriend = (Button) editMeetingView.findViewById(R.id.selectFriendsBtn);
        editFriend.setText("edit friends");


        // Controllers to set meeting Start and Finish
        editStartTime.setOnClickListener(new MeetingTimeEditController(id, meetingArrayAdapter, startTimeTV));
        editEndTime.setOnClickListener(new MeetingTimeEditController(id, meetingArrayAdapter, endTimeTV));
        editFriend.setOnClickListener(new MeetingSelectFriendsController(id, meetingArrayAdapter));


        AlertDialog.Builder alert = new AlertDialog.Builder(meetingArrayAdapter.getContext());
        alert.setTitle("Edit details");
        alert.setView(editMeetingView);

        // Edit meeting details on 'Confirm'
        alert.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title = editTitle.getText().toString();

                try {
                    editMeetingDetails(id, title, startTimeTV, endTimeTV);
                } catch (InvalidMeetingInput invalidMeetingInput) {
                }
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

    private void editMeetingDetails(UUID id, String title, TextView startTimeTV, TextView endTimeTV) throws InvalidMeetingInput {
        DateFormat d = new SimpleDateFormat("d-MMM-yyyy, h:mm:ss a");

        for (Meeting m : meetingTable.getAllMeetings())
            if (m.getId().equals(id)) {
                // Edit meeting name
                if (!title.isEmpty()) {
                    m.setTitle(title);

                    meetingTable.updateMeeting(m);
                    meetingArrayAdapter.updateItems(meetingTable.getAllMeetings());
                }

                String startTimeString = startTimeTV.getText().toString();
                String endTimeString = endTimeTV.getText().toString();

                Date newStartDate = null;
                Date newEndDate = null;

                try {
                    newStartDate = d.parse(startTimeString);
                    newEndDate = d.parse(endTimeString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (newStartDate.before(newEndDate)) {
                    m.setStartTime(newStartDate);
                    m.setFinishTime(newEndDate);
                    meetingTable.updateMeeting(m);
                    meetingArrayAdapter.updateItems(meetingTable.getAllMeetings());
                    Toast.makeText(meetingArrayAdapter.getContext(), R.string.meeting_successfuly_edited_toast, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(meetingArrayAdapter.getContext(), "Start time must be before finish time", Toast.LENGTH_LONG).show();
                }
            }
    }

    private Meeting getMeetingForPopulatingHints() {
        for (Meeting m : meetingTable.getAllMeetings()) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }
}
