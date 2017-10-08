package com.example.s3529589.mad_a1.Activity.meetingActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.s3529589.mad_a1.Adapter.MeetingArrayAdapter;
import com.example.s3529589.mad_a1.Database.MeetingTable;
import com.example.s3529589.mad_a1.Model.MeetingDateCompare;
import com.example.s3529589.mad_a1.R;

import java.util.Collections;

public class DisplayMeetingActivity extends AppCompatActivity {

    private MeetingTable meetingTable;
    private MeetingArrayAdapter meetingArrayAdapter;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.display_meetings);
        meetingTable = new MeetingTable();
        createListView();
    }

    private void createListView() {
        //Sort the meetings based in their start times
        Collections.sort(meetingTable.getAllMeetings(), new MeetingDateCompare());

        ListView lv = (ListView) findViewById(R.id.meeting_list_view);
        lv.setEmptyView(findViewById(R.id.meeting_list_view_empty));

        meetingArrayAdapter = new MeetingArrayAdapter(this,meetingTable.getAllMeetings());
        lv.setAdapter(meetingArrayAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent it = new Intent(DisplayMeetingActivity.this, MeetingMenuActivity.class);
        startActivity(it);

        finish();
    }
}
