package com.example.s3529589.mad_a1.Model;

public class Friend {
    private int id;
    private String name;
    private String email;
    private String birthday;
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
    public int getId(){
        return id;
    }
}
