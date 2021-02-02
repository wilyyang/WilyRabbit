package wily.apps.wilyrabbit;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;


import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import wily.apps.wilyrabbit.dao.TodoDao;
import wily.apps.wilyrabbit.database.RecordDatabase;
import wily.apps.wilyrabbit.database.TodoDatabase;
import wily.apps.wilyrabbit.entity.Record;
import wily.apps.wilyrabbit.entity.Todo;
import wily.apps.wilyrabbit.util.NotificationUtil;

public class CheckService extends Service {
    private Context mContext;

    public static final String ACTION_SERVICE_INIT = "ACTION_SERVICE_INIT";
    public static final String ACTION_SERVICE_STOP = "ACTION_SERVICE_STOP";
    public static final String ACTION_CHECK = "ACTION_CHECK";
    public static final String ACTION_ALARM = "ACTION_ALARM";
    public static final String ACTION_REGISTER_CHECK = "ACTION_REGISTER_CHECK";
    public static final String ACTION_REGISTER_TIMER = "ACTION_REGISTER_TIMER";

    public static final String ACTION_WORK_CHECK = "ACTION_WORK_CHECK";

    private NotificationUtil notiUtil = null;
    private boolean isInit = false;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mContext = getApplicationContext();

        if (intent != null) {
            String action = intent.getAction();
            switch (action) {
                case ACTION_SERVICE_INIT:
                    initCheckService();
                    break;
                case ACTION_SERVICE_STOP:
                    stopCheckService();
                    break;
                case ACTION_CHECK:
                    checkTest();
                    break;
                case ACTION_ALARM:
                    alarmTest();
                    break;
                case ACTION_REGISTER_CHECK:
                    registerCheckNoti(intent.getExtras());
                    break;
                case ACTION_WORK_CHECK:
                    workCheck(intent.getExtras());
                    break;
                case ACTION_REGISTER_TIMER:
                    checkTest();
                    break;
            }
        }
        return START_STICKY;
    }

    // ACTION_ALARM
    private void alarmTest(){
        notiUtil.sendNotification("알람입니다");
    }

    // ACTION_WORK_CHECK
    private void workCheck(Bundle bundle){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String formatDate = sdfNow.format(date);

        Calendar cal = Calendar.getInstance ( );
        cal.setTime ( date );

        RecordDatabase db = RecordDatabase.getAppDatabase(this);

        Record record = new Record(bundle.getInt("type"), bundle.getString("title"), bundle.getBoolean("status"),
                cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND)
                );
        db.recordDao().insert(record).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    // ACTION_REGISTER_CHECK
    private void registerCheckNoti(Bundle bundle){
        String title = bundle.getString("title");
        NotificationManager notiManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel(title, title, NotificationManager.IMPORTANCE_HIGH);

        Intent checkIntent = new Intent(mContext, CheckService.class);
        checkIntent.setAction(CheckService.ACTION_WORK_CHECK);
        checkIntent.putExtra("type", bundle.getInt("type"));
        checkIntent.putExtra("title", title);
        PendingIntent checkPending = PendingIntent.getService(mContext, 0, checkIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, title)
                .setContentTitle(title)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(false)
                .setSmallIcon(R.drawable.ic_find_alarm)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .addAction(android.R.drawable.btn_default, "Check", checkPending);
        builder.setContentText("Check : 0");
        notiManager.createNotificationChannel(channel);
        notiManager.notify(bundle.getInt("type"), builder.build());
    }

    // ACTION_CHECK
    private int count = 0;
    private void checkTest(){
        ++count;
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String formatDate = sdfNow.format(date);

        TodoDatabase db = TodoDatabase.getAppDatabase(this);

        db.todoDao().insert(new Todo(formatDate)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        notiUtil.sendNotification("Count : "+count+" Time : "+formatDate);
    }

    // ACTION_SERVICE_INIT
    private void initCheckService(){
        if(isInit){
            return;
        }
        isInit = true;
        notiUtil = new NotificationUtil(mContext);
        startForeground(notiUtil.CHANNEL_ID, notiUtil.builder.build());
        notiUtil.sendNotification("CheckService Init");
    }

    // ACTION_SERVICE_STOP
    private void stopCheckService() {
        notiUtil.sendNotification("Service Dead");
        stopForeground(true);
        stopSelf();
    }


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
