package com.sesvete.gachaframework.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.sesvete.gachaframework.MainActivity;
import com.sesvete.gachaframework.R;
import com.sesvete.gachaframework.helper.SettingsHelper;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    private ListPreference bannerPreference;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        bannerPreference = findPreference("banner");

        SettingsHelper.updateBannerPreference(PreferenceManager.getDefaultSharedPreferences(getContext()).getString("game", "genshin"), bannerPreference);
    }

    // register and unregister the settings fragment as a listener
    @Override
    public void onResume() {
        super.onResume();
        PreferenceManager.getDefaultSharedPreferences(getContext()).registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        PreferenceManager.getDefaultSharedPreferences(getContext()).unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, @Nullable String key) {
        if (key.equals("game")) {
            SettingsHelper.updateBannerPreference(sharedPreferences.getString(key, "genshin"), findPreference("banner"));
        }
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.updateNavHeader(mainActivity.findViewById(R.id.nav_view));
        }
    }
}