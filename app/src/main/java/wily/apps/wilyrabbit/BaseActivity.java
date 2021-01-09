package wily.apps.wilyrabbit;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BaseActivity extends AppCompatActivity {
    protected Toolbar toolbar;
    protected TextView toolbarTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        toolbarInit();
    }

    private void toolbarInit(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbarTextView = findViewById(R.id.action_bar_title);
    }
}