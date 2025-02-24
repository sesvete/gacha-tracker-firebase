package com.sesvete.gachaframework;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.sesvete.gachaframework.fragment.CounterFragment;
import com.google.android.material.navigation.NavigationView;
import com.sesvete.gachaframework.fragment.HistoryFragment;
import com.sesvete.gachaframework.fragment.SettingsFragment;
import com.sesvete.gachaframework.fragment.StatsFragment;
import com.sesvete.gachaframework.helper.SettingsHelper;

//TODO: light/night mode
//TODO: translations ENG/SLO
//TODO: sign in activity
//TODO: loading/splash screen

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        if (savedInstanceState == null){
            toolbar.setTitle(R.string.counter);
        }
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        fragmentManager = getSupportFragmentManager();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            fragmentManager.beginTransaction().replace(R.id.fragment_container, CounterFragment.class, null).setReorderingAllowed(true).commit();
            navigationView.setCheckedItem(R.id.nav_counter);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_counter){
                    toolbar.setTitle(R.string.counter);
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, CounterFragment.class, null).setReorderingAllowed(true).commit();
                }
                else if (id == R.id.nav_history){
                    toolbar.setTitle(R.string.history);
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, HistoryFragment.class, null).setReorderingAllowed(true).commit();
                }
                else if (id == R.id.nav_statistics){
                    toolbar.setTitle(R.string.statistics);
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, StatsFragment.class, null).setReorderingAllowed(true).commit();
                }
                else if (id == R.id.nav_settings){
                    toolbar.setTitle(R.string.settings);
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, SettingsFragment.class, null).setReorderingAllowed(true).commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        updateNavHeaderUser(navigationView);
        updateNavHeader(navigationView);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void updateNavHeaderUser(NavigationView navigationView){
        if (navigationView != null) {
            View navHeaderView = navigationView.getHeaderView(0);
            TextView txtNavHeaderUserName = navHeaderView.findViewById(R.id.txtNavHeaderUserName);
            txtNavHeaderUserName.setText("Simon Svetec");
        }
    }

    public void updateNavHeader(NavigationView navigationView){
        if (navigationView != null) {
            SettingsHelper settingsHelper = new SettingsHelper();
            View navHeaderView = navigationView.getHeaderView(0);
            TextView txtNavHeaderGame = navHeaderView.findViewById(R.id.txtNavHeaderGame);
            TextView txtNavHeaderBanner = navHeaderView.findViewById(R.id.txtNavHeaderBanner);

            txtNavHeaderGame.setText(settingsHelper.getEntryFromValue(this, "game", "genshin"));
            txtNavHeaderBanner.setText(settingsHelper.getEntryFromValue(this, "banner", "limited"));
        }
    }
}