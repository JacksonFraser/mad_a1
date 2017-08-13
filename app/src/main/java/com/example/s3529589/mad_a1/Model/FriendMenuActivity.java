package com.example.s3529589.mad_a1.Model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.s3529589.mad_a1.Controller.AddFriendController;
import com.example.s3529589.mad_a1.R;

public class FriendMenuActivity extends Activity{
    private DatePickerActivity datePickerActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_menu);

        View addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new AddFriendController(this));
        this.datePickerActivity = new DatePickerActivity();
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


                } catch (ContactDataManager.ContactQueryException e) {
                }
            }
        }

        Intent it = new Intent(this, DatePickerActivity.class);
        this.startActivity(it);
    }
}

