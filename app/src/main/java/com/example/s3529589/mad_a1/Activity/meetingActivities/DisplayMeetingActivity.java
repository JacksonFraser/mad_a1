package com.example.s3529589.mad_a1.Activity.meetingActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.s3529589.mad_a1.Model.CustomMeetingDetailsArrayAdapter;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.R;

public class DisplayMeetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.display_meetings);
        createListView();
    }
    private void createListView(){
        ListView lv = (ListView) findViewById(R.id.meeting_list_view);

        lv.setEmptyView(findViewById(R.id.meeting_list_view_empty));

        lv.setAdapter(new CustomMeetingDetailsArrayAdapter(this, DataSingleton.getInstance().getMeetingList()));
    }
}
