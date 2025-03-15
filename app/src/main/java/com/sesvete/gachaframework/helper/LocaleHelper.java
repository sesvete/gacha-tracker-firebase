package com.sesvete.gachaframework.helper;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import androidx.preference.PreferenceManager;

import java.util.Locale;

public class LocaleHelper {

    public static Context setLocaleFromPreferences(Context context) {
        String language = PreferenceManager.getDefaultSharedPreferences(context).getString("language", "en");
        return updateLocale(context, language);
    }

    public static Context updateLocale(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration config = new Configuration(resources.getConfiguration());
        config.setLocale(locale);
        context = context.createConfigurationContext(config);

        return context;
    }

    public static Context onAttach(Context context) {
        return setLocaleFromPreferences(context);
    }

    public static Context onAttachBaseWithDefault(Context context, String defaultLanguage) {
        String language = PreferenceManager.getDefaultSharedPreferences(context).getString("language", defaultLanguage);
        return updateLocale(context, language);
    }
}

/*
updateLocaleFromPrefs(Context context):

    This method now specifically indicates that it retrieves the locale from SharedPreferences before calling updateLocale.

updateLocale(Context context, String language):

    This method remains the core locale update method, taking a language code directly.

onAttachBase(Context context):

    This method now uses updateLocaleFromPrefs to prevent redundant code.

onAttachBaseWithDefault(Context context, String defaultLanguage):

    This method now has a distinct name, clearly showing that it uses a default language parameter.
 */

/*
You've encountered a common issue when setting locales in Android. The problem arises because Android's system locale can sometimes override your application's locale settings, especially during the initial launch or when the application is restored from the background. Here's a breakdown of the problem and how to fix it:

Problem:

    System Locale Priority: Android prioritizes the system locale (device language) over your application's locale settings.
    Initialization Timing: Even though you're setting the locale in your Application class, the system might have already initialized resources based on the system locale before your code runs.
    Configuration Updates: Sometimes, the system configuration might not be fully updated when you set the locale, leading to inconsistencies.

Solution:

To ensure your application always uses the selected language, you need to:

    Force Configuration Update: Force the system to update the configuration with your desired locale.
    Use createConfigurationContext (API 17+) or attachBaseContext: This is the most reliable way to enforce the locale before the Activity is created.

Implementation:

    Update LocaleHelper:

Java

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import java.util.Locale;

public class LocaleHelper {

    public static Context setLocale(Context context) {
        String language = PreferenceManager.getDefaultSharedPreferences(context).getString("language", "en");
        return setLocale(context, language);
    }

    public static Context setLocale(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration config = new Configuration(resources.getConfiguration());
        config.setLocale(locale);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            context = context.createConfigurationContext(config);
        } else {
            resources.updateConfiguration(config, resources.getDisplayMetrics());
        }
        return context;
    }

    public static Context onAttach(Context context) {
        return setLocale(context);
    }

    public static Context onAttach(Context context, String defaultLanguage) {
        String language = PreferenceManager.getDefaultSharedPreferences(context).getString("language", defaultLanguage);
        return setLocale(context, language);
    }
}

    Update MyApplication:

Java

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LocaleHelper.onAttach(this);
    }
}

    Update MainActivity:

Java

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ... rest of your onCreate() code
    }
}

Explanation of Changes:

    LocaleHelper.onAttach():
        This method is now used to set the locale and return a context with the updated configuration.
        It handles API level differences using createConfigurationContext (API 17+) for better compatibility.
    MainActivity.attachBaseContext():
        By overriding attachBaseContext(), you ensure that the locale is set before the activity is created.
        This is the most effective way to guarantee the correct locale is used from the start.
    MyApplication:
        The application class now calls LocaleHelper.onAttach() during creation.

Key Improvements:

    Robust Locale Setting: The code now handles locale setting in a more robust and reliable way, especially on different Android versions.
    Early Initialization: The locale is set at the earliest possible point in the activity lifecycle.
    Avoids System Overrides: The use of attachBaseContext() and createConfigurationContext() helps prevent the system locale from overriding your application's locale.

By implementing these changes, your application should consistently use the selected language, even on initial launch or when restored from the background.
 */
