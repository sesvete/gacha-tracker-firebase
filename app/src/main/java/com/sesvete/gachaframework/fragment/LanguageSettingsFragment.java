package com.sesvete.gachaframework.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.sesvete.gachaframework.R;

public class LanguageSettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.language_preferences, rootKey);
    }

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
