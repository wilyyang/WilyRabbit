package wily.apps.wilyrabbit;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.core.app.NotificationCompat;

public class Alarm extends BroadcastReceiver {
    String CHANNEL_ID = "Wily Rabbit";
    String CHANNEL_NAME = "Wily Rabbit";
    String CHANNEL_DESCRIPT = "Wily Rabbit is good habbit";

    @Override
    public void onReceive(Context context, Intent intent) {
        TimerAsyncTask task = new TimerAsyncTask(context);
        task.execute();

    }

    private class TimerAsyncTask extends AsyncTask<Void, Integer, Integer> {
        private Context mContext;
        private NotificationManager notificationManager;
        private NotificationCompat.Builder builder;
        public TimerAsyncTask(Context context) {
            super();
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            builder = new NotificationCompat.Builder(mContext, CHANNEL_ID).setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(CHANNEL_NAME).setContentText("[Timer Start]").setAutoCancel(false)
                    .setProgress(100,0, false);

            notificationManager.notify(4935, builder.build());
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            for(int count = 0; count<100; ++count){
                publishProgress(count);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            builder.setProgress(100, values[0], false).setContentText("["+values[0]+"]");
            notificationManager.notify(4935, builder.build());
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            builder.setProgress(0, 0, false).setContentText("[Timer End]");
            notificationManager.notify(4935, builder.build());
        }
    }
}
