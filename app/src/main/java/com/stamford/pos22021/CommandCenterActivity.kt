package com.stamford.pos22021

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class CommandCenterActivity : AppCompatActivity() {
    private val TAG = "CommandCenterActivity"
    private val CHANNEL_ID = "90200"
    private val notificationId = 1
    private val EXTRA_NOTIFICATION_ID = "notif_id"


    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Play BG Status Channel"
            val descriptionText =
                "A notification to show play background music status"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_command_center)




        val btnClickOrder = findViewById<Button>(R.id.button_newOrder)
// set on-click listener
        btnClickOrder.setOnClickListener {
            Toast.makeText(this, "Kimseng has clicked the new order button", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ProductCatActivity::class.java)
            startActivity(intent)
        }

        val btnClickAddProduct = findViewById<Button>(R.id.button_addProduct)
// set on-click listener
        btnClickAddProduct.setOnClickListener {
            Toast.makeText(this, "Kimseng has clicked the add product button", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, CRUDProductCat::class.java)
            startActivity(intent)
        }

    }

    fun onclick_play_music_in_bg_btn (view: View) {
        Log.i(TAG, "Play music in bg clicked")
        Intent(this@CommandCenterActivity, PlayMusicInBGService::class.java).also { intent ->
            startService(intent)
        }
        val intent = Intent(this, CommandCenterActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)



        val playIntent = Intent(this, PlayMusicBroadcastReceiver::class.java).apply {

            action = ACTION_POS_PLAY

            putExtra(EXTRA_NOTIFICATION_ID, notificationId)

        }



        val playPendingIntent: PendingIntent =

            PendingIntent.getBroadcast(this, 0, playIntent, 0)
                



        val stopIntent = Intent(this, PlayMusicBroadcastReceiver::class.java).apply {

            action = ACTION_POS_STOP

            putExtra(EXTRA_NOTIFICATION_ID, notificationId)

        }



        val stopPendingIntent: PendingIntent =

            PendingIntent.getBroadcast(this, 0, stopIntent, 0)

        //Create a channel
        createNotificationChannel()

        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_bg_music_stat)
            .setContentTitle("Stamford POS BG Music Status")
            .setContentText("Status = Playing")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(false)
            .addAction(R.drawable.ic_action_play, "Play",
        playPendingIntent)
            .addAction(R.drawable.ic_action_stop, "Stop", stopPendingIntent)
        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }


    }
    fun onclick_stop_music_in_bg_btn (view: View) {
        Log.i(TAG, "Stop music in bg clicked")
        Intent(this@CommandCenterActivity, PlayMusicInBGService::class.java).also { intent ->
            stopService(intent)
        }

    }

}