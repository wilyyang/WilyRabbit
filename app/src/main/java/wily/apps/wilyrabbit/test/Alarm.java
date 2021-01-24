package wily.apps.wilyrabbit.test;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import wily.apps.wilyrabbit.R;


public class Alarm extends BroadcastReceiver {
    String CHANNEL_ID = "Wily Rabbit";
    String CHANNEL_NAME = "Wily Rabbit";

    @Override
    public void onReceive(Context context, Intent intent) {
        onPreExecute(context);
        Flowable.interval(100L, TimeUnit.MILLISECONDS).take(100).subscribeOn(AndroidSchedulers.mainThread()).subscribe(
                val -> onProgressUpdate(Math.toIntExact(val)),
                error -> error.printStackTrace(),
                ()-> onPostExecute()
        );
    }

    private NotificationManager notiManager;
    private NotificationCompat.Builder builder;
    private int count;

    private void onPreExecute(Context context){
        notiManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(context, CHANNEL_ID).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(CHANNEL_NAME).setContentText("[Timer Start]").setAutoCancel(false)
                .setProgress(100, 0, false);
        notiManager.notify(4935, builder.build());
        count = 0;
    };

    protected void onProgressUpdate(int count) {
        builder.setProgress(100, count, false).setContentText("["+count+"]");
        notiManager.notify(4935, builder.build());
    }

    private void onPostExecute(){
        builder.setProgress(0, 0, false).setContentText("[Timer End]");
        notiManager.notify(4935, builder.build());
    };
}
