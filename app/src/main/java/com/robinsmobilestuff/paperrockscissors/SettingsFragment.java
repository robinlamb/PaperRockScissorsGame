package com.robinsmobilestuff.paperrockscissors;


import android.os.Bundle;

import android.preference.PreferenceFragment;

/**
 * Created by Robin on 11/17/2017.
 */

public class SettingsFragment extends PreferenceFragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
