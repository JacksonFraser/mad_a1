package com.example.s3529589.mad_a1.Model;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.s3529589.mad_a1.Controller.AddFriendController;
import com.example.s3529589.mad_a1.Controller.DisplayFriendListController;
import com.example.s3529589.mad_a1.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String LOG_TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View addFriendBtn = findViewById(R.id.addFriends);
        addFriendBtn.setOnClickListener(new AddFriendController(this));

        View displayriendListBtn = findViewById(R.id.displayFriends);
        displayriendListBtn.setOnClickListener(new DisplayFriendListController(this));
    }




}






