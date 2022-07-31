package com.example.exampleeee.data

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.exampleeee.MainActivity
import com.example.exampleeee.R

class MyService: Service() {
    private val CHANNEL_ID = "foreground_service"

    companion object {

        fun startService(context: Context, message: String) {
            val startIntent = Intent(context, MyService::class.java)
            startIntent.putExtra("inputtext", message)
            ContextCompat.startForegroundService(context, startIntent)
        }
        fun stopService(context: Context) {
            val stopIntent = Intent(context, MyService::class.java)
            context.stopService(stopIntent)
        }

    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val input = intent?.getStringExtra("inputExtra")
        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("title")
            .setContentText(input)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)

        return START_NOT_STICKY
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "my service channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager!!.createNotificationChannel(serviceChannel)
        }
    }

}