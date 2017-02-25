package com.isisruby.childrensbook;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Pipu on 11-Feb-17.
 */
public class DisplayHelper {

    public static void replaceFragment(Context context, Fragment newfragment, int mContainerId) {
        FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        fragmentTransaction.replace(mContainerId, newfragment, newfragment.toString());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
