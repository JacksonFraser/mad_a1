package com.example.s3529589.mad_a1.Model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.s3529589.mad_a1.Controller.AddFriendController;
import com.example.s3529589.mad_a1.R;

public class FriendMenuActivity extends Activity{
    private final int F_REQUEST = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_menu);

        View addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new AddFriendController(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == F_REQUEST) {
            if (resultCode == RESULT_OK) {
                System.out.println(data.getExtras());
                System.out.println("JKHDSKJFHDKSJFHKJDHFKJHFKJHSKJHFKDJSFHKJSDHFKJHFS");

            }
        }

    }


}

