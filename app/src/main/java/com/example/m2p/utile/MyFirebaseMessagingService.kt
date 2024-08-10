package com.example.m2p.utile

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        Log.d("TAG", "Refreshed token: $token")
    }
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Handle the message here, for example, show a notification
        val notification = remoteMessage.notification
        if (notification != null) {
            showNotification(notification.title, notification.body)
        }
    }

    private fun showNotification(title: String?, message: String?) {
        // Create and display a notification
        createNotificationChannel(this)

        if (title != null) {
            showNotification(this,title)
        }

    }
}
