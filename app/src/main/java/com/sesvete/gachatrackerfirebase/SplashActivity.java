package com.sesvete.gachatrackerfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Intent intent;
    // to lahko še prilagodiš glede na potrebe
    private static final long SPLASH_DISPLAY_LENGTH = 1000; // 1 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentUser == null) {
                    intent = new Intent(SplashActivity.this, SignInActivity.class);
                } else {
                    long timerAutoLoginStart = System.nanoTime();
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.putExtra("timerStart", timerAutoLoginStart);
                }
                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}