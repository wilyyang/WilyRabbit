package wily.apps.wilyrabbit;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class Alarm extends BroadcastReceiver {
    String CHANNEL_ID = "Wily Rabbit";
    String CHANNEL_NAME = "Wily Rabbit";
    String CHANNEL_DESCRIPT = "Wily Rabbit is good habbit";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(CHANNEL_NAME).setContentText(CHANNEL_DESCRIPT+" [Alarm Ringing]").setAutoCancel(false);

        notificationManager.notify(4935, builder.build());
    }
}
