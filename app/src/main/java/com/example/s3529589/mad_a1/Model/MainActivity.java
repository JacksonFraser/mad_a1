package com.example.s3529589.mad_a1.Model;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.s3529589.mad_a1.Controller.AddFriendController;
import com.example.s3529589.mad_a1.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Friend> friendList = new ArrayList<Friend>();
    private String LOG_TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View addFriendBtn = findViewById(R.id.addFriends);
        addFriendBtn.setOnClickListener(new AddFriendController(this));
    }

    public void addToFriendList(Friend f){
        friendList.add(f);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                ContactDataManager contactsManager = new ContactDataManager(this, data);
                String name = "";
                String email = "";
                try {

                    name = contactsManager.getContactName();
                    email = contactsManager.getContactEmail();

                    friendList.add(new Friend(name,email));

                } catch (ContactDataManager.ContactQueryException e) {
                    Log.e(LOG_TAG, e.getMessage());
                }
            }
        }
    }

}






