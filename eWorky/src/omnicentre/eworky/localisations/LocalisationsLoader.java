package omnicentre.eworky.localisations;

import java.util.ArrayList;
import java.util.HashMap;

import omnicentre.eworky.SearchResults;
import omnicentre.eworky.API.LocalisationJson;
import omnicentre.eworky.API.NoSuccessException;
import omnicentre.eworky.API.Requests;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

/**
 * Run on background the request to the API and the parsing.
 *
 */
public class LocalisationsLoader extends AsyncTask<Void, Void,ArrayList<LocalisationJson>> implements LocationListener {

    private ProgressDialog progress;
    private SearchResults activity;
    private Location from;
    private HashMap<String, String> params;
    private ArrayList<LocalisationJson> placeList;

    public LocalisationsLoader(SearchResults activity, ProgressDialog progress) {
        this.progress = progress;
        this.activity = activity;
    }

    public void onPreExecute() {
        progress.show();
     // We construct the query:
        Bundle extras = activity.getIntent().getExtras();
        String[] keys = extras.getStringArray("omnicentre.eworki.keys");
        String[] entries = extras.getStringArray("omnicentre.eworki.entries");
        
        params = new HashMap<String, String>();
        if (keys != null)
            for (int i = 0 ; i < keys.length ; i++)
                params.put(keys[i], entries[i]);

        if (params.isEmpty()) {
            LocationManager lManager = (LocationManager)
                    activity.getSystemService(Context.LOCATION_SERVICE);

            Criteria c = new Criteria();
            //c.setAccuracy(Criteria.ACCURACY_HIGH); 
            Location location = lManager.getLastKnownLocation(lManager.getBestProvider(c, true));
            params.put("latitude", "" + location.getLatitude());
            params.put("longitude", "" + location.getLongitude());
            Log.e("LAT", "" + location.getLatitude());
            Log.e("LNG", "" + location.getLongitude());
        } else
            Log.e("WAT", "");
    }

    public ArrayList<LocalisationJson> doInBackground(Void... unused) {

        placeList = new ArrayList<LocalisationJson>();

        

        try {
            placeList = (ArrayList<LocalisationJson>) Requests.search(params);
        } catch (NoSuccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return placeList;
    }

    public void onPostExecute(ArrayList<LocalisationJson> placeList) {
        activity.show(placeList, true, "");
        progress.dismiss();
    }

    public void onLocationChanged(Location location) {
        from = location;
    }

    public void onProviderDisabled(String provider) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}