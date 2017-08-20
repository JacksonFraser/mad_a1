package com.example.s3529589.mad_a1.Controller;

import android.content.Intent;
import android.view.View;

import com.example.s3529589.mad_a1.Activity.DisplayFriendActivity;
import com.example.s3529589.mad_a1.Activity.FriendMenuActivity;

/**
 * Created by s3529589 on 8/20/17.
 */

public class RemoveFriendMController implements View.OnClickListener {
    private FriendMenuActivity friendMenuActivity;

    public RemoveFriendMController(FriendMenuActivity friendMenuActivity){
        this.friendMenuActivity = friendMenuActivity;
    }
    @Override
    public void onClick(View v) {
        Intent it = new Intent(friendMenuActivity, DisplayFriendActivity.class);
        friendMenuActivity.startActivity(it);
        friendMenuActivity.finish();
    }
}
