package de.rwth_aachen.engel.beaconsforproduction;

import android.os.AsyncTask;
import android.util.Log;


/**
 * Created by steffen on 5/27/16.
 */

public class  BeaconApiDownloader extends AsyncTask <String, Integer, Long> {
    protected Long doInBackground(String... url) {
        //Downloader.executeGET(url);

        return null;
    }
    protected void onPostExecute(Long result) {
        Log.d("Downloader", "Downloaded " + result + " bytes");
    }
}
