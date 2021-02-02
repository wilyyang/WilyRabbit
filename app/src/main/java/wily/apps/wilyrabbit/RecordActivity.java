package wily.apps.wilyrabbit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import wily.apps.wilyrabbit.database.RecordDatabase;
import wily.apps.wilyrabbit.database.TodoDatabase;

public class RecordActivity extends BaseActivity{
    TextView recordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout dynamicContent = findViewById(R.id.dynamic_content);
        View recordView = getLayoutInflater().inflate(R.layout.activity_record, dynamicContent, false);
        dynamicContent.addView(recordView);

        toolbarTitle.setText(R.string.title_record);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recordText = findViewById(R.id.recordText);
        loadRecords();
    }

    private void loadRecords(){
        RecordDatabase db = RecordDatabase.getAppDatabase(this);
        db.recordDao().getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    recordText.setText(item.toString());
                });
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
