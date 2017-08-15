package com.example.s3529589.mad_a1.Model;

public class Friend {
    private String id;
    private String name;
    private String email;
    private String birthday;

    public Friend(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

}
