package demo.zjj.wellingtonnews.activities;


import android.app.Activity;
import android.os.Bundle;

import demo.zjj.wellingtonnews.fragments.SettingsFragment;


public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, SettingsFragment.newInstance()).commit();


    }
}
