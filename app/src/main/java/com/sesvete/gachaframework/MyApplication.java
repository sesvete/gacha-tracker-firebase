package com.sesvete.gachaframework;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        applySavedDarkMode();
    }


    // naredimo custom aplication, da lahko shranjujemo custom variable
    // v našem primeru dark mode
    // https://stackoverflow.com/questions/18002227/why-extend-the-android-application-class
    private void applySavedDarkMode() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isDarkMode = sharedPreferences.getBoolean("dark_mode", false); // "sync" is your key

        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
