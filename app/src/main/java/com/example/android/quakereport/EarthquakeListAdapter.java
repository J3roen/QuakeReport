package com.example.android.quakereport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class EarthquakeListAdapter extends RecyclerView.Adapter<EarthquakeListAdapter.ViewHolder> {
    private ArrayList<Earthquake> mDataset;

    /** Provide a reference to the views for each data item
     Complex data items may need more than one view per item, and
     you provide access to all the views for a data item in a view holder */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView magView;
        private TextView locView;
        private TextView dateView;
        private TextView hourView;
        private Earthquake earthquake;
        private TextView distanceView;

        public ViewHolder (View v) {
            super(v);
            magView = (TextView) v.findViewById(R.id.earthquakeListItem_mag);
            locView = (TextView) v.findViewById(R.id.earthquakeListItem_loc);
            dateView = (TextView) v.findViewById(R.id.earthquakeListItem_date);
            hourView = (TextView) v.findViewById(R.id.earthquakeListItem_dateHour);
            distanceView = (TextView) v.findViewById(R.id.earthquakeListItem_distance);
        }

        public void setListItem(Earthquake listItem) {
            this.earthquake = listItem;
        }

        @Override
        public void onClick(View v) {
            String url = earthquake.getUrl();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            v.getContext().startActivity(i);
        }
    }

    /** Provide a suitable constructor (depends on the kind of dataset) */
    public EarthquakeListAdapter (ArrayList<Earthquake> data) {
        this.mDataset = data;
    }

    /** Create new views (invoked by the layout manager)*/
    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        /** create a new view*/
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.earthquake_list_item, parent, false);

        return new ViewHolder(itemView);
    }

    /** Replace the contents of a view (invoked by the layout manager)*/
    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        /** - get element from your dataset at this position
         / - replace the contents of the view with that element*/
        Context context = holder.magView.getContext();
        Resources res = context.getResources();
        Earthquake earthquake = mDataset.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        holder.magView.setText(decimalFormat.format(earthquake.getMagnitude()));
        holder.locView.setText(earthquake.getPrimaryLocation());
        holder.distanceView.setText(earthquake.getLocationOffset());
        holder.dateView.setText(earthquake.getTimeDate());
        holder.hourView.setText(earthquake.getTimeHour());
        holder.setListItem(earthquake);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) holder.magView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = res.getColor(getMagnitudeColor(earthquake.getMagnitude()));
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        //set proper color for text fields
        holder.locView.setTextColor(res.getColor(R.color.textColorEarthquakeLocation));
        holder.distanceView.setTextColor(res.getColor(R.color.textColorEarthquakeDetails));
        holder.dateView.setTextColor(res.getColor(R.color.textColorEarthquakeDetails));
        holder.hourView.setTextColor(res.getColor(R.color.textColorEarthquakeDetails));

        //add onClickListener to parent Layout of item list
        ViewGroup parent = (ViewGroup) holder.magView.getParent();
        parent.setOnClickListener(holder);
    }

    private int getMagnitudeColor(double magnitude) {
        int magColor;
        switch ((int) magnitude) {
            case 0:
            case 1: magColor = R.color.magnitude1;
                break;
            case 2: magColor = R.color.magnitude2;
                break;
            case 3: magColor = R.color.magnitude3;
                break;
            case 4: magColor = R.color.magnitude4;
                break;
            case 5: magColor = R.color.magnitude5;
                break;
            case 6: magColor = R.color.magnitude6;
                break;
            case 7: magColor = R.color.magnitude7;
                break;
            case 8: magColor = R.color.magnitude8;
                break;
            case 9: magColor = R.color.magnitude9;
                break;
            default: magColor = R.color.magnitude10plus;
                break;
        }
        return magColor;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
