package com.android.mysport.view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.android.mysport.R;
import com.android.mysport.model.TeamDetails;
import com.android.mysport.utils.Utils;
import com.android.mysport.viewmodel.SearchDataViewModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SearchFragment extends RootFragment {

    private SearchDataViewModel searchDataViewModel;
    private TextView teamName;
    private TextView leagueName;
    private View teamLogo;
    private View teamCardDetails;
    private View searchAnimIcon;
    private TextView foundedInYear;
    private TextView shortInfo;
    private String instaUrl;
    private String twitterUrl;
    private ImageView instaIcon;
    private ImageView twitterIcon;
    private TextView noEventFound;

    // Getting the fragment instance
    public static SearchFragment getInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_search, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViewModel();
        initUILayer();

        // Can change the visibility of the insta and the twitter button via Remote Config
        final Map<String, Object> defaultValues = new HashMap<>();
        defaultValues.put("insta_btn_enable", false);
        defaultValues.put("twitter_btn_enable", false);
        firebaseRemoteConfig.setDefaultsAsync(defaultValues);

        firebaseRemoteConfig.fetch(0).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                firebaseRemoteConfig.fetchAndActivate();

                if (!firebaseRemoteConfig.getBoolean("insta_btn_enable")) {
                    instaIcon.setVisibility(View.GONE);
                }

                if (!firebaseRemoteConfig.getBoolean("twitter_btn_enable")) {
                    twitterIcon.setVisibility(View.VISIBLE);
                }

            } else {
                showToast("Something went wrong");
            }
        });

        if (searchDataViewModel != null) {
            searchDataViewModel.getTeamsData().observe(getViewLifecycleOwner(), responseObject -> {
                if (responseObject.getError() == null) {
                    if (responseObject.getTeams() != null && responseObject.getTeams().getTeams() != null && !responseObject.getTeams().getTeams().isEmpty()) {
                        final TeamDetails teamItem = responseObject.getTeams().getTeams().get(0);
                        if (teamItem != null) {
                            searchAnimIcon.setVisibility(View.GONE);
                            teamName.setText(teamItem.getStrTeam());
                            Utils.loadImage(activity, (ImageView) teamLogo, Objects.requireNonNull(teamItem.getStrTeamBadge()));
                            leagueName.setText(teamItem.getStrLeague());
                            foundedInYear.setText(teamItem.getIntFormedYear());
                            shortInfo.setText(teamItem.getStrDescriptionEN());

                            try {
                                instaUrl = Objects.requireNonNull(teamItem.getStrInstagram()).replace("\\", "");
                                twitterUrl = Objects.requireNonNull(teamItem.getStrTwitter()).replace("\\", "");
                            } catch (NullPointerException ex) {
                                showToast("Something went wrong. Please try again!");
                            }

                            // Hide the instagram button i dat is null
                            if (instaUrl == null || instaUrl.isEmpty()) {
                                instaIcon.setVisibility(View.GONE);
                            }

                            // Hide the twitter button i dat is null
                            if (twitterUrl == null || twitterUrl.isEmpty()) {
                                twitterIcon.setVisibility(View.GONE);
                            }

                            teamCardDetails.setVisibility(View.VISIBLE);
                        }
                    } else {
                        teamCardDetails.setVisibility(View.GONE);
                        if (searchAnimIcon.getVisibility() != View.VISIBLE) {
                            noEventFound.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    throwError(responseObject.getError());
                }
            });
        }
    }

    private void initUILayer() {
        final SearchView searchView = rootView.findViewById(R.id.search);
        teamCardDetails = rootView.findViewById(R.id.teamCardDetails);
        teamLogo = rootView.findViewById(R.id.iv_Team);
        teamName = rootView.findViewById(R.id.teamName);
        foundedInYear = rootView.findViewById(R.id.foundedInYear);
        leagueName = rootView.findViewById(R.id.leagueName);
        searchAnimIcon = rootView.findViewById(R.id.searchAnimIcon);
        shortInfo = rootView.findViewById(R.id.shortInfo);
        instaIcon = rootView.findViewById(R.id.instaIcon);
        twitterIcon = rootView.findViewById(R.id.twitterIcon);
        noEventFound = rootView.findViewById(R.id.noEventFound);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchAnimIcon.setVisibility(View.GONE);
                searchDataViewModel.init(mySportsAPI, query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        instaIcon.setOnClickListener(v -> {
            if (instaUrl != null) {
                startActivity(newInstagramProfileIntent(activity.getPackageManager(), instaUrl));
            }
        });

        twitterIcon.setOnClickListener(v -> {
            if (twitterUrl != null) {
                startActivity(newTwitterProfileIntent(activity.getPackageManager(), twitterUrl));
            }
        });

    }

    private void initViewModel() {
        searchDataViewModel = new ViewModelProvider(requireActivity()).get(SearchDataViewModel.class);
    }

    //
    private static Intent newInstagramProfileIntent(@NonNull PackageManager pm, @NonNull String url) {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            if (pm.getPackageInfo("com.instagram.android", 0) != null) {
                if (url.endsWith("/")) {
                    url = url.substring(0, url.length() - 1);
                }
                final String username = url.substring(url.lastIndexOf("/") + 1);
                intent.setData(Uri.parse("http://instagram.com/_u/" + username));
                intent.setPackage("com.instagram.android");
                return intent;
            }
        } catch (ActivityNotFoundException exception) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.instagram.android"));
        } catch (PackageManager.NameNotFoundException ignored) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.instagram.android"));
        }
        intent.setData(Uri.parse(url));
        return intent;
    }

    private static Intent newTwitterProfileIntent(PackageManager pm, String url) {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            if (pm.getPackageInfo("com.twitter.android", 0) != null) {
                if (url.endsWith("/")) {
                    url = url.substring(0, url.length() - 1);
                }
                final String username = url.substring(url.lastIndexOf("/") + 1);
                return new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + username));
            }
        } catch (ActivityNotFoundException exception) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.twitter.android"));
        } catch (PackageManager.NameNotFoundException ignored) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.twitter.android"));
        }
        intent.setData(Uri.parse(url));
        return intent;
    }


}
