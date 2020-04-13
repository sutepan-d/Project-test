package com.example.project;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class Serv extends Service {
    MediaPlayer mediaPlayer;
    long startTime;
    public Serv() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.sound);
        mediaPlayer.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        long currentTime = System.currentTimeMillis();
        while (true){
            if (currentTime - startTime > 1000) {
                mediaPlayer.start();
                break;
            }
            currentTime = System.currentTimeMillis();
        }
        Intent back = new Intent(this, MainActivity.class);
        PendingIntent notif = PendingIntent.getActivity(this, 0, back, PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this, "MyNote").setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("Подъём").setContentText("Пора на революцию!").setContentIntent(notif);
        Notification note = nBuilder.build();
        notificationManager.notify(4578612, note);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
