package wily.apps.wilyrabbit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TimePicker alarmTimepicker;
    private Button registBtn;
    private Button unRegistBtn;

    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmTimepicker = findViewById(R.id.alarm_timepicker);
        registBtn = findViewById(R.id.registBtn);
        registBtn.setOnClickListener(btnOnClickListener);
        unRegistBtn = findViewById(R.id.unRegistBtn);
        unRegistBtn.setOnClickListener(btnOnClickListener);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    View.OnClickListener btnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.registBtn:
                    registAlarm();
                    break;
                case R.id.unRegistBtn:
                    unregistAlarm();
                    break;
            }
        }
    };

    String CHANNEL_ID = "Wily Rabbit";
    String CHANNEL_NAME = "Wily Rabbit";
    String CHANNEL_DESCRIPT = "Wily Rabbit is good habbit";
    public void registAlarm() {
        Intent intent = new Intent(this, Alarm.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        int hour = alarmTimepicker.getHour();
        int minute = alarmTimepicker.getMinute();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),  AlarmManager.INTERVAL_DAY, pIntent);

        // Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(CHANNEL_NAME).setContentText(CHANNEL_DESCRIPT+" ["+hour+":"+minute+"]").setAutoCancel(false);

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT));
        }
        notificationManager.notify(4935, builder.build());
    }

    public void unregistAlarm() {
        Intent intent = new Intent(this, Alarm.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.cancel(pIntent);

        // Notification
        NotificationManagerCompat.from(this).cancel(4935);
    }
}