package omnicentre.eworky.localisations;

import java.util.ArrayList;

import omnicentre.eworky.R;
import omnicentre.eworky.SearchResults;
import omnicentre.eworky.API.LocalisationJson;
import omnicentre.eworky.API.NoSuccessException;
import omnicentre.eworky.API.Requests;
import omnicentre.eworky.tools.Redirections;
import omnicentre.eworky.tools.SearchCriteria;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;

/**
 * Run on background the request to the API and the parsing.
 *
 */
public class LocalisationsLoader
extends AsyncTask<Void, Void, ArrayList<LocalisationJson>> {

    private ProgressDialog progress;
    private SearchResults activity;
    private SearchCriteria criteria;
    private ArrayList<LocalisationJson> localisationsList;
    private String error;

    public LocalisationsLoader(SearchResults activity,ProgressDialog progress){
        this.progress = progress;
        this.activity = activity;
    }

    public void onPreExecute() {

        // We display the progress dialog:
        progress.show();

        // We construct the query:
        criteria = Redirections.getCriteria(activity);
    }

    public ArrayList<LocalisationJson> doInBackground(Void... unused) {

        localisationsList = new ArrayList<LocalisationJson>();
        error = "";

        if (criteria.getName() == null && criteria.getPlace() == null) {
            // We have to get GPS location:
            LocationManager lManager = (LocationManager)
                    activity.getSystemService(Context.LOCATION_SERVICE); 
            Location location = lManager.getLastKnownLocation(
                    lManager.getBestProvider(new Criteria(), true));
            if (location != null) {
                criteria.setLatitude(location.getLatitude());
                criteria.setLongitude(location.getLongitude());
                try {
                    localisationsList = (ArrayList<LocalisationJson>)
                            Requests.search(criteria);
                } catch (NoSuccessException e) {
                    error = e.getError();
                }
            } else
                error = activity.getResources().getString(R.string.errorGPS);;
        } else {
            try {
                localisationsList = (ArrayList<LocalisationJson>)
                        Requests.search(criteria);
            } catch (NoSuccessException e) {
                error = e.getError();
            }
        }

        return localisationsList;
    }

    public void onPostExecute(ArrayList<LocalisationJson> placeList) {
        activity.show(placeList, error);
        progress.dismiss();
    }
}