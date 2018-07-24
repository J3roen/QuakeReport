package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader <List<Earthquake>>{
    //Query URL
    private String url;
    //tag for log messages
    private static final String LOG_TAG = Earthquake.class.getSimpleName();

    public EarthquakeLoader (Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        Log.d(LOG_TAG,"TEST: onStartLoading method called");
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        Log.d(LOG_TAG,"TEST: loadInBackground method called");
        if(this.url == null) { return null;}
        return QueryUtils.fetchEarthquakeData(this.url);
    }
}
