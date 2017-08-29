package com.example.s3529589.mad_a1.Model;

import java.util.ArrayList;
import java.util.List;

public class DataSingleton {
    private static DataSingleton instance = null;
    private static List<Friend> friendList = new ArrayList<>();
    private static List<Meeting> modelList = new ArrayList<>();

    protected DataSingleton(){

    }

    public static DataSingleton getInstance(){
        if(instance == null)
            instance = new DataSingleton();
        return instance;
    }

    public List<Friend> getFriendList(){
        return friendList;
    }
    public Friend getFriendById(int id){
        for(Friend f : friendList)
            if(id == f.getId())
                return f;

        return null;
    }
}
