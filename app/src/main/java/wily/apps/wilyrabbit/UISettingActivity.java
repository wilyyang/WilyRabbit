package wily.apps.wilyrabbit;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class UISettingActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout dynamicContent = findViewById(R.id.dynamic_content);
        View recordView = getLayoutInflater().inflate(R.layout.activity_ui_setting, dynamicContent, false);
        dynamicContent.addView(recordView);

        toolbarTitle.setText(R.string.title_ui_setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:{
                finish();
            }
        }
        return true;
    }
}
