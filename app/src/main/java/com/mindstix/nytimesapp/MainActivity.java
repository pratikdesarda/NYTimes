package com.mindstix.nytimesapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.mindstix.nytimesapp.books.BestSellerListFragment;
import com.mindstix.nytimesapp.common.Commons;
import com.mindstix.nytimesapp.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private String CLASS_NAME = getClass().getName() + " ";

    private ProgressDialog progressDialog;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        startHomeFragment();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                        startHomeFragment();
                    return true;
                case R.id.navigation_books:
                        startBestSellerListFragment();
                    return true;
            }
            return false;
        }

    };

    /**
     * Function to show progress dialog.
     */
    public void showProgressDialog(boolean toShow, String message) {
        if (toShow) {
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
            progressDialog.show();

            return;
        }

        progressDialog.dismiss();
    }

    private void startHomeFragment() {

        Log.d(Commons.LOG_TAG, CLASS_NAME + "In startHomeFragment");

        fragmentManager = getFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, homeFragment);
        fragmentTransaction.addToBackStack(HomeFragment.class.getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void startBestSellerListFragment() {

        Log.d(Commons.LOG_TAG, CLASS_NAME + "In startBestSellerListFragment");

        fragmentManager = getFragmentManager();
        BestSellerListFragment bestSellerListFragment = new BestSellerListFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, bestSellerListFragment);
        fragmentTransaction.addToBackStack(HomeFragment.class.getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        Log.d(Commons.LOG_TAG, CLASS_NAME + " In onBackPressed");
        final FragmentManager fragmentManager = getFragmentManager();
        int currentBackStackIndex = fragmentManager.getBackStackEntryCount() - 1;
        String currentTag = fragmentManager.getBackStackEntryAt(currentBackStackIndex).getName();
        Log.d(Commons.LOG_TAG, CLASS_NAME + " Current Fragment Tag - " + currentTag);
        Log.d(Commons.LOG_TAG, CLASS_NAME + " In onBackPressed : fragmentManager.getBackStackEntryCount = " + fragmentManager.getBackStackEntryCount());
        if (currentTag.equalsIgnoreCase(HomeFragment.class.getSimpleName()) ||
                currentTag.equalsIgnoreCase(BestSellerListFragment.class.getSimpleName())) {
            finish();
        }
        // If no Fragment found in stack, finish app.
        if (fragmentManager.getBackStackEntryCount() == 0) {
            finish();
        } else {
            fragmentManager.popBackStack();
        }
    }
}
