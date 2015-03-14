package com.fudfill.runner.slidingmenu.service;

import android.os.Parcel;
import android.util.Log;

import com.fudfill.runner.slidingmenu.common.Runner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by praveenthota on 3/13/15.
 */
public class RunnersLocationReq extends HTTPRequest {

    private static String TAG = "RunnersLocationReq";

    List<Runner> runnersList;
    // JSON Node names
    private static final String TAG_RUNNERS = "runners";
    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "emailId";
    private static final String TAG_LOCATION = "location";
    private static final String TAG_LATITUDE = "latitude";
    private static final String TAG_LONGITUDE = "longitude";
    private static final String TAG_UPDATEDTIME = "lastupdatedtime";
    private static final String TAG_PHONE_MOBILE = "mobile";

    public RunnersLocationReq(Parcel temp) {
        super(temp);
    }

    public List<Runner> getRunnersList() {
        return runnersList;
    }

    @Override
    public int parseData() {

        String jsonStr = getRespData();

        Log.d(TAG, "Response: > " + jsonStr);


        if (jsonStr != null) {
            try {
                JSONArray runners;
                runnersList = new ArrayList<Runner>();
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                runners = jsonObj.getJSONArray(TAG_RUNNERS);

                // looping through All orders
                for (int i = 0; i < runners.length(); i++) {
                    Runner tRunner = new Runner();
                    JSONObject runner = runners.getJSONObject(i);

                    String mobile = runner.getString(TAG_PHONE_MOBILE);
                    String name = runner.getString(TAG_NAME);
                    String email = runner.getString(TAG_EMAIL);
                    // Location node is JSON Object
                    //JSONObject location = runner.getJSONObject(TAG_LOCATION);
                    String latitude = runner.getString(TAG_LATITUDE);
                    String longitude = runner.getString(TAG_LONGITUDE);
                    String timeupdated = runner.getString(TAG_UPDATEDTIME);

                    tRunner.setEmailId(email);
                    tRunner.setRunnerId(email);
                    tRunner.setLatitude(latitude);
                    tRunner.setLongitude(longitude);
                    tRunner.setMobile(mobile);
                    tRunner.setLastUpdatedtime(timeupdated);
                    tRunner.setName(name);
                    runnersList.add(tRunner);

                }
            } catch (JSONException e) {
                e.printStackTrace();
                return 1;
            }
        }

        return 0;
    }
}
