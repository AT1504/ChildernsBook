package com.isisruby.MasApp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceFragmentCompat;

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
    }
}