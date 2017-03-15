package com.isisruby.MasApp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Pipu on 03-Mar-17.
 */

public class AboutActivity extends AppCompatActivity{
    private CustomPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new CustomPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        }

    public class CustomPagerAdapter extends FragmentPagerAdapter {

        private int NUM_ITEMS = 2;

        public CustomPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 1 - This will show AboutFirstFragment
                    return AboutFirstFragment.newInstance(1, "Page # 1");
                case 1: // Fragment # 2 - This will show AboutSecondFragment
                    return AboutSecondFragment.newInstance(2, "Page # 2");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + String.valueOf(position+1);
        }
    }
}

