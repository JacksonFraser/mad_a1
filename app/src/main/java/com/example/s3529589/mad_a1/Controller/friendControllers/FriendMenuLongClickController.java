package com.example.s3529589.mad_a1.Controller.friendControllers;

import android.view.View;

import com.example.s3529589.mad_a1.Model.CustomEditFriendDetailsAlertDialog;
import com.example.s3529589.mad_a1.Model.CustomArrayArrayAdapter;


public class FriendMenuLongClickController implements View.OnLongClickListener {
    private int id;
    private CustomArrayArrayAdapter customArrayArrayAdapter;

    public FriendMenuLongClickController(int id, CustomArrayArrayAdapter customArrayArrayAdapter) {
        this.id = id;
        this.customArrayArrayAdapter = customArrayArrayAdapter;
    }

    @Override
    public boolean onLongClick(View v) {
       CustomEditFriendDetailsAlertDialog a =  new CustomEditFriendDetailsAlertDialog(this.customArrayArrayAdapter, this.id);
        a.show();


        return true;
    }


}
