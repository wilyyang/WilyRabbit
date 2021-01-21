package wily.apps.wilyrabbit;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;


import java.text.SimpleDateFormat;
import java.util.Date;

import wily.apps.wilyrabbit.util.NotificationUtil;

public class CheckService extends Service {
    private Context mContext;

    public static final String ACTION_SERVICE_INIT = "ACTION_SERVICE_INIT";
    public static final String ACTION_SERVICE_STOP = "ACTION_SERVICE_STOP";
    public static final String ACTION_CHECK = "ACTION_CHECK";

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
            }
        }
        return START_STICKY;
    }

    // ACTION_CHECK
    private int count = 0;
    private void checkTest(){
        ++count;
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String formatDate = sdfNow.format(date);

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
