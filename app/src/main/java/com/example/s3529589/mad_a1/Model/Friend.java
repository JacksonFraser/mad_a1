package com.example.s3529589.mad_a1.Model;

import java.util.Date;
import java.util.UUID;

public class Friend {
    private String name;
    private String email;
    private Date birthday;
    private UUID uuid;
    private double lon;
    private double lat;

    public Friend(String name, String email, Date birthday, double lon, double lat){
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.lon = lon;
        this.lat = lat;
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

    public UUID getId(){
        return uuid;
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
