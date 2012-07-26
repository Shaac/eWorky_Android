package omnicentre.eworky.API;

import java.util.List;

import omnicentre.eworky.SearchResults;
import omnicentre.eworky.API.LocalisationJson;
import omnicentre.eworky.API.NoSuccessException;
import omnicentre.eworky.API.Requests;
import omnicentre.eworky.tools.SearchCriteria;

import android.app.ProgressDialog;
import android.os.AsyncTask;

/**
 * Run on background the request to the API and the parsing.
 *
 */
public class AsyncSearch
extends AsyncTask<Void, Void, List<LocalisationJson>> {

    private ProgressDialog progress;
    private SearchResults activity;
    private SearchCriteria criteria;
    private List<LocalisationJson> localisationsList;
    private String error = "";

    public AsyncSearch(SearchResults activity, SearchCriteria criteria){
        this.activity = activity;
        this.criteria = criteria;
    }

    public void onPreExecute() {
        // We display a progress dialog:
        progress = ProgressDialog.show(activity, "", 
                "Loading. Please wait...", true);
    }

    public List<LocalisationJson> doInBackground(Void... unused) {

        try {
            localisationsList = Requests.search(criteria);
        } catch (NoSuccessException e) {
            error = e.getError();
        }

        return localisationsList;
    }

    public void onPostExecute(List<LocalisationJson> placeList) {
        if (error.length() == 0)
            activity.refresh(placeList);
        
        progress.dismiss();
    }
}