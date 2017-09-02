package com.example.s3529589.mad_a1.Activity.meetingActivities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

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
        ListView lv = (ListView) findViewById(R.id.list_view);

       // lv.setE
    }
}
