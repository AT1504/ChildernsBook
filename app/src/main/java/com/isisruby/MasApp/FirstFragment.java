package com.isisruby.MasApp;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by Pipu on 13-Mar-17.
 */

public class FirstFragment extends PreferenceFragmentCompat {

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

    }
}
