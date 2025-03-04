package com.sesvete.gachaframework.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
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
            bannerPreference.setValueIndex(0);
        }
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.updateNavHeader(mainActivity.findViewById(R.id.nav_view));
        }
    }

    // custom alert dialog builder za settingse
    @Override
    public void onDisplayPreferenceDialog(@NonNull Preference preference) {
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext(), R.style.RoundedDialog); // Apply your theme

            // za sestavo radio buttona
            // list Preference.get entries je char sequence zato moramo njapre to pretvorit v String []

            CharSequence[] entriesCharSequence = listPreference.getEntries();
            CharSequence[] entryValuesCharSequence = listPreference.getEntryValues();

            String[] entries = new String[entriesCharSequence.length];
            String[] entryValues = new String[entryValuesCharSequence.length];

            for (int i = 0; i < entriesCharSequence.length; i++) {
                entries[i] = entriesCharSequence[i].toString();
                entryValues[i] = entryValuesCharSequence[i].toString();
            }

            String currentValue = listPreference.getValue();

            int checkedItem = -1; // default - ni izbrane možnosti
            for (int i = 0; i < entryValues.length; i++) {
                if (entryValues[i].equals(currentValue)) {
                    checkedItem = i;
                    break;
                }
            }
            // dejansko sestavi window
            builder.setTitle(listPreference.getTitle())
                    .setSingleChoiceItems(entries, checkedItem, (dialog, which) -> {
                        listPreference.setValue(entryValues[which]);
                        dialog.dismiss();
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .show();
        } else {
            super.onDisplayPreferenceDialog(preference); // Use the default dialog for other preferences
        }
    }

}