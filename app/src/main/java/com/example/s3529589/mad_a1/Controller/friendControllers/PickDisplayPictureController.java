package com.example.s3529589.mad_a1.Controller.friendControllers;

import android.view.View;

import com.example.s3529589.mad_a1.Model.CustomFriendDetailsArrayAdapter;

public class PickDisplayPictureController implements View.OnClickListener {
    private CustomFriendDetailsArrayAdapter caa;

    public PickDisplayPictureController(CustomFriendDetailsArrayAdapter caa) {
        this.caa = caa;
    }

    @Override
    public void onClick(View v) {
        System.out.println("ZZZZZ PICK PICTURe");
    }
}
