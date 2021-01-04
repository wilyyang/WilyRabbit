package wily.apps.wilyrabbit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Alarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Alarm", "Alarm Test ...");
        Toast.makeText(context, "Alarm Test ...", Toast.LENGTH_SHORT).show();
    }
}
