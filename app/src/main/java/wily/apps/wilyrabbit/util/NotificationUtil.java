package wily.apps.wilyrabbit.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import wily.apps.wilyrabbit.CheckService;
import wily.apps.wilyrabbit.R;

public class NotificationUtil {
    private Context mContext;

    public NotificationCompat.Builder builder;
    private NotificationManager mNotificationManager;
//    private NotificationCompat.BigTextStyle bigTextStyle;
    private NotificationChannel channel;

    private final String NOTI_CHANNEL_ID = "10001";
    public static int CHANNEL_ID = 10;
    private static int NOTI_PROGRESS_ID = 0x100;
    private String CHANNEL_NAME = "CHECK TEST";
    private String NOTI_TITLE = "Count : ";
    private String NOTI_BIG_CONTENT = "Time : ";

    private int PROGRESS_MAX = 100;

    public NotificationUtil(Context context) {
        mContext = context;
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        channel = new NotificationChannel(NOTI_CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

//        bigTextStyle = new NotificationCompat.BigTextStyle();
//        bigTextStyle.setBigContentTitle(NOTI_TITLE);
//        bigTextStyle.bigText(NOTI_BIG_CONTENT);


        Intent checkIntent = new Intent(mContext, CheckService.class);
        checkIntent.setAction(CheckService.ACTION_CHECK);
        PendingIntent checkPending = PendingIntent.getService(mContext, 0, checkIntent, 0);

        Intent exitIntent = new Intent(mContext, CheckService.class);
        exitIntent.setAction(CheckService.ACTION_SERVICE_STOP);
        PendingIntent exitPending = PendingIntent.getService(mContext, 0, exitIntent, 0);

        builder = new NotificationCompat.Builder(mContext, NOTI_CHANNEL_ID)
//                .setStyle(bigTextStyle)
                .setContentTitle(NOTI_TITLE)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(false)
                .setSmallIcon(R.drawable.ic_find_alarm)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .addAction(android.R.drawable.btn_default, "Check", checkPending)
                .addAction(android.R.drawable.btn_default, "Exit", exitPending);
    }

    public void sendNotification( String msg) {
        builder.setContentText(msg);
        mNotificationManager.createNotificationChannel(channel);
        mNotificationManager.notify((++CHANNEL_ID), builder.build());
    }

    public void sendNotification(int id, String msg) {
        builder.setContentText(msg);
        mNotificationManager.createNotificationChannel(channel);
        mNotificationManager.notify(id, builder.build());
    }


    public void sendNotification(int id, String msg, int progress) {
        builder.setContentText(msg);
        if(progress >= 0 && progress <PROGRESS_MAX){
            builder.setProgress(PROGRESS_MAX, progress, false);
        }else{
            builder.setProgress(0, 0, false);
        }
        mNotificationManager.createNotificationChannel(channel);
        mNotificationManager.notify(id, builder.build());
    }
}
