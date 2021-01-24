package wily.apps.wilyrabbit.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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
                db.workDao().insert(new Work(titleEt.getText().toString(), hour, minute)).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();

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
