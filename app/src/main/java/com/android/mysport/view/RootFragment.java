package com.android.mysport.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.mysport.network.MySportsAPI;
import com.android.mysport.network.MySportsAPIClient;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.Objects;

/**
 * This is the root fragment to handle all the common functions across the other three fragments
 */
public class RootFragment extends Fragment {

    protected View rootView;
    protected Activity activity;
    protected MySportsAPI mySportsAPI;
    protected FirebaseRemoteConfig firebaseRemoteConfig;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setting the remote config here to update the buttons.
        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        final FirebaseRemoteConfigSettings firebaseRemoteConfigSettings = new FirebaseRemoteConfigSettings.Builder().build();
        firebaseRemoteConfig.setConfigSettingsAsync(firebaseRemoteConfigSettings);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        mySportsAPI = Objects.requireNonNull(MySportsAPIClient.getRetrofitInstance()).create(MySportsAPI.class);

        // Hide the opened keyboard when moving across the fragments
        final InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(Objects.requireNonNull(getView()).getWindowToken(), 0);
    }

    /**
     * Show toast messages
     *
     * @param message toast message String
     */
    protected void showToast(@NonNull final String message) {
        Toast.makeText(activity,
                message,
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Show Toast depending on the error from the service
     *
     * @param throwable the error throwable
     */
    protected void throwError(@NonNull final Throwable throwable) {
        showToast(Objects.requireNonNull(throwable.getMessage()));
    }

}
