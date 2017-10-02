package com.example.s3529589.mad_a1.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.s3529589.mad_a1.Controller.friendControllers.EditBirthdayController;
import com.example.s3529589.mad_a1.Database.FriendDatabaseHandler;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.Model.Meeting;
import com.example.s3529589.mad_a1.R;

import java.util.UUID;

public class CustomEditFriendDetailsAlertDialog extends AlertDialog.Builder {
    FriendDatabaseHandler db = new FriendDatabaseHandler(this.getContext());

    private CustomFriendDetailsArrayAdapter customFriendDetailsArrayAdapter;
    private UUID id;

    public CustomEditFriendDetailsAlertDialog(CustomFriendDetailsArrayAdapter customFriendDetailsArrayAdapter, UUID id) {
        super(customFriendDetailsArrayAdapter.getContext());
        this.customFriendDetailsArrayAdapter = customFriendDetailsArrayAdapter;
        this.id = id;

        final String[] holdOptions = {
                "Edit", "Delete"
        };

        setTitle("Choose an option");

        setItems(holdOptions, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        editFriend();
                        break;
                    case 1:
                        removeFriend();
                        break;
                }
            }
        });
    }


    private void removeFriend() {


        try {
            for (Friend f : db.getAllFriends()) {
                if (f.getId().equals(id)) {
                    db.deleteFriend(f);
                    customFriendDetailsArrayAdapter.updateItems(db.getAllFriends());
                    //if the friend exists in any of the meeting, remove them.
                    //
                    // NOT IMPLEMENTED YET
                    //
                    // removeFriendFromMeeting(friend);
                    Toast.makeText(customFriendDetailsArrayAdapter.getContext(), R.string.friend_removed_toast, Toast.LENGTH_LONG).show();
                }
            }

        } catch (Exception e) {
        }
    }


    // When the user selects Edit after long hold
    public void editFriend() {
        Friend friendDetailsForHint = getFriendForPopulatingHints();
        String choices[] = {"Cancel", "Confirm"};
        LayoutInflater factory = LayoutInflater.from(customFriendDetailsArrayAdapter.getContext());

        final View textEntryView = factory.inflate(R.layout.edit_friend, null);

        final EditText editName = (EditText) textEntryView.findViewById(R.id.edit_friend_name);
        editName.setHint(friendDetailsForHint.getName());

        final EditText editEmail = (EditText) textEntryView.findViewById(R.id.edit_friend_email);
        editEmail.setHint(friendDetailsForHint.getEmail());

        final Button editBtn = (Button) textEntryView.findViewById(R.id.selectDate);

        // Select birthday
        editBtn.setOnClickListener(new EditBirthdayController(customFriendDetailsArrayAdapter.getContext(), id));

        AlertDialog.Builder alert = new AlertDialog.Builder(customFriendDetailsArrayAdapter.getContext());
        alert.setTitle("Edit details");
        alert.setView(textEntryView);

        // Edit friend details on 'Confirm'
        alert.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String name = editName.getText().toString();
                String email = editEmail.getText().toString();
                editFriendDetails(id, name, email);

                Toast.makeText(customFriendDetailsArrayAdapter.getContext(), R.string.friend_updated_toast, Toast.LENGTH_LONG).show();
            }
        });

        // Close alert on 'Cancel'
        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        alert.show();
    }

    private void editFriendDetails(UUID id, String name, String email) {


        for (Friend f : db.getAllFriends()) {
            db.updateFriend(id.toString(), name, email);
            customFriendDetailsArrayAdapter.updateItems(db.getAllFriends());
        }
    }

    private void removeFriendFromMeeting(Friend f) {
        for (Meeting m : DataSingleton.getInstance().getMeetingList())
            if (m.getFriendList().contains(f)) {
                m.getFriendList().remove(f);
            }
    }


    private Friend getFriendForPopulatingHints() {
        for(Friend f : db.getAllFriends()){
            if(f.getId().equals(id)){

                return f;
            }
        }

        return null;
    }

}
