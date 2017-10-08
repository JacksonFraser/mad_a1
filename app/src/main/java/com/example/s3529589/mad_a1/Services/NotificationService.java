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

import com.example.s3529589.mad_a1.Activity.MeetingAlarm;
import com.example.s3529589.mad_a1.Database.MeetingTable;
import com.example.s3529589.mad_a1.Model.Meeting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NotificationService extends JobService {
    private static final String TAG = SuggestMeetingService.class.getSimpleName();
    boolean isWorking = false;
    boolean jobCancelled = false;
    private static List<UUID> alarmMeetingList = new ArrayList<>();
    private  Meeting meeting;

    // Called by the Android system when it's time to run the job
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
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
        // How many minutes before a meeting to display a notification
        int meetingVariable = (60*1000)*3;

        Date date = new Date(System.currentTimeMillis() + meetingVariable);
        MeetingTable meetingTable = new MeetingTable();

        boolean foundAMeeting = false;
        for(Meeting m : meetingTable.getAllMeetings()){
            if(date.after(m.getStartTime()) && !alarmMeetingList.contains(m.getId()) && !foundAMeeting){
                System.out.println("THIS IS THE SIZE " + alarmMeetingList.size());
                meeting = m;
                alarmMeetingList.add(m.getId());
                foundAMeeting = true;
                startAlarm();
            }
        }

        if (jobCancelled)
            return;

        isWorking = false;
        boolean needsReschedule = false;
        jobFinished(jobParameters, needsReschedule);
    }

    // Called if the job was cancelled before being finished
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        jobCancelled = true;
        boolean needsReschedule = isWorking;
        jobFinished(jobParameters, needsReschedule);
        return needsReschedule;
    }

    // Alarm + notification related code
    private void startAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent myIntent;
        PendingIntent pendingIntent;

        myIntent = new Intent(NotificationService.this, MeetingAlarm.class);
        myIntent.putExtra("meeting_title", meeting.getTitle());
        pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 1000, pendingIntent);
    }
}