package com.example.s3529589.mad_a1.Activity.friendActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.s3529589.mad_a1.Adapter.FriendArrayAdapter;
import com.example.s3529589.mad_a1.Database.FriendTable;
import com.example.s3529589.mad_a1.R;


public class DisplayFriendActivity extends AppCompatActivity {

    private FriendTable friendTable;
    private FriendArrayAdapter friendArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_friends);
        createListView();
    }

    private void createListView() {
        // load the listview based on the friends database
        friendTable = new FriendTable();

        ListView lv = (ListView) findViewById(R.id.list_view);
        // show when the list is empty
        lv.setEmptyView(findViewById(R.id.list_view_empty));

        friendArrayAdapter = new FriendArrayAdapter(this,friendTable.getAllFriends());
        lv.setAdapter(friendArrayAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent it = new Intent(DisplayFriendActivity.this, FriendMenuActivity.class);
        startActivity(it);

        //finish DisplayFriendActivity
        finish();
    }



}
