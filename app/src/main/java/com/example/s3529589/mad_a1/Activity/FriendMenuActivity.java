package com.example.s3529589.mad_a1.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.s3529589.mad_a1.Controller.AddFriendController;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.R;

public class FriendMenuActivity extends Activity{
    private String name;
    private String email;
    private String birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_menu);

        View addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new AddFriendController(this));

        for(Friend f : DataSingleton.getInstance().getFriendList()){
            System.out.println(f.getName());
            System.out.println(f.getEmail());
            System.out.println(f.getBirthdate());
        }
    }
}

