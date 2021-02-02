package com.android.mysport.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.mysport.R;
import com.android.mysport.adapter.SportsDataAdapter;
import com.android.mysport.model.SportEventDetails;
import com.android.mysport.viewmodel.SportsDataViewModel;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * This is the fragment responsible for handling the previous matches view
 */
public class SportsDataFragment extends RootFragment {

    // Get the FirebaseAnalytics instance.
    private FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this.activity);

    private Spinner sportsSpinner;
    private Spinner leaguesSpinner;
    private SportsDataAdapter sportsDataAdapter = null;

    private List<String> leagueNameData = new ArrayList<>();
    private List<String> leagueCodeData = new ArrayList<>();
    private SportsDataViewModel sportsDataViewModel;
    private RecyclerView sportsDataRecyclerView;
    private View loadingAnim;
    private View separator;
    private TextView noEventFound;

    // Getting the fragment instance
    public static SportsDataFragment getInstance() {
        return new SportsDataFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_sports_data, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewModel();
        initUILayer();

        if (sportsDataViewModel != null) {
            sportsDataViewModel.getData().observe(getViewLifecycleOwner(), responseObject -> {

                if (responseObject.getError() == null) {
                    if (sportsDataAdapter != null) {
                        final List<SportEventDetails> sportEventDetailsList = Objects.requireNonNull(responseObject.getEvents()).getSportsEvents();
                        if (sportEventDetailsList != null && sportEventDetailsList.size() >= 1) {
                            sportsDataRecyclerView.setVisibility(View.VISIBLE);
                            sportsDataAdapter.setAdapterData(sportEventDetailsList);
                        } else {
                            showNoDataState();
                        }
                    }
                } else {
                    throwError(responseObject.getError());
                }
            });
        }
    }

    private void initViewModel() {
        sportsDataViewModel = new ViewModelProvider(requireActivity()).get(SportsDataViewModel.class);
    }

    private void initUILayer() {
        sportsSpinner = rootView.findViewById(R.id.sportsSpinner);
        leaguesSpinner = rootView.findViewById(R.id.leaguesSpinner);
        sportsDataRecyclerView = rootView.findViewById(R.id.sportsDataRecyclerView);
        loadingAnim = rootView.findViewById(R.id.loadingAnim);
        separator = rootView.findViewById(R.id.separator);
        noEventFound = rootView.findViewById(R.id.noEventFound);

        sportsDataAdapter = new SportsDataAdapter();
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, RecyclerView.VERTICAL, false);
        sportsDataRecyclerView.setLayoutManager(linearLayoutManager);
        sportsDataRecyclerView.setAdapter(sportsDataAdapter);

        setUpSpinnerForSports();

        sportsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String sport = Arrays.asList(activity.getResources().getStringArray(R.array.SportsValues)).get(position);

                // Tracking which sport the user is interested in
                final Bundle params = new Bundle();
                params.putString(FirebaseAnalytics.Param.ITEM_ID, sport);
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, params);

                if (sportsDataRecyclerView.getVisibility() == View.VISIBLE) {
                    loadingAnim.setVisibility(View.GONE);
                }

                if (position > 0) {
                    sportsDataRecyclerView.setVisibility(View.GONE);
                    leaguesSpinner.setVisibility(View.VISIBLE);
                    separator.setVisibility(View.VISIBLE);
                    setUpSpinnerForLeagues(sport);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do Nothing
            }
        });
    }

    // Set up the spinner for populating all the popular sports
    private void setUpSpinnerForSports() {
        final String[] sportsData = activity.getResources().getStringArray(R.array.SportsValues);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_item, Arrays.asList(sportsData));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sportsSpinner.setAdapter(adapter);
    }

    // Set up the spinner for populating all the popular leagues based on the sport that is selected.
    private void setUpSpinnerForLeagues(@NonNull final String sport) {

        leagueNameData.clear();
        leagueCodeData.clear();
        sportsDataAdapter.clearAdapterData();

        switch (sport) {
            case "Cricket":
                final String[] data = activity.getResources().getStringArray(R.array.CricketLeagueValues);
                final String[] codes = activity.getResources().getStringArray(R.array.CricketLeagueCodes);
                leagueNameData.addAll(Arrays.asList(data));
                leagueCodeData.addAll(Arrays.asList(codes));
                break;
            case "Soccer":
                final String[] soccerData = activity.getResources().getStringArray(R.array.SoccerLeagueValues);
                leagueNameData.addAll(Arrays.asList(soccerData));
                final String[] soccerCodes = activity.getResources().getStringArray(R.array.SoccerLeagueCodes);
                leagueCodeData.addAll(Arrays.asList(soccerCodes));
                break;
            case "BaseBall":
                final String[] tennisData = activity.getResources().getStringArray(R.array.BaseBallLeagueValue);
                leagueNameData.addAll(Arrays.asList(tennisData));
                final String[] tennisCodes = activity.getResources().getStringArray(R.array.BaseBallLeagueCodes);
                leagueCodeData.addAll(Arrays.asList(tennisCodes));
                break;
            case "American Football":
                final String[] americanFootBallData = activity.getResources().getStringArray(R.array.AmericanFootBallLeagueValues);
                leagueNameData.addAll(Arrays.asList(americanFootBallData));
                final String[] americanFootBallCodes = activity.getResources().getStringArray(R.array.AmericanFootBallLeagueCodes);
                leagueCodeData.addAll(Arrays.asList(americanFootBallCodes));
                break;
            case "Basket Ball":
                final String[] basketBallData = activity.getResources().getStringArray(R.array.BasketBallLeagueValues);
                leagueNameData.addAll(Arrays.asList(basketBallData));
                final String[] basketBallCodes = activity.getResources().getStringArray(R.array.BasketBallLeagueCodes);
                leagueCodeData.addAll(Arrays.asList(basketBallCodes));
                break;
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_item, leagueNameData);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leaguesSpinner.setAdapter(adapter);

        leaguesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (sportsDataViewModel != null && position > 0) {
                    hideNoDataState();
                    sportsDataAdapter.clearAdapterData();
                    sportsDataViewModel.init(mySportsAPI, leagueCodeData.get(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }


    private void showNoDataState() {
        sportsDataRecyclerView.setVisibility(View.GONE);
        noEventFound.setVisibility(View.VISIBLE);
    }

    private void hideNoDataState() {
        loadingAnim.setVisibility(View.GONE);
        sportsDataRecyclerView.setVisibility(View.VISIBLE);
    }

}
