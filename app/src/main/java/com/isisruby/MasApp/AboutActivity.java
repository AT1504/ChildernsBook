package com.isisruby.MasApp;

import android.os.Bundle;
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
}
