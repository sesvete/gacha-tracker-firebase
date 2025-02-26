package com.sesvete.gachaframework.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceManager;

import com.sesvete.gachaframework.R;

public class SettingsHelper {

    public static String getEntryFromValue(Context context, String key, String defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String value = prefs.getString(key, defaultValue);

        Resources res = context.getResources();
        String[] entryValues = res.getStringArray(getEntryValuesResourceId(context, key));
        String[] entries = res.getStringArray(getEntriesResourceId(context, key));

        if (entryValues != null && entries != null && entryValues.length == entries.length) {
            for (int i = 0; i < entryValues.length; i++) {
                if (entryValues[i].equals(value)) {
                    return entries[i];
                }
            }
        }
        return defaultValue; // Return default if not found
    }

    private static int getEntriesResourceId(Context context, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if(key.equals("game")){
            return R.array.game_entries;
        } else if (key.equals("banner")){
            String game = prefs.getString("game", "genshin");
            if (game.equals("genshin")){
                return R.array.genshin_banner_entries;
            } else if (game.equals("hsr")) {
                return R.array.hsr_banner_entries;
            } else if (game.equals("zzz")) {
                return R.array.zzz_banner_entries;
            } else {
                return R.array.banner_entries;
            }
        } else if (key.equals("language")){
            return R.array.language_entries;
        } else {
            return 0;
        }
    }

    private static int getEntryValuesResourceId(Context context, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if(key.equals("game")){
            return R.array.game_values;
        } else if (key.equals("banner")){
            String game = prefs.getString("game", "genshin");
            if (game.equals("genshin")){
                return R.array.genshin_banner_values;
            } else if (game.equals("hsr")) {
                return R.array.hsr_banner_values;
            } else if (game.equals("zzz")) {
                return R.array.zzz_banner_values;
            } else {
                return R.array.banner_values;
            }
        } else if (key.equals("language")){
            return R.array.language_values;
        } else {
            return 0;
        }
    }

    public static void updateBannerPreference(String gameValue, ListPreference bannerPreference) {
        int entriesResId;
        int valuesResId;

        switch (gameValue) {
            case "genshin":
                entriesResId = R.array.genshin_banner_entries;
                valuesResId = R.array.genshin_banner_values;
                break;
            case "hsr":
                entriesResId = R.array.hsr_banner_entries;
                valuesResId = R.array.hsr_banner_values;
                break;
            case "zzz":
                entriesResId = R.array.zzz_banner_entries;
                valuesResId = R.array.zzz_banner_values;
                break;
            default:
                entriesResId = R.array.banner_entries; // Default
                valuesResId = R.array.banner_values;
                break;
        }
        bannerPreference.setEntries(entriesResId);
        bannerPreference.setEntryValues(valuesResId);
    }

}
