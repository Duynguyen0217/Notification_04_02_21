package com.example.notification_04_02_21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button mBtnNotify;
    String CHANNEL_ID = "MY_CHANNEL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnNotify = findViewById(R.id.buttonNotification);

        mBtnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*tạo giao diện cho notification*/NotificationCompat.Builder notify = new NotificationCompat.Builder(MainActivity.this , CHANNEL_ID)
      /*set giao diện*/ .setSmallIcon(android.R.drawable.btn_star)
                        .setShowWhen(true) //giá trị true cho phép hiện thời gian
                         .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.notification))
                        .setContentTitle("Thông báo mới")
                        .setContentText("Có phiên bản mới")
                        .setPriority(2); //priority càng cao thì thiết bị mau nóng



                  NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                      NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID ,
                              "Notification",
                              NotificationManager.IMPORTANCE_HIGH);

                      AudioAttributes audioAttributes = new AudioAttributes.Builder()
                              .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                              .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                              .build();
                      notificationChannel.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.nhachoakoloi),audioAttributes);
                      notificationChannel.enableVibration(true);
                      notificationChannel.enableLights(true);
                      notificationChannel.setLightColor(Color.RED);

                      notificationManager.createNotificationChannel(notificationChannel);

                  }
                  notificationManager.notify(1, notify.build());
            }
        });
    }
}
