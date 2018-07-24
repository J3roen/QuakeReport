/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private final String sampleUrl = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private static final String LOG_TAG = EarthquakeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        //create Loadermanager to manage AsyncTaskLoader to fetch earthquakes from url
        LoaderManager loaderManager = getLoaderManager();
        Log.d(LOG_TAG, "TEST: LoaderManager.initLoader called");
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
    }

    //method for updating UI, using Earthquake List as param
    private void updateUI(List<Earthquake> earthquakeList) {

        // Find a reference to the {@link RecyclerView} in the layout
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerList);

        //use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Create & set custom adapter
        mAdapter = new EarthquakeListAdapter((ArrayList) earthquakeList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        Log.d(LOG_TAG, "TEST: onCreateLoader method called");
        EarthquakeLoader earthquakeLoader = new EarthquakeLoader(this.getApplicationContext(), sampleUrl);
        return earthquakeLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakeList) {
        Log.d(LOG_TAG, "TEST: onLoadFinished method called");
        // Find a reference to the {@link RecyclerView} in the layout
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerList);

        //use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Create & set custom adapter
        mAdapter = new EarthquakeListAdapter((ArrayList) earthquakeList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        Log.d(LOG_TAG, "TEST: onLoaderReset method called");
        mAdapter = null;
    }
}
