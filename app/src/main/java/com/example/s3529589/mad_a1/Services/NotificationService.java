package com.example.s3529589.mad_a1.Services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.s3529589.mad_a1.Model.MeetingAlarm;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NotificationService extends JobService {
    private static final String TAG = MeetingJobService.class.getSimpleName();
    boolean isWorking = false;
    boolean jobCancelled = false;


    // Called by the Android system when it's time to run the job
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG, "Job started!");
        isWorking = true;
        // We need 'jobParameters' so we can call 'jobFinished'
        startWorkOnNewThread(jobParameters); // Services do NOT run on a separate thread

        return isWorking;
    }

    private void startWorkOnNewThread(final JobParameters jobParameters) {
        new Thread(new Runnable() {
            public void run() {
                doWork(jobParameters);
            }
        }).start();
    }

    private void doWork(JobParameters jobParameters) {
        startAlarm();
        /*
        Intent it = new Intent(this, JobSchedulerActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(it);
        //friendMenuActivity.finish();
        */

        if (jobCancelled)
            return;


        isWorking = false;
        boolean needsReschedule = false;
        jobFinished(jobParameters, needsReschedule);
    }

    // Called if the job was cancelled before being finished
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG, "Job cancelled before being completed.");
        jobCancelled = true;
        boolean needsReschedule = isWorking;
        jobFinished(jobParameters, needsReschedule);
        return needsReschedule;
    }

    // Alarm + notification related code
    private void startAlarm(){
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent myIntent;
        PendingIntent pendingIntent;

        myIntent = new Intent(NotificationService.this, MeetingAlarm.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime()+1000, pendingIntent);
    }
}