package wily.apps.wilyrabbit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class RecordActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout dynamicContent = findViewById(R.id.dynamic_content);
        View recordView = getLayoutInflater().inflate(R.layout.activity_record, dynamicContent, false);
        dynamicContent.addView(recordView);

        toolbarTitle.setText(R.string.title_record);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

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
                Intent intent = new Intent(RecordActivity.this, UISettingActivity.class);
                startActivity(intent);
            case android.R.id.home:{
                finish();
            }
        }
        return true;
    }
}
