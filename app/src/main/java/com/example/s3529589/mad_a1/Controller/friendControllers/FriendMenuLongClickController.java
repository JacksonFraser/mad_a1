package com.example.s3529589.mad_a1.Controller.friendControllers;

import android.view.View;

import com.example.s3529589.mad_a1.CustomDialogs.EditFriendAlertDialog;
import com.example.s3529589.mad_a1.Adapter.FriendArrayAdapter;

import java.util.UUID;

public class FriendMenuLongClickController implements View.OnLongClickListener {
    private UUID id;
    private FriendArrayAdapter friendArrayAdapter;

    public FriendMenuLongClickController(UUID id, FriendArrayAdapter friendArrayAdapter) {
        this.id = id;
        this.friendArrayAdapter = friendArrayAdapter;
    }

    @Override
    public boolean onLongClick(View v) {
        EditFriendAlertDialog editFriendDialog = new EditFriendAlertDialog(this.friendArrayAdapter, this.id);
        editFriendDialog.show();

        return true;
    }
}
