package omnicentre.eworky.places;

import java.io.IOException;
import java.util.ArrayList;

import omnicentre.eworky.R;
import omnicentre.eworky.SearchResults;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.app.ProgressDialog;
import android.os.AsyncTask;

/**
 * Run on background the request to the API and the parsing.
 *
 */
public class PlaceLoader extends AsyncTask<Void, Void, ArrayList<Place>> {

    private ProgressDialog progress;
    private SearchResults activity;
    private boolean isOk = true;
    private String error = "";

    public PlaceLoader(SearchResults activity, ProgressDialog progress) {
        this.progress = progress;
        this.activity = activity;
    }

    public void onPreExecute() {
        progress.show();
    }

    public ArrayList<Place> doInBackground(Void... unused) {
        
        ArrayList<Place> placeList = null;

        // We check if the query is valid:
        String query = activity.getIntent().getExtras().getString("query");
        if (query == null || query.length() == 0) {
            isOk = false;
            error = activity.getString(R.string.errorEmptyQuery);
        }

        // If it is, we parse the JSON:
        else {
            try {
                placeList = Place.fromQuery(query);
                Place.computeDistance(placeList,query,activity.getApplicationContext());
            } catch (ClientProtocolException e) {
                error = activity.getString(R.string.errorClientProtocol);
                isOk = false;
            } catch (IOException e) {
                error = activity.getString(R.string.errorIo);
                isOk = false;
            } catch (JSONException e) {
                error = activity.getString(R.string.errorBadJson);
                isOk = false;
            }
        }
        return placeList;
    }

    public void onPostExecute(ArrayList<Place> placeList) {
        activity.show(placeList, isOk, error);
        progress.dismiss();
    }
}