package com.example.s3529589.mad_a1.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by s3529589 on 8/26/17.
 */

public class Meeting {
    private int id;
    private String title;
    private Date startDate;
    private Date endDate;
    private List<Friend> friendList = new ArrayList<>();

    private static int uuid = 0;


    public Meeting(int id, String title, Date startDate, Date endDate, List<Friend> friendList){
        this.id = uuid;
        uuid++;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.friendList = friendList;

    }
}
