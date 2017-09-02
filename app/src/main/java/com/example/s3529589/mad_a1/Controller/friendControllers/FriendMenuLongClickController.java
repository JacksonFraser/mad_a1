package com.example.s3529589.mad_a1.Controller.friendControllers;

import android.view.View;

import com.example.s3529589.mad_a1.Model.CustomEditFriendDetailsAlertDialog;
import com.example.s3529589.mad_a1.Model.CustomFriendDetailsArrayAdapter;

public class FriendMenuLongClickController implements View.OnLongClickListener {
    private int id;
    private CustomFriendDetailsArrayAdapter customFriendDetailsArrayAdapter;

    public FriendMenuLongClickController(int id, CustomFriendDetailsArrayAdapter customFriendDetailsArrayAdapter) {
        this.id = id;
        this.customFriendDetailsArrayAdapter = customFriendDetailsArrayAdapter;
    }

    @Override
    public boolean onLongClick(View v) {
        CustomEditFriendDetailsAlertDialog editFriendDialog =  new CustomEditFriendDetailsAlertDialog(this.customFriendDetailsArrayAdapter, this.id);
        editFriendDialog.show();

        return true;
    }
}
