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
    private String name;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_menu);

        View addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new AddFriendController(this));

        for(Friend f : DateSingleton.getInstance().getFriendList()){
            System.out.println(f.getName());
            System.out.println(f.getEmail());
            System.out.println(f.getBirthdate());
        }
    }
}

