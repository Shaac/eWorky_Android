package omnicentre.eworky.localisations;

import java.util.ArrayList;

import omnicentre.eworky.SearchResults;
import omnicentre.eworky.API.LocalisationJson;
import omnicentre.eworky.API.NoSuccessException;
import omnicentre.eworky.API.Requests;

import android.app.ProgressDialog;
import android.os.AsyncTask;

/**
 * Run on background the request to the API and the parsing.
 *
 */
public class LocalisationsLoader extends AsyncTask<Void, Void, ArrayList<LocalisationJson>> {

    private ProgressDialog progress;
    private SearchResults activity;
    private boolean isOk = true;
    private String error = "";

    public LocalisationsLoader(SearchResults activity, ProgressDialog progress) {
        this.progress = progress;
        this.activity = activity;
    }

    public void onPreExecute() {
        progress.show();
    }

    public ArrayList<LocalisationJson> doInBackground(Void... unused) {
        
        ArrayList<LocalisationJson> placeList = null;

        // We check if the query is valid:
        String[] keys = activity.getIntent().getExtras().getStringArray("keys");
        try {
            placeList = (ArrayList<LocalisationJson>) Requests.search();
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
}