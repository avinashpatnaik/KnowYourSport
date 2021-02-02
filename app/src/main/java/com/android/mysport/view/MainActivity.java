package com.android.mysport.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.mysport.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Objects;

/**
 * This is the root activity for managing the fragment transactions.
 */
public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_main);
        MobileAds.initialize(this);

        //To check active internet on the device
        checkInternetAvailability();

        // Get the FirebaseAnalytics instance.
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_schedule:
                    final SportsDataFragment sportsDataFragment = SportsDataFragment.getInstance();
                    replaceFragment(sportsDataFragment);
                    final Bundle sportsData = new Bundle();
                    sportsData.putString(FirebaseAnalytics.Param.CONTENT, "SportsDataFragment");
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, sportsData);
                    break;
                case R.id.action_search:
                    final SearchFragment searchFragment = SearchFragment.getInstance();
                    replaceFragment(searchFragment);
                    final Bundle search = new Bundle();
                    search.putString(FirebaseAnalytics.Param.CONTENT, "SearchFragment");
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, search);
                    break;
                case R.id.upcoming_matches:
                    final UpcomingMatchesFragment upcomingMatchesFragment = UpcomingMatchesFragment.getInstance();
                    replaceFragment(upcomingMatchesFragment);
                    final Bundle upcomingmatches = new Bundle();
                    upcomingmatches.putString(FirebaseAnalytics.Param.CONTENT, "UpcomingMatchesFragment");
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, upcomingmatches);
                    break;
            }
            return true;
        });
        bottomNavigationView.setSelectedItemId(R.id.action_schedule);

        // Load the AdView
        final AdView adView = (AdView) findViewById(R.id.adView);
        final AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }

    /**
     * Replace fragment
     *
     * @param fragment instance of root fragment
     */
    private void replaceFragment(@NonNull final Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }


    //To check active internet connection
    public void checkInternetAvailability() {
        if (!isInternetAvailable()) {
            final Intent intent = new Intent(MainActivity.this, NoNetwork.class);
            startActivity(intent);
            finish();
        }
    }

    public boolean isInternetAvailable() {
        try {
            final ConnectivityManager connectivityManager
                    = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (Exception e) {
            Log.e("isInternetAvailable:", e.toString());
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}