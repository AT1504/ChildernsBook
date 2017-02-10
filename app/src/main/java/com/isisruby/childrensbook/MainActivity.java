package com.isisruby.childrensbook;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity {
    Fragment mCurrentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            AlphabetListFragment firstFragment = new AlphabetListFragment();
            OutputFragment outputFragment = new OutputFragment();

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            // Add the fragment to the 'fragment_container' FrameLayout
            transaction.add(R.id.fragment_container, firstFragment).commit();
            //transaction.add(R.id.fragment_container, outputFragment).commit();
            mCurrentFragment = firstFragment;

        }
    }
    private void ShowFragment(Fragment fragment, FragmentTransaction transaction){
        //transaction.replace(R.id.fragment_container, fragment);
        transaction.hide(mCurrentFragment);
        transaction.show(fragment);
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
        mCurrentFragment = fragment;
    }


}

