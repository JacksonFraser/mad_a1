package com.example.s3529589.mad_a1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.s3529589.mad_a1.Controller.AddFriendController;
import com.example.s3529589.mad_a1.Model.ContactDataManager;
import com.example.s3529589.mad_a1.R;

public class FriendMenuActivity extends Activity{
    private DatePickerActivity datePickerActivity;
    private String name;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_menu);

        View addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new AddFriendController(this));

<<<<<<< HEAD:app/src/main/java/com/example/s3529589/mad_a1/Activity/FriendMenuActivity.java
        // Recieve intent from DatePickerActivity
        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        System.out.println("Printing date in FriendMenuActivity: " + date);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                ContactDataManager contactsManager = new ContactDataManager(this, data);
                String name = "";
                String email = "";
                try {
                    name = contactsManager.getContactName();
                    email = contactsManager.getContactEmail();

                    // Immediately prompt user to choose birthday after selecting contact
                    Intent it = new Intent(this, DatePickerActivity.class);
                    this.startActivity(it);

                    // Finish activity
                    finish();

                } catch (ContactDataManager.ContactQueryException e) {
                }
            }
=======
        for(Friend f : DateSingleton.getInstance().getFriendList()){
            System.out.println(f.getName());
            System.out.println(f.getEmail());
            System.out.println(f.getBirthdate());
>>>>>>> testBranch:app/src/main/java/com/example/s3529589/mad_a1/Model/FriendMenuActivity.java
        }
    }
}

