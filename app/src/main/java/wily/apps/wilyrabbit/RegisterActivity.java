package wily.apps.wilyrabbit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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

    RadioGroup.OnCheckedChangeListener radioListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            frame.removeViewAt(0);
            switch(checkedId){
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