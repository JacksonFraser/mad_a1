package com.example.s3529589.mad_a1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View addFriendBtn = findViewById(R.id.addFriends);

        addFriendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nextActivity(v);
            }
        });
    }

    public void nextActivity(View view){
        Intent myIntent = new Intent(this, AddFriendActivity.class);
        this.startActivityForResult(myIntent, 1);
    }
}






