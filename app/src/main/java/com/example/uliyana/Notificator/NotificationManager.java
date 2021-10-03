package com.example.uliyana.Notificator;

import android.app.NotificationChannel;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.uliyana.R;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class NotificationManager {
    Context context;
    private NotificationCompat.Builder builder;

    public  NotificationManager(Context context){
        this.context = context;
        this.builder = new NotificationCompat.Builder(context,"CHANNEL_ID")
                .setSmallIcon(R.drawable.sheiz)
                .setContentTitle(context.getString(R.string.onNewCardNot))
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        prepareNotificationChannel();
    }

    private void prepareNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = R.string.app_name+"_channel";
            String descriptipon = "Channel owner";
            int importance = android.app.NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID",name,importance);
            channel.setDescription(descriptipon);
            android.app.NotificationManager notificationManager = context.getSystemService(android.app.NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void notify(String contentText){
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        this.builder
                .setContentText(contentText);
        notificationManagerCompat.notify(ThreadLocalRandom.current().nextInt(0,Integer.MAX_VALUE/1000), builder.build());

    }
}
