package de.rwth_aachen.engel.beaconsforproduction;


import android.os.AsyncTask;


/**
 * Created by steffen on 5/27/16.
 */

public class  BeaconApiDownloader extends AsyncTask <String, Integer, String> {
    protected String doInBackground(String... params) {

        String url = "http://itslocationservices.azurewebsites.net/api/getbeacons";

        String result;


        result = Downloader.executeGET(url);


        return result;
    }
    protected void onPostExecute(String result) {
    }
}
