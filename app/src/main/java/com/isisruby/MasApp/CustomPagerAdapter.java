package com.isisruby.MasApp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Pipu on 11-Feb-17.
 */
public class CustomPagerAdapter extends FragmentPagerAdapter {

        private static int NUM_ITEMS = 2;

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
