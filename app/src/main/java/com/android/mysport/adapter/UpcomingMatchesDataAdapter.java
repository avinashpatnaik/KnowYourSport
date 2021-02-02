package com.android.mysport.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.mysport.R;
import com.android.mysport.model.SportEventDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the adapter for displaying the upcoming matches based on the league.
 */
public class UpcomingMatchesDataAdapter extends RecyclerView.Adapter<UpcomingMatchesDataAdapter.SportEventDetailsHolder> {

    private List<SportEventDetails> sportEventDetailsList = new ArrayList<>();

    @NonNull
    @Override
    public UpcomingMatchesDataAdapter.SportEventDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_upcoming_matches, parent, false);
        return new UpcomingMatchesDataAdapter.SportEventDetailsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingMatchesDataAdapter.SportEventDetailsHolder holder, int position) {
        holder.bindData(sportEventDetailsList.get(position));
    }

    @Override
    public int getItemCount() {
        if(sportEventDetailsList != null) {
            return sportEventDetailsList.size();
        }
        return -1;
    }

    public void setAdapterData(@NonNull final List<SportEventDetails> sportEvents) {
        clearAdapterData();
        sportEventDetailsList.addAll(sportEvents);
        notifyDataSetChanged();
    }

    public void clearAdapterData() {
        sportEventDetailsList.clear();
        notifyItemRangeRemoved(0, sportEventDetailsList.size());
    }

    protected static class SportEventDetailsHolder extends RecyclerView.ViewHolder {

        private TextView resultsEventDate;
        private TextView resultsHomeName;
        private TextView resultsAwayName;

        public SportEventDetailsHolder(@NonNull final View itemView) {
            super(itemView);
            resultsEventDate = itemView.findViewById(R.id.tv_fixturesEventDate);
            resultsHomeName = itemView.findViewById(R.id.tv_fixturesHomeName);
            resultsAwayName = itemView.findViewById(R.id.tv_fixturesAwayName);
        }

        private void bindData(@NonNull final SportEventDetails sportEventDetails) {
            resultsEventDate.setText(sportEventDetails.getDateEvent());
            resultsHomeName.setText(sportEventDetails.getStrHomeTeam());
            resultsAwayName.setText(sportEventDetails.getStrAwayTeam());
        }

    }
}
