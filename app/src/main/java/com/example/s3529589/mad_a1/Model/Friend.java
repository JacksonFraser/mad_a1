package com.example.s3529589.mad_a1.Model;

import android.graphics.drawable.Drawable;

public class Friend {
    private int id;
    private String name;
    private String email;
    private String birthday;
    private Drawable displayPicture;

    private static int uuid = 0;
    public Friend(String name, String email, String birthday){
        id = uuid;
        uuid++;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getBirthdate(){
        return birthday;
    }

    public Drawable getDisplayPicture() {
        return displayPicture;
    }

    public int getId(){
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setDisplayPicture(Drawable displayPicture) {
        this.displayPicture = displayPicture;
    }
}
