package com.example.s3529589.mad_a1.Model;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.s3529589.mad_a1.Controller.AddFriendController;
import com.example.s3529589.mad_a1.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View addFriendBtn = findViewById(R.id.addFriends);
        addFriendBtn.setOnClickListener(new AddFriendController(this));
    }

}






