package com.example.s3529589.mad_a1.Activity.friendActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.s3529589.mad_a1.Model.CustomFriendDetailsArrayAdapter;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.R;

public class DisplayFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_friends);
        createListView();
    }

    private void createListView(){
        ListView lv = (ListView) findViewById(R.id.list_view);
        // show when the list is empty
        lv.setEmptyView(findViewById(R.id.list_view_empty));
        lv.setAdapter(new CustomFriendDetailsArrayAdapter(this, DataSingleton.getInstance().getFriendList()));
    }

    @Override
    public void onBackPressed()
    {
        Intent it = new Intent(DisplayFriendActivity.this, FriendMenuActivity.class);
        startActivity(it);

        //finish DisplayFriendActivity
        finish();
    }

}
