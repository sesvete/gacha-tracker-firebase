package com.sesvete.gachatrackerfirebase;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.credentials.CredentialManager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sesvete.gachatrackerfirebase.fragment.CounterFragment;
import com.google.android.material.navigation.NavigationView;
import com.sesvete.gachatrackerfirebase.fragment.HistoryFragment;
import com.sesvete.gachatrackerfirebase.fragment.SettingsFragment;
import com.sesvete.gachatrackerfirebase.fragment.StatsFragment;
import com.sesvete.gachatrackerfirebase.helper.AuthenticationHelper;
import com.sesvete.gachatrackerfirebase.helper.DialogHelper;
import com.sesvete.gachatrackerfirebase.helper.LocaleHelper;
import com.sesvete.gachatrackerfirebase.helper.SettingsHelper;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private FragmentManager fragmentManager;
    private FirebaseAuth mAuth;
    private CredentialManager credentialManager;
    private FirebaseUser currentUser;
    private String uid;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        credentialManager = CredentialManager.create(getBaseContext());
        currentUser = mAuth.getCurrentUser();
        uid = mAuth.getUid();

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
                } else if (id == R.id.nav_logout) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    LayoutInflater dialogInflater = getLayoutInflater();
                    View dialogView = dialogInflater.inflate(R.layout.logout_dialog, null);
                    builder.setView(dialogView);
                    AlertDialog dialog = builder.create();

                    DialogHelper.buildAlertDialogWindow(dialog, MainActivity.this, MainActivity.this);

                    MaterialButton btnLogoutCancel = dialogView.findViewById(R.id.btn_logout_cancel);
                    MaterialButton btnLogoutConfirm = dialogView.findViewById(R.id.btn_logout_confirm);

                    btnLogoutCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    btnLogoutConfirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // tu bo seveda najprej prišlo do odjave
                            dialog.dismiss();
                            long timerLogoutStart = System.nanoTime();
                            AuthenticationHelper.logOut(mAuth, credentialManager, MainActivity.this, timerLogoutStart);
                        }
                    });
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        updateNavHeaderUser(navigationView);
        updateNavHeader(navigationView);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    finishAffinity();
                }
            }
        });

    }

    private void updateNavHeaderUser(NavigationView navigationView){
        if (navigationView != null) {
            View navHeaderView = navigationView.getHeaderView(0);
            TextView txtNavHeaderUserName = navHeaderView.findViewById(R.id.txt_nav_header_user_name);
            // nav header user se bo pobral iz podatkovne baze
            String userName = currentUser.getEmail();
            txtNavHeaderUserName.setText(userName);
        }
    }

    public void updateNavHeader(NavigationView navigationView){
        if (navigationView != null) {
            View navHeaderView = navigationView.getHeaderView(0);
            TextView txtNavHeaderGame = navHeaderView.findViewById(R.id.txt_nav_header_game);
            TextView txtNavHeaderBanner = navHeaderView.findViewById(R.id.txt_nav_header_banner);

            txtNavHeaderGame.setText(SettingsHelper.getEntryFromValue(this, "game", "genshin_impact"));
            txtNavHeaderBanner.setText(SettingsHelper.getEntryFromValue(this, "banner", "limited"));
        }
    }
}