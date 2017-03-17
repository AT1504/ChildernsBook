package com.isisruby.MasApp;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pipu on 13-Mar-17.
 */

public class NekudotZikui extends AppCompatActivity {
    private ViewPagerAdapter mSectionsPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nekudotzikui);


        viewPager = (ViewPager) findViewById(R.id.NekudotZikuiPager);
        setupViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new NekudotZikuiFragmentFirst(), "עמוד 1");
        adapter.addFragment(new NekudotZikuiFragmentSecond(), "עמוד 2");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public static class NekudotZikuiFragmentFirst extends PreferenceFragmentCompat {

        public NekudotZikuiFragmentFirst() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.nekudot_zikui_first);
        }

        @Override
        public void onCreatePreferences(Bundle bundle, String s) {

        }
    }

    public static class NekudotZikuiFragmentSecond extends PreferenceFragmentCompat {

        public NekudotZikuiFragmentSecond() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.nekudot_zikui_second);
        }

        @Override
        public void onCreatePreferences(Bundle bundle, String s) {
        }

        @Override
        public boolean onPreferenceTreeClick(Preference preference) {
            CheckBoxPreference checkBoxPreference;
            checkBoxPreference = (CheckBoxPreference) preference;

            if(preference.getKey().equals("pref_checkbox_babys") && checkBoxPreference.isChecked()) {

                // custom dialog
                final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
                dialog.setContentView(R.layout.custom_dialog_babys);
                dialog.setTitle("בגין הילדים הפעוטים");

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }

            return true;

        }
    }
}