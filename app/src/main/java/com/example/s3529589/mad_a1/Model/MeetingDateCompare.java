package com.example.s3529589.mad_a1.Model;

import java.util.Comparator;
import java.util.Date;
import java.util.Random;

public class MeetingDateCompare implements Comparator<Meeting> {

    @Override
    public int compare(Meeting o1, Meeting o2) {
        return o1.getStartTime().compareTo(o2.getStartTime());
    }
}
