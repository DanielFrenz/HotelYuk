package com.example.hotelyuk.notification;

import static com.example.hotelyuk.notification.MyApplication.CHANNEL_1_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.hotelyuk.MainActivity;
import com.example.hotelyuk.R;
import com.google.firebase.messaging.FirebaseMessagingService;

public class PushNotificationService extends FirebaseMessagingService {
}
