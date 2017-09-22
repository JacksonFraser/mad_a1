package com.example.s3529589.mad_a1.Activity.friendActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.s3529589.mad_a1.Adapter.CustomFriendDetailsArrayAdapter;
import com.example.s3529589.mad_a1.Database.FriendDatabaseHandler;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.R;

import java.util.List;

public class DisplayFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_friends);
        createListView();
    }

    private void createListView() {
        /* load the listview based on the friends list singleton
        *

        ListView lv = (ListView) findViewById(R.id.list_view);
        // show when the list is empty
        lv.setEmptyView(findViewById(R.id.list_view_empty));
        lv.setAdapter(new CustomFriendDetailsArrayAdapter(this, DataSingleton.getInstance().getFriendList()));

        *
        */

        // load the listview based on the friends database
        FriendDatabaseHandler db = new FriendDatabaseHandler(this);
        List<Friend> friendsInDatabase = db.getAllFriends();

        ListView lv = (ListView) findViewById(R.id.list_view);
        // show when the list is empty
        lv.setEmptyView(findViewById(R.id.list_view_empty));
        lv.setAdapter(new CustomFriendDetailsArrayAdapter(this, friendsInDatabase));
    }

    @Override
    public void onBackPressed() {
        Intent it = new Intent(DisplayFriendActivity.this, FriendMenuActivity.class);
        startActivity(it);

        //finish DisplayFriendActivity
        finish();
    }

}
