package com.example.m2p.utile

import AlarmWorker
import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

fun scheduleAlarm(context:Context) {
    // Delay for 10 minutes (for example)
    val delay = 1L // in minutes

    val alarmRequest = OneTimeWorkRequestBuilder<AlarmWorker>()
        .setInitialDelay(delay, TimeUnit.MINUTES)
        .build()

    WorkManager.getInstance(context).enqueue(alarmRequest)
}
