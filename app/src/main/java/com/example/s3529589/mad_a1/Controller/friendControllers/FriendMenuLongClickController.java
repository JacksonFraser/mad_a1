package com.example.s3529589.mad_a1.Controller.friendControllers;

import android.view.View;

import com.example.s3529589.mad_a1.Model.CustomEditFriendDetailsAlertDialog;
import com.example.s3529589.mad_a1.Model.CustomFriendDetailsArrayAdapter;

import java.util.UUID;

public class FriendMenuLongClickController implements View.OnLongClickListener {
    private UUID id;
    private CustomFriendDetailsArrayAdapter customFriendDetailsArrayAdapter;

    public FriendMenuLongClickController(UUID id, CustomFriendDetailsArrayAdapter customFriendDetailsArrayAdapter) {
        this.id = id;
        this.customFriendDetailsArrayAdapter = customFriendDetailsArrayAdapter;
    }

    @Override
    public boolean onLongClick(View v) {
        CustomEditFriendDetailsAlertDialog editFriendDialog = new CustomEditFriendDetailsAlertDialog(this.customFriendDetailsArrayAdapter, this.id);
        editFriendDialog.show();

        return true;
    }
}
