package de.rwth_aachen.engel.beaconsforproduction;

import android.os.AsyncTask;
import android.util.Log;


/**
 * Created by steffen on 5/27/16.
 */

public class  BeaconApiDownloader extends AsyncTask <String, Integer, String> {
    protected String doInBackground(String... url) {

        String result = "";

        for(int i = 0; i < url.length; i++) {
            result += Downloader.executeGET(url[i]);
        }

        return result;
    }
    protected void onPostExecute(String result) {
        Log.d("BeaconApiDownloader", "Downloaded " + result + " bytes");
    }
}

//new BeaconApiDownloader().execute(url1, url2, url3); url as String
