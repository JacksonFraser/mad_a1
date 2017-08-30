package com.example.s3529589.mad_a1.Controller.friendControllers;

import android.view.View;

import com.example.s3529589.mad_a1.Model.CustomEditFriendDetailsAlertDialog;
import com.example.s3529589.mad_a1.Model.CustomArrayAdapter;


public class FriendMenuLongClickController implements View.OnLongClickListener {
    private int id;
    private CustomArrayAdapter customArrayAdapter;

    public FriendMenuLongClickController(int id, CustomArrayAdapter customArrayAdapter) {
        this.id = id;
        this.customArrayAdapter = customArrayAdapter;
    }

    @Override
    public boolean onLongClick(View v) {
        CustomEditFriendDetailsAlertDialog editFriendDialog =  new CustomEditFriendDetailsAlertDialog(this.customArrayAdapter, this.id);
        editFriendDialog.show();


        return true;
    }


}
