package wily.apps.wilyrabbit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

public class RegisterActivity extends BaseActivity {

    private FrameLayout frame;
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
    }

    int minValue = -50;
    int maxValue = 50;
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
                    view = inflater.inflate(R.layout.register_schedule_check, frame, false) ;
                    NumberPicker checkPickerGoal = view.findViewById(R.id.check_picker_goal);
                    checkPickerGoal.setMaxValue(100);
                    checkPickerGoal.setMinValue(0);
                    NumberPicker checkPickerInit = view.findViewById(R.id.check_picker_init);
                    checkPickerInit.setMaxValue(100);
                    checkPickerInit.setMinValue(0);

                    minValue = -50;
                    maxValue = 50;
                    NumberPicker checkPickerPer = view.findViewById(R.id.check_picker_per);
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
                    view = inflater.inflate(R.layout.register_schedule_timer, frame, false) ;
                    NumberPicker timerPickerGoal = view.findViewById(R.id.timer_picker_goal);
                    timerPickerGoal.setMaxValue(100);
                    timerPickerGoal.setMinValue(0);
                    NumberPicker timerPickerInit = view.findViewById(R.id.timer_picker_init);
                    timerPickerInit.setMaxValue(100);
                    timerPickerInit.setMinValue(0);

                    minValue = -50;
                    maxValue = 50;
                    NumberPicker timerPickerPer = view.findViewById(R.id.timer_picker_per);
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
                    view = inflater.inflate(R.layout.register_schedule_alarm, frame, false) ;
                    break;
                case R.id.scheduleAT:
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