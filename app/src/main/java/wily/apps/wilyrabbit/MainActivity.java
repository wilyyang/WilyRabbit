package wily.apps.wilyrabbit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
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
    }

    public void unregistAlarm() {
        Intent intent = new Intent(this, Alarm.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.cancel(pIntent);
    }
}