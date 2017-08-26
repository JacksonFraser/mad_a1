package com.example.s3529589.mad_a1.Controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.s3529589.mad_a1.Model.CustomArrayArrayAdapter;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.R;


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
                                switch(which){
                                    case 0 :
                                        editFriend();
                                        break;
                                    case 1 :
                                        removeFriend();
                                        break;
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
    public void editFriend(){
        String choices[] = {"Cancel","Confirm"};
        LayoutInflater factory = LayoutInflater.from(customArrayArrayAdapter.getContext());
        final View textEntryView = factory.inflate(R.layout.edit_friend, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(customArrayArrayAdapter.getContext());
        alert.setTitle("Choose an option");
        alert.setView(textEntryView);




        alert.setItems(choices, new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case 0 :

                        break;
                    case 1 :
                        editFriendDetails(id);
                        break;
                }
            }
        });



        alert.show();
    }
    private void editFriendDetails(int id, String name, String email){
        try {
            for(Friend f : DataSingleton.getInstance().getFriendList()){
                if(f.getId() ==  id) {
                    f.setName(name);
                    f.setEmail(email);
                    customArrayArrayAdapter.notifyDataSetChanged();
                }
            }

        } catch(Exception e ){

        }
    }
}
