package com.sesvete.gachaframework.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import androidx.preference.PreferenceManager;

import com.sesvete.gachaframework.R;

public class SettingsHelper {

    public String getEntryFromValue(Context context, String key, String defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String value = prefs.getString(key, defaultValue);

        Resources res = context.getResources();
        String[] entryValues = res.getStringArray(getEntryValuesResourceId(key));
        String[] entries = res.getStringArray(getEntriesResourceId(key));

        if (entryValues != null && entries != null && entryValues.length == entries.length) {
            for (int i = 0; i < entryValues.length; i++) {
                if (entryValues[i].equals(value)) {
                    return entries[i];
                }
            }
        }
        return defaultValue; // Return default if not found
    }

    private int getEntriesResourceId(String key){
        if(key.equals("game")){
            return R.array.game_entries;
        } else if (key.equals("banner")){
            return R.array.banner_entries;
        } else if (key.equals("language")){
            return R.array.language_entries;
        } else {
            return 0;
        }
    }

    private static int getEntryValuesResourceId(String key){
        if(key.equals("game")){
            return R.array.game_values;
        } else if (key.equals("banner")){
            return R.array.banner_values;
        } else if (key.equals("language")){
            return R.array.language_values;
        } else {
            return 0;
        }
    }

}
