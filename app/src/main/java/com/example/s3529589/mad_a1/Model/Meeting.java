package com.example.s3529589.mad_a1.Model;

import com.example.s3529589.mad_a1.Exceptions.InvalidDateInput;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Meeting {
    private int id;
    private String title;
    private Date startDate;
    private Date endDate;
    private static int uuid = 0;
    private List<Friend> friendList = new ArrayList<>();
    private String location;

    public Meeting(String title, Date startDate, Date endDate, List<Friend> friendList, String location) throws InvalidDateInput {

        if(endDate.before(startDate))
            throw new InvalidDateInput("End date cannot be before start date");

        this.id = uuid;
        uuid++;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.friendList = friendList;
        this.location = location;
    }

}
