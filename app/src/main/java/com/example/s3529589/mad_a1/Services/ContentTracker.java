package com.example.s3529589.mad_a1.Services;

import android.app.Application;
import android.content.Context;

public class ContentTracker extends Application {
    private static ContentTracker instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }

}
