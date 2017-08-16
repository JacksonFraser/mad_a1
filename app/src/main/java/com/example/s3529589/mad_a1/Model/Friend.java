package com.example.s3529589.mad_a1.Model;

import android.widget.DatePicker;

public class Friend {
    private String id;
    private String name;
    private String email;
    private String birthdate;

    public Friend(String name, String email, String birthdate){
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getBirthdate(){
        return birthdate;
    }
}
