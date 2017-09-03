package com.example.s3529589.mad_a1.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataSingleton {
    private static DataSingleton instance = null;
    private static List<Friend> friendList = new ArrayList<>();
    private static List<Meeting> meetingList = new ArrayList<>();

    protected DataSingleton() {

    }

    public static DataSingleton getInstance() {
        if (instance == null)
            instance = new DataSingleton();
        return instance;
    }

    public List<Friend> getFriendList() {
        return friendList;
    }

    public List<Meeting> getMeetingList() {
        return meetingList;
    }

    public Friend getFriendById(UUID id) {
        for (Friend f : friendList)
            if (id.equals(f.getId()))
                return f;

        return null;
    }

    public Meeting getMeetingById(UUID id) {
        for (Meeting m : meetingList)
            if (id.equals(m.getId()))
                return m;

        return null;
    }


}
