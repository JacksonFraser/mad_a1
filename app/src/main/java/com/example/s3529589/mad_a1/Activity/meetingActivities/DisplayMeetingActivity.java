package com.example.s3529589.mad_a1.Activity.meetingActivities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.s3529589.mad_a1.Model.CustomMeetingDetailsArrayAdapter;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.R;

/**
 * Created by supriya on 2/09/17.
 */

public class DisplayMeetingActivity extends Activity {

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
