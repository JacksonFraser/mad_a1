package com.example.s3529589.mad_a1.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.s3529589.mad_a1.Model.CustomArrayArrayAdapter;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.R;


/**
 * Created by s3529589 on 8/20/17.
 */

public class DisplayFriendActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_friends);
        createListView();
    }

    private void createListView(){
        ListView lv = (ListView) findViewById(R.id.list_view);
        lv.setAdapter(new CustomArrayArrayAdapter(this, DataSingleton.getInstance().getFriendList()));
    }
}
