package wily.apps.wilyrabbit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        toolbarTextView = findViewById(R.id.action_bar_title);
        toolbarInit();
        fragmentInit();
    }

    private void toolbarInit(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
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
                        toolbarTextView.setText(R.string.menu_bottom_home_tab);
                        return true;

                    case R.id.work_tab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, workFragment).commit();
                        toolbarTextView.setText(R.string.menu_bottom_work_tab);
                        return true;

                    case R.id.objective_tab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, objectiveFragment).commit();
                        toolbarTextView.setText(R.string.menu_bottom_objective_tab);
                        return true;
                }
                return false;
            }
        });
        bottomNavigation.setSelectedItemId(R.id.home_tab);
    }
}