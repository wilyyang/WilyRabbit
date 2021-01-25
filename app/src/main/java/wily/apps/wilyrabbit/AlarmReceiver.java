package wily.apps.wilyrabbit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    public static int NOTIFICATION_ID = 3;
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent checkServiceIntent = new Intent(context, CheckService.class);
        checkServiceIntent.setAction(CheckService.ACTION_ALARM);
        context.startService(checkServiceIntent);
    }
}
