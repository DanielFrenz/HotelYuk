package com.example.hotelyuk;

import static com.example.hotelyuk.notification.MyApplication.CHANNEL_1_ID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.hotelyuk.preferences.UserPreferences;
import com.example.hotelyuk.entity.User;
import com.example.hotelyuk.ui.nav.accounts.AccountsFragment;
import com.example.hotelyuk.ui.nav.history.HistoryFragment;
import com.example.hotelyuk.ui.nav.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private NotificationManagerCompat notificationManager;
    private User user;
    private UserPreferences userPreferences;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.title_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        notificationManager = NotificationManagerCompat.from(this);

        userPreferences = new UserPreferences(this);
        user = userPreferences.getUserLogin();

        sendNotification();
    }

    public void sendNotification(){
        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity( this,
                0,activityIntent,0);

        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.logo);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle("Hotel Yuk")
                .setContentText("Banyak Promo Yang Menanti Anda!!")
                .setLargeIcon(picture)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Halo, " + user.getUsername() + "\nCek Promo Terbaru Dari Kami!\n" +
                                "- Diskon 20% untuk semua kamar hotel menggunakan OVO (Sampai tanggal 31 Oktober)\n" +
                                "- Diskon 10% untuk semua kamar hotel menggunakan Dana (Sampai tanggal 31 Oktober\n\n" +
                                "Tunggu Apa Lagi, Ayo Pesan Hotel Sekarang!")
                        .setBigContentTitle("Hotel Yuk")
                        .setSummaryText("Promo"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.GREEN)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build();

        notificationManager.notify(1, notification);
    }
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_fragment,fragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.navigation_home:
                changeFragment(new HomeFragment());
                setTitle(R.string.title_home);
                return true;
            case R.id.navigation_history:
                changeFragment(new HistoryFragment());
                setTitle(R.string.title_history);
                return true;
            case R.id.navigation_accounts:
                changeFragment(new AccountsFragment());
                setTitle(R.string.title_accounts);
                return true;
        }
        return false;
    }
}