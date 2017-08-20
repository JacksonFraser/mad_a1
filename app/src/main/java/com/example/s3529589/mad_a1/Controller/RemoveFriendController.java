package com.example.s3529589.mad_a1.Controller;

import android.view.View;

import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;

/**
 * Created by s3529589 on 8/20/17.
 */

public class RemoveFriendController implements View.OnClickListener {
    private String name;

    public RemoveFriendController(String name) {
        this.name = name;
    }

    @Override
    public void onClick(View v) {
        System.out.println("got clicked");
        for(Friend f : DataSingleton.getInstance().getFriendList()){
            if(f.getName().equals(name)){
                DataSingleton.getInstance().getFriendList().remove(f);
            }
        }
    }
}
