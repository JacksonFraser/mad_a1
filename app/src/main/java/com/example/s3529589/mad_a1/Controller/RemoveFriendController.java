package com.example.s3529589.mad_a1.Controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;

import com.example.s3529589.mad_a1.Model.CustomArrayArrayAdapter;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;

/**
 * Created by s3529589 on 8/20/17.
 */

public class RemoveFriendController implements View.OnClickListener {
    private int id;
    private  CustomArrayArrayAdapter customArrayArrayAdapter;
    public RemoveFriendController(int id, CustomArrayArrayAdapter customArrayArrayAdapter) {

        this.id = id;
        this.customArrayArrayAdapter = customArrayArrayAdapter;
    }



   /* @Override
    public void onClick(DialogInterface dialog, int which) {
        switch(which){
            case DialogInterface.BUTTON_POSITIVE:
                removeFriend();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                break;
        }

    }*/


    @Override
    public void onClick(View v) {
        AlertDialog.Builder alert = new AlertDialog.Builder(customArrayArrayAdapter.getContext());
        alert.setMessage("Delete friend?");
        alert.setNegativeButton("NO", null);
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                removeFriend();
            }
        });
        alert.show();

    }
    private void removeFriend(){
        try{
            for(Friend f : DataSingleton.getInstance().getFriendList()){
                if(f.getId() ==  id){
                    DataSingleton.getInstance().getFriendList().remove(f);
                    customArrayArrayAdapter.notifyDataSetChanged();
                }
            }

        }catch(Exception e ){

        }
    }
}
