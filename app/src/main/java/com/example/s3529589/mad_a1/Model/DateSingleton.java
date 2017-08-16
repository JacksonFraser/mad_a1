package com.example.s3529589.mad_a1.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by supriya on 16/08/17.
 */

public class DateSingleton {
    private static DateSingleton instance = null;
    private static List<Friend> friendList = new ArrayList<>();

    protected DateSingleton(){

    }

    public static DateSingleton getInstance(){
        if(instance == null)
            instance = new DateSingleton();
        return instance;
    }

    public List<Friend> getFriendList(){
        return friendList;
    }
}
