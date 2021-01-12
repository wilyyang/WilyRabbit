package wily.apps.wilyrabbit;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import wily.apps.wilyrabbit.fragment.HomeFragment;
import wily.apps.wilyrabbit.fragment.ObjectiveFragment;
import wily.apps.wilyrabbit.fragment.WorkFragment;

public class MainActivity extends BaseActivity {

    HomeFragment homeFragment;
    WorkFragment workFragment;
    ObjectiveFragment objectiveFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout dynamicContent = findViewById(R.id.dynamic_content);
        View mainView = getLayoutInflater().inflate(R.layout.activity_main, dynamicContent, false);
        dynamicContent.addView(mainView);

        fragmentInit();
    }

    private void fragmentInit(){
        homeFragment = new HomeFragment();
        workFragment = new WorkFragment();
        objectiveFragment = new ObjectiveFragment();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_menu);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_tab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        toolbarTitle.setText(R.string.title_home);
                        return true;

                    case R.id.work_tab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, workFragment).commit();
                        toolbarTitle.setText(R.string.title_work);
                        return true;

                    case R.id.objective_tab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, objectiveFragment).commit();
                        toolbarTitle.setText(R.string.title_objective);
                        return true;
                }
                return false;
            }
        });
        bottomNavigation.setSelectedItemId(R.id.home_tab);
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
                Intent intent = new Intent(MainActivity.this, UISettingActivity.class);
                startActivity(intent);
        }
        return true;
    }


    // test code

}