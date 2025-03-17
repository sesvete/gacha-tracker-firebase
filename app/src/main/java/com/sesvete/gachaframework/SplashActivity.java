package com.sesvete.gachaframework;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

//TODO: to se bo še posodobilo
// glede na to če je upoarbnik pirjavljen ali ne se bo prešlo bodisi v sign in screen ali ali main activity

public class SplashActivity extends AppCompatActivity {

    // to lahko še prilagodiš glede na potrebe
    private static final long SPLASH_DISPLAY_LENGTH = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // to se bo še prilagodilo
                Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}