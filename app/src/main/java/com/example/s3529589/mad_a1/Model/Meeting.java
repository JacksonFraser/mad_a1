package com.example.s3529589.mad_a1.Model;

import com.example.s3529589.mad_a1.Exceptions.InvalidMeetingInput;
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

    public Meeting(String title, Date start, Date finish, List<Friend> friendList, String location) throws InvalidMeetingInput{
        if(finish.before(start))
            throw new InvalidMeetingInput("Start time has to be before end time");
        if(start == null)
            throw new InvalidMeetingInput("Meeting start time cannot be null");
        if(finish == null)
            throw new InvalidMeetingInput("Meeting end time cannot be null");
        if(title.isEmpty())
            throw new InvalidMeetingInput("Meeting has to have a title");

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

    public Date getStartTime() {
        return startTime;
    }

    public Date getFinishTime() { return finishTime; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartTime(Date startTime) throws InvalidMeetingInput {
        if(startTime.after(this.finishTime)) {
            throw new InvalidMeetingInput("Start time must not be after finish time");
        } else {
            this.startTime = startTime;
        }
    }

    public void setFinishTime(Date finishTime) throws InvalidMeetingInput {
        if(finishTime.before(this.startTime)) {
            throw new InvalidMeetingInput("Finish time must be after start time");
        } else {
            this.finishTime = finishTime;
        }
    }
}
