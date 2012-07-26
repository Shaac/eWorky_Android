package omnicentre.eworky.tools;

import omnicentre.eworky.R;
import omnicentre.eworky.SearchResults;
import omnicentre.eworky.API.AsyncSearch;
import omnicentre.eworky.tools.SearchCriteria;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Run on background the request to the API and the parsing.
 *
 */
public class GPS extends AsyncTask<Void, Void, SearchCriteria> {

    private ProgressDialog progress;
    private SearchResults activity;
    private SearchCriteria criteria;
    private String error = "";

    public GPS(SearchResults activity, SearchCriteria criteria){
        this.activity = activity;
        this.criteria = criteria;
    }

    public void onPreExecute() {
        // We display a progress dialog:
        progress = ProgressDialog.show(activity, "", 
                "Loading. Please wait...", true);
    }

    public SearchCriteria doInBackground(Void... unused) {
        if (criteria.getName() == null && criteria.getPlace() == null) {
            // We have to get GPS location:
            LocationManager lManager = (LocationManager)
                    activity.getSystemService(Context.LOCATION_SERVICE); 
            Location location = lManager.getLastKnownLocation(
                    lManager.getBestProvider(new Criteria(), true));
            if (location != null) {
                criteria.setLatitude(location.getLatitude());
                criteria.setLongitude(location.getLongitude());
            } else
                error = activity.getResources().getString(R.string.errorGPS);;
        }
        return criteria;
    }

    public void onPostExecute(SearchCriteria criteria) {
        progress.dismiss();
        Log.w("lat", "" + criteria.getLatitude());
        Log.w("error", "" + error);
        if (error.length() == 0)
            (new AsyncSearch(activity, criteria)).execute();
    }
}