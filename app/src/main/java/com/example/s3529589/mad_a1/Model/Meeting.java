package com.example.s3529589.mad_a1.Model;

import com.example.s3529589.mad_a1.Exceptions.InvalidDateInput;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Meeting {
    private int id;
    private String title;
    private Date startTime;
    private Date finishTime;
    private static int uuid = 0;
    private List<Friend> friendList = new ArrayList<>();
    private String location;

    public Meeting(String title, Date start, Date finish, List<Friend> friendList, String location) {
        this.id = uuid;
        uuid++;
        this.title = title;
        this.startTime = start;
        this.finishTime = finish;
        this.friendList = friendList;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
