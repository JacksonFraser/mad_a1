package com.example.s3529589.mad_a1.Model;

import com.example.s3529589.mad_a1.Exceptions.InvalidMeetingInput;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Meeting {
    private String title;
    private Date startTime;
    private Date finishTime;
    private UUID uuid;
    private String location;

    public Meeting(String title, Date start, Date finish,  String location) throws InvalidMeetingInput {
        if (start == null)
            throw new InvalidMeetingInput("Meeting start time cannot be null");
        if (finish == null)
            throw new InvalidMeetingInput("Meeting end time cannot be null");
        if (title.isEmpty())
            throw new InvalidMeetingInput("Meeting has to have a title");
        if (finish.before(start))
            throw new InvalidMeetingInput("Start time has to be before end time");
        if (!validLocation(location))
            throw new InvalidMeetingInput("Invalid location");

        this.uuid = UUID.randomUUID();
        this.title = title;
        this.startTime = start;
        this.finishTime = finish;
        this.location = location;
    }

    public Meeting(){

    }

    public UUID getId() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public String getLocation() {
        return location;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setId(UUID uuid) {
        this.uuid = uuid;
    }


    public void setStartTime(Date startTime) throws InvalidMeetingInput {
        this.startTime = startTime;
    }

    public void setFinishTime(Date finishTime) throws InvalidMeetingInput {
        this.finishTime = finishTime;
    }

    private boolean validLocation(String location) {
        if (location.matches("([+-]?\\d+\\.?\\d+)\\s*,\\s*([+-]?\\d+\\.?\\d+)"))
            return true;
        return false;
    }
}
