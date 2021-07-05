package com.univ.l3.s2_calculdestatistiques;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.univ.l3.s2_calculdestatistiques.Statistiques.Statistique;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Statistique}.
 * TODO: Replace the implementation with code for your data type.
 */
public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.ViewHolder> {

    private final List<Statistique> mValues;

    public StatsAdapter(List<Statistique> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_stats, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mLabelView.setText(mValues.get(position).intitule);
        holder.mStatView.setText(mValues.get(position).calcul);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mLabelView;
        public final TextView mStatView;
        public Statistique mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mLabelView = (TextView) view.findViewById(R.id.stat_label);
            mStatView = (TextView) view.findViewById(R.id.stat_content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mStatView.getText() + "'";
        }
    }
}