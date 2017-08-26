package com.example.s3529589.mad_a1.Controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;

import com.example.s3529589.mad_a1.Model.CustomArrayArrayAdapter;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;

public class FriendMenuLongClickController implements View.OnLongClickListener {
    private int id;
    private  CustomArrayArrayAdapter customArrayArrayAdapter;

    public FriendMenuLongClickController(int id, CustomArrayArrayAdapter customArrayArrayAdapter) {
        this.id = id;
        this.customArrayArrayAdapter = customArrayArrayAdapter;
    }

    @Override
    public boolean onLongClick(View v) {

        final String[] holdOptions = {
                "Edit", "Delete"
        };

        AlertDialog.Builder alert = new AlertDialog.Builder(customArrayArrayAdapter.getContext());
        alert.setTitle("Choose an option")
                .setItems(holdOptions, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if(which == 1) {
                                    removeFriend();
                                }
                            }
        });
        alert.show();

        return true;
    }

    private void removeFriend() {
        try {
            for(Friend f : DataSingleton.getInstance().getFriendList()){
                if(f.getId() ==  id) {
                    DataSingleton.getInstance().getFriendList().remove(f);
                    customArrayArrayAdapter.notifyDataSetChanged();
                }
            }

        } catch(Exception e ){

        }
    }
}
