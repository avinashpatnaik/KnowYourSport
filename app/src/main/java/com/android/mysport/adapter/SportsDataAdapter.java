package com.android.mysport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.mysport.R;
import com.android.mysport.model.SportEventDetails;
import com.android.mysport.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the adapter for displaying the results of the matches.
 */
public class SportsDataAdapter extends RecyclerView.Adapter<SportsDataAdapter.SportEventDetailsHolder> {

    private List<SportEventDetails> sportEventDetailsList = new ArrayList<>();
    Context context;

    @NonNull
    @Override
    public SportEventDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        final View itemView = LayoutInflater.from(context).inflate(R.layout.card_results, parent, false);
        return new SportEventDetailsHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull SportEventDetailsHolder holder, int position) {
        holder.bindData(sportEventDetailsList.get(position));
    }

    @Override
    public int getItemCount() {
        if (sportEventDetailsList != null) {
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
        private ImageView resultsHomeTeam;
        private TextView resultsHomeScore;
        private TextView resultsAwayScore;
        private TextView resultsHomeName;
        private TextView resultsAwayName;
        private Context mContext;

        public SportEventDetailsHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.mContext = context;
            resultsEventDate = itemView.findViewById(R.id.tv_resultsEventDate);
            resultsHomeTeam = itemView.findViewById(R.id.iv_resultsHomeTeam);
            resultsHomeScore = itemView.findViewById(R.id.tv_resultsHomeScore);
            resultsAwayScore = itemView.findViewById(R.id.tv_resultsAwayScore);
            resultsHomeName = itemView.findViewById(R.id.tv_resultsHomeName);
            resultsAwayName = itemView.findViewById(R.id.tv_resultsAwayName);
        }

        private void bindData(@NonNull final SportEventDetails sportEventDetails) {

            resultsEventDate.setText(sportEventDetails.getDateEvent());
            resultsHomeScore.setText(sportEventDetails.getIntHomeScore());
            resultsAwayScore.setText(sportEventDetails.getIntAwayScore());
            resultsHomeName.setText(sportEventDetails.getStrHomeTeam());
            resultsAwayName.setText(sportEventDetails.getStrAwayTeam());

            final String strThumb = sportEventDetails.getStrThumb();
            if (strThumb != null && !strThumb.isEmpty()) {
                resultsHomeTeam.setBackgroundResource(android.R.color.transparent);
                Utils.loadImage(mContext, resultsHomeTeam, strThumb);
            } else {
                resultsHomeTeam.setBackground(ContextCompat.getDrawable(mContext, R.drawable.placeholder_card));
                resultsHomeTeam.setImageResource(android.R.color.transparent);
            }

        }
    }


}
