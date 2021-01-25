package wily.apps.wilyrabbit.util;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import wily.apps.wilyrabbit.AlarmReceiver;
import wily.apps.wilyrabbit.R;
import wily.apps.wilyrabbit.database.WorkDatabase;
import wily.apps.wilyrabbit.entity.Work;

public class WorkDialog {
    private Context context;
    public WorkDialog(Context context) {
        this.context = context;
    }
    private EditText titleEt;
    private TimePicker alarmPicker;

    public void callFunction() {
        View mDialogView = LayoutInflater.from(context).inflate(R.layout.work_dialog, null);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context)
                .setView(mDialogView);

        titleEt = mDialogView.findViewById(R.id.dialogTitleEt);
        alarmPicker = mDialogView.findViewById(R.id.alarmPicker);

        mBuilder.setPositiveButton("저장", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int hour = alarmPicker.getCurrentHour();
                int minute = alarmPicker.getCurrentMinute();

                WorkDatabase db = WorkDatabase.getAppDatabase(context);
                db.workDao().insert(new Work(titleEt.getText().toString(), hour, minute))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();

                Calendar mCalendar = Calendar.getInstance();
                mCalendar.set(Calendar.HOUR_OF_DAY, hour);
                mCalendar.set(Calendar.MINUTE, minute);
                mCalendar.set(Calendar.SECOND, 0);

                int requestCode = 33;
                Intent alarmIntent = new Intent(context, AlarmReceiver.class);
                PendingIntent pAlarmIntent = PendingIntent.getBroadcast(context, AlarmReceiver.NOTIFICATION_ID, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmmanager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                alarmmanager.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pAlarmIntent);

                dialog.dismiss();
            }
        });
        mBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        mBuilder.show();
    }
}
