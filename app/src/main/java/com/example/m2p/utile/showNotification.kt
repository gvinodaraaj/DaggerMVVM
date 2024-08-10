package com.example.m2p.utile

import android.annotation.SuppressLint
import com.example.m2p.R
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.m2p.view.profileActivity

@SuppressLint("MissingPermission")
fun showNotification(context: Context,title:String="test default") {
    val channelId = "your_channel_id"

    // Intent to open your activity when the notification is clicked
    val intent = Intent(context, profileActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    // Build the notification
    val notification = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.profile_img) // Replace with your own icon
        .setContentTitle("Your Notification Title")
        .setContentText("This is your notification message.")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true) // Dismiss notification when clicked
        .build()

    // Show the notification
    with(NotificationManagerCompat.from(context)) {
        notify(1, notification) // Notification ID can be any integer
    }
}
