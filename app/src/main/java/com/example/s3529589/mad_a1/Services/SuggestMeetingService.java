package com.example.s3529589.mad_a1.Services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.s3529589.mad_a1.Activity.CreateMeetingPromptActivity;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SuggestMeetingService extends JobService {
    private static final String TAG = SuggestMeetingService.class.getSimpleName();
    boolean isWorking = false;
    boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        isWorking = true;
        startWorkOnNewThread(jobParameters);

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
        if (jobCancelled)
            return;

        Intent it = new Intent(this, CreateMeetingPromptActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(it);

        isWorking = false;
        boolean needsReschedule = false;
        jobFinished(jobParameters, needsReschedule);
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        jobCancelled = true;
        boolean needsReschedule = isWorking;
        jobFinished(jobParameters, needsReschedule);
        return needsReschedule;
    }

    @Override
    public void onDestroy() {
        this.jobCancelled = true;
    }
}