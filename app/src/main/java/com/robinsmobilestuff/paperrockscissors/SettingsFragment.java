//Robin Lamb
//Paper Rock Scissors
//File for the Preference Fragment

package com.robinsmobilestuff.paperrockscissors;


import android.os.Bundle;

import android.preference.PreferenceFragment;


public class SettingsFragment extends PreferenceFragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
