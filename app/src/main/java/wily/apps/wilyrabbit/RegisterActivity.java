package wily.apps.wilyrabbit;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.Switch;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import wily.apps.wilyrabbit.database.TodoDatabase;
import wily.apps.wilyrabbit.database.WorkDatabase;
import wily.apps.wilyrabbit.entity.Todo;
import wily.apps.wilyrabbit.entity.Work;

public class RegisterActivity extends BaseActivity {
    private final int SCHEDULE_NONE = 0;
    private final int SCHEDULE_CHECK = 1;
    private final int SCHEDULE_TIMER = 2;
    private final int SCHEDULE_ALARM = 3;
    private final int SCHEDULE_ALARMTIMER = 4;

    private int SCHEDULE_TYPE = SCHEDULE_NONE;

    private FrameLayout frame;

    private EditText etScheduleTitle;
    private Switch switchScheduleActive;

    //
    private NumberPicker checkPickerGoal;
    private NumberPicker checkPickerInit;
    private NumberPicker checkPickerPer;

    private NumberPicker timerPickerGoal;
    private NumberPicker timerPickerInit;
    private NumberPicker timerPickerPer;
    //

    private Button cancelBtn;
    private Button saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout dynamicContent = findViewById(R.id.dynamic_content);
        View registerView = getLayoutInflater().inflate(R.layout.activity_register, dynamicContent, false);
        dynamicContent.addView(registerView);
        frame = (FrameLayout) findViewById(R.id.frame);

        toolbarTitle.setText(R.string.title_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RadioGroup rg = (RadioGroup)findViewById(R.id.scheduleGroup);
        rg.setOnCheckedChangeListener(radioListener);

        etScheduleTitle = findViewById(R.id.et_schedule_title);
        switchScheduleActive = findViewById(R.id.switch_schedule_active);

        cancelBtn = findViewById(R.id.btn_cancel);
        cancelBtn.setOnClickListener(btnOnClickListener);
        saveBtn = findViewById(R.id.btn_save);
        saveBtn.setOnClickListener(btnOnClickListener);
    }

    View.OnClickListener btnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn_save:
                    addWork();
                    break;
                case R.id.btn_cancel:
                    break;
            }
            finish();
        }
    };

    private void addWork(){
        WorkDatabase db = WorkDatabase.getAppDatabase(this);

        int type = SCHEDULE_TYPE;
        String title = etScheduleTitle.getText().toString();
        boolean active = switchScheduleActive.isChecked();
        int goalCost = 0;
        int initCost = 0;
        int perCost = 0;
        if(SCHEDULE_TYPE == SCHEDULE_CHECK){
            goalCost = checkPickerGoal.getValue();
            initCost = checkPickerInit.getValue();
            perCost = checkPickerPer.getValue();
        }else{
            goalCost = timerPickerGoal.getValue();
            initCost = timerPickerInit.getValue();
            perCost = timerPickerPer.getValue();
        }

        if(active){
            Intent checkIntent = new Intent(this, CheckService.class);
            checkIntent.setAction(CheckService.ACTION_REGISTER_CHECK);
            checkIntent.putExtra("type", type);
            checkIntent.putExtra("title", title);
            startService(checkIntent);
        }

        db.workDao().insert(new Work(type, title, active, goalCost, initCost, perCost)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private final int minValue = -50;
    private final int maxValue = 50;
    RadioGroup.OnCheckedChangeListener radioListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (frame.getChildCount() > 0) {
                frame.removeViewAt(0);
            }
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = null ;
            switch(checkedId){
                case R.id.scheduleCheck:
                    SCHEDULE_TYPE = SCHEDULE_CHECK;
                    view = inflater.inflate(R.layout.register_schedule_check, frame, false) ;
                    checkPickerGoal = view.findViewById(R.id.check_picker_goal);
                    checkPickerGoal.setMaxValue(100);
                    checkPickerGoal.setMinValue(0);

                    checkPickerInit = view.findViewById(R.id.check_picker_init);
                    checkPickerInit.setMaxValue(100);
                    checkPickerInit.setMinValue(0);

                    checkPickerPer = view.findViewById(R.id.check_picker_per);
                    checkPickerPer.setMinValue(0);
                    checkPickerPer.setMaxValue(maxValue - minValue);
                    checkPickerPer.setValue(0 - minValue);
                    checkPickerPer.setFormatter(new NumberPicker.Formatter() {
                        @Override
                        public String format(int index) {
                            return Integer.toString(index + minValue);
                        }
                    });
                    break;
                case R.id.scheduleTimer:
                    SCHEDULE_TYPE = SCHEDULE_TIMER;
                    view = inflater.inflate(R.layout.register_schedule_timer, frame, false) ;
                    timerPickerGoal = view.findViewById(R.id.timer_picker_goal);
                    timerPickerGoal.setMaxValue(100);
                    timerPickerGoal.setMinValue(0);
                    timerPickerInit = view.findViewById(R.id.timer_picker_init);
                    timerPickerInit.setMaxValue(100);
                    timerPickerInit.setMinValue(0);

                    timerPickerPer = view.findViewById(R.id.timer_picker_per);
                    timerPickerPer.setMinValue(0);
                    timerPickerPer.setMaxValue(maxValue - minValue);
                    timerPickerPer.setValue(0 - minValue);
                    timerPickerPer.setFormatter(new NumberPicker.Formatter() {
                        @Override
                        public String format(int index) {
                            return Integer.toString(index + minValue);
                        }
                    });
                    break;
                case R.id.scheduleAlarm:
                    SCHEDULE_TYPE = SCHEDULE_ALARM;
                    view = inflater.inflate(R.layout.register_schedule_alarm, frame, false) ;
                    break;
                case R.id.scheduleAT:
                    SCHEDULE_TYPE = SCHEDULE_ALARMTIMER;
                    view = inflater.inflate(R.layout.register_schedule_alarmtimer, frame, false) ;
                    break;
            }
            if (view != null) {
                frame.addView(view) ;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ui_setting_item:
                Intent intent = new Intent(RegisterActivity.this, UISettingActivity.class);
                startActivity(intent);
            case android.R.id.home:{
                finish();
            }
        }
        return true;
    }
}