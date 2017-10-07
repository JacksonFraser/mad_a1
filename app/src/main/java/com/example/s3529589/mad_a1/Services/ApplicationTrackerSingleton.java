package com.example.s3529589.mad_a1.Services;


import android.app.Activity;
import android.app.Application;

public class ApplicationTrackerSingleton {
    private static ApplicationTrackerSingleton instance = null;
    private Activity activity;

    protected ApplicationTrackerSingleton() {

    }


    public static ApplicationTrackerSingleton getInstance() {
        if (instance == null) {
            instance = new ApplicationTrackerSingleton();
        }
        return instance;
    }

    public void setCurrentActivity(Activity mCurrentActivity) {
        this.activity = mCurrentActivity;
    }
    public  Activity getActivity(){
        return activity;
    }
}
