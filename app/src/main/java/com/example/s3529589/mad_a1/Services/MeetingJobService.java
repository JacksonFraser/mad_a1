package com.example.s3529589.mad_a1.Services;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.s3529589.mad_a1.Activity.MainActivity;
import com.example.s3529589.mad_a1.Adapter.CustomEditFriendDetailsAlertDialog;
import com.example.s3529589.mad_a1.Adapter.CustomFriendDetailsArrayAdapter;

import java.util.UUID;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MeetingJobService extends JobService {
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
        // 10 seconds of working (1000*10ms)
        // If the job has been cancelled, stop working; the job will be rescheduled.
            if (jobCancelled)
                return;

            try { Thread.sleep(10); } catch (Exception e) { }

        Log.d(TAG, "Job finished!");
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
}