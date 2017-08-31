package com.example.s3529589.mad_a1.Model;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.s3529589.mad_a1.Controller.friendControllers.EditBirthDateController;
import com.example.s3529589.mad_a1.R;

public class CustomEditFriendDetailsAlertDialog extends AlertDialog.Builder{
    private CustomArrayAdapter customArrayAdapter;
    private int id;

    public CustomEditFriendDetailsAlertDialog(CustomArrayAdapter customArrayAdapter, int id) {
        super(customArrayAdapter.getContext());
        this.customArrayAdapter = customArrayAdapter;
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
            for (Friend f : DataSingleton.getInstance().getFriendList()) {
                if (f.getId() == id) {
                    DataSingleton.getInstance().getFriendList().remove(f);
                    customArrayAdapter.notifyDataSetChanged();
                    Toast.makeText(customArrayAdapter.getContext(), "Friend Removed", Toast.LENGTH_LONG).show();
                }
            }

        } catch (Exception e) {
        }
    }

    // When the user selects Edit after long hold
    public void editFriend() {
        String choices[] = {"Cancel", "Confirm"};
        LayoutInflater factory = LayoutInflater.from(customArrayAdapter.getContext());

        final View textEntryView = factory.inflate(R.layout.edit_friend, null);

        final EditText editName = (EditText) textEntryView.findViewById(R.id.edit_friend_name);
        editName.setHint(DataSingleton.getInstance().getFriendById(id).getName());

        final EditText editEmail = (EditText) textEntryView.findViewById(R.id.edit_friend_email);
        editEmail.setHint(DataSingleton.getInstance().getFriendById(id).getEmail());

        final Button editBtn = (Button) textEntryView.findViewById(R.id.selectDate);

        // Select birthday
        editBtn.setOnClickListener(new EditBirthDateController(customArrayAdapter.getContext(),id));

        AlertDialog.Builder alert = new AlertDialog.Builder(customArrayAdapter.getContext());
        alert.setTitle("Edit details");
        alert.setView(textEntryView);

        // Edit friend details on 'Confirm'
        alert.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String name = editName.getText().toString();
                String email = editEmail.getText().toString();
                editFriendDetails(id, name, email);

                Toast.makeText(customArrayAdapter.getContext(), "Friend updated", Toast.LENGTH_LONG).show();
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

    private void editFriendDetails(int id, String name, String email) {
        try {

            for (Friend f : DataSingleton.getInstance().getFriendList()) {
                if (f.getId() == id) {
                    if (!name.isEmpty())
                        f.setName(name);
                    if (!email.isEmpty())
                        f.setEmail(email);
                    customArrayAdapter.notifyDataSetChanged();
                }
            }

        } catch (Exception e) {

        }
    }

}
