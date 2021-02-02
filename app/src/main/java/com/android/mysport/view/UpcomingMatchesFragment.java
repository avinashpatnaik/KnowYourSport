package com.android.mysport.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.mysport.R;
import com.android.mysport.adapter.UpcomingMatchesDataAdapter;
import com.android.mysport.model.SportEventDetails;
import com.android.mysport.viewmodel.UpcomingMatchesViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * This is the fragment responsible for handling the upcoming matches view
 */
public class UpcomingMatchesFragment extends RootFragment {

    private Spinner upcomingLeaguesSpinner;
    private UpcomingMatchesDataAdapter upcomingMatchesDataAdapter;
    private List<String> leagueCodeData = new ArrayList<>();
    private UpcomingMatchesViewModel upcomingMatchesViewModel;
    private RecyclerView upcomingSportsDataRecyclerView;
    private View loadingAnim;
    private View separator;
    private TextView noEventFound;

    // Getting the fragment instance
    public static UpcomingMatchesFragment getInstance() {
        return new UpcomingMatchesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_upcoming, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewModel();
        initUILayer();

        if (upcomingMatchesViewModel != null) {
            upcomingMatchesViewModel.getData().observe(getViewLifecycleOwner(), responseObject -> {
                if (responseObject.getError() == null) {
                    if (upcomingMatchesDataAdapter != null) {
                        final List<SportEventDetails> sportEventDetailsList = Objects.requireNonNull(responseObject.getEvents()).getSportsEvents();
                        if (sportEventDetailsList != null && !sportEventDetailsList.isEmpty()) {
                            hideNoDataState();
                            upcomingSportsDataRecyclerView.post(() -> upcomingMatchesDataAdapter.setAdapterData(sportEventDetailsList));
                        } else {
                            hideNoDataState();
                            noEventFound.setVisibility(View.VISIBLE);
                            upcomingSportsDataRecyclerView.setVisibility(View.GONE);
                        }
                    }
                } else {
                    throwError(responseObject.getError());
                }
            });
        }
    }

    private void initViewModel() {
        upcomingMatchesViewModel = new ViewModelProvider(requireActivity()).get(UpcomingMatchesViewModel.class);
    }

    private void initUILayer() {
        upcomingLeaguesSpinner = rootView.findViewById(R.id.upcomingLeaguesSpinner);
        upcomingSportsDataRecyclerView = rootView.findViewById(R.id.upcomingSportsDataRecyclerView);
        loadingAnim = rootView.findViewById(R.id.loadingAnim);
        separator = rootView.findViewById(R.id.separator);
        noEventFound = rootView.findViewById(R.id.noEventFound);

        upcomingSportsDataRecyclerView.setVisibility(View.GONE);
        loadingAnim.setVisibility(View.VISIBLE);

        upcomingMatchesDataAdapter = new UpcomingMatchesDataAdapter();
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, RecyclerView.VERTICAL, false);
        upcomingSportsDataRecyclerView.setLayoutManager(linearLayoutManager);
        upcomingSportsDataRecyclerView.setAdapter(upcomingMatchesDataAdapter);

        setUpLeagues();
        setUpSpinnerForLeagues();
    }

    private void setUpLeagues() {
        final String[] leagueCodes = activity.getResources().getStringArray(R.array.LeagueCodes);
        leagueCodeData.addAll(Arrays.asList(leagueCodes));
    }

    private void setUpSpinnerForLeagues() {

        upcomingMatchesDataAdapter.clearAdapterData();

        upcomingLeaguesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    separator.setVisibility(View.VISIBLE);
                }
                if (upcomingMatchesViewModel != null && position > 0) {
                    upcomingMatchesViewModel.init(mySportsAPI, leagueCodeData.get(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void hideNoDataState() {
        loadingAnim.setVisibility(View.GONE);
        upcomingSportsDataRecyclerView.setVisibility(View.VISIBLE);
        noEventFound.setVisibility(View.GONE);
    }

}
