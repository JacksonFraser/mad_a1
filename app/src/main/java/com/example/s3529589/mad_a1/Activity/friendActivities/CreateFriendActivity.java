package com.example.s3529589.mad_a1.Activity.friendActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.s3529589.mad_a1.Controller.friendControllers.ConfirmDateController;
import com.example.s3529589.mad_a1.Database.FriendTable;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.R;

import java.util.Date;
import java.util.List;

public class CreateFriendActivity extends AppCompatActivity {
    private Button confirmBtn;
    private DatePicker datePicker;
    private String name;
    private String email;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker);
        confirmBtn = (Button) findViewById(R.id.confirmDateBtn);
        datePicker = (DatePicker) findViewById(R.id.datePicker);

        // receive intents from SelectContactActivity
        Intent incomingIntent = getIntent();
        name = incomingIntent.getStringExtra("name");
        email = incomingIntent.getStringExtra("email");

        confirmBtn.setOnClickListener(new ConfirmDateController(name, email, datePicker, this));
    }

    public void createFriend(String name, String email, Date date, double latitude, double longitude) {

        // database
        FriendTable friendTable = new FriendTable();
        friendTable.addFriend(new Friend(name, email, date, latitude, longitude));

        // Reading all contacts
        List<Friend> friends = friendTable.getAllFriends();

        for (Friend f : friends) {
            String log = "Id: "+f.getId()+", Name: " + f.getName() + ", Email: " + f.getEmail() + ", Date: " + f.getBirthdate() + ", Longitude: " +
                    f.getLon() + ", Latitude: " +  f.getLat() + " " + friends.size();
            // Writing Contacts to log
            Log.d("CREATE_FRIEND", log);
        }
    }

    @Override
    public void onBackPressed() {
        Intent it = new Intent(CreateFriendActivity.this, FriendMenuActivity.class);
        startActivity(it);

        //finish CreateFriendActivity
        finish();
    }
}

