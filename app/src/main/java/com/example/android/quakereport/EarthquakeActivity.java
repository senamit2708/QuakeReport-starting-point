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

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private static final String USGS_RQST_URL = " https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    private EarthquakeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);


        EarthquakeAsyncTask earthquakeAsyncTask = new EarthquakeAsyncTask();
        earthquakeAsyncTask.execute(USGS_RQST_URL);

//        ArrayList<EarthquakeItems> earthquakes = QueryUtils.extractEarthquake();

//        List<EarthquakeItems> earthquakes = new ArrayList<EarthquakeItems>();

//
//        earthquakes = QueryUtils.extractFeatureFromJson();




        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes


       mAdapter = new EarthquakeAdapter(this, new ArrayList<EarthquakeItems>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);


        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EarthquakeItems currentEarthquake = mAdapter.getItem(position);
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<EarthquakeItems>>{

        @Override
        protected List<EarthquakeItems> doInBackground(String... url) {
            Log.e(LOG_TAG, "In doInBackground function" + "starting");

            if (url.length<1 || url[0]==null){
                return null;
            }

           List<EarthquakeItems> result= QueryUtils.fetchEarthquakeData(url[0]);

            return result;


        }

        @Override
        protected void onPostExecute(List<EarthquakeItems> result) {
            Log.e(LOG_TAG,"In onPostExecute function"+"starting");

            //clear the adapter of previous data
            mAdapter.clear();
            if (result != null && !result.isEmpty()) {
                mAdapter.addAll(result);
            }

        }
    }

}
