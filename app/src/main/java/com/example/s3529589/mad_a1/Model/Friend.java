package com.example.s3529589.mad_a1.Model;

import java.util.Date;

public class Friend {
    private int id;
    private String name;
    private String email;
    private Date birthday;

    private static int uuid = 0;
    public Friend(String name, String email, Date birthday){
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

    public Date getBirthdate(){
        return birthday;
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

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
