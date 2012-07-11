package omnicentre.eworky.localisations;

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
public class LocalisationsLoader extends AsyncTask<Void, Void, ArrayList<Localisation>> {

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

    public ArrayList<Localisation> doInBackground(Void... unused) {
        
        ArrayList<Localisation> placeList = null;

        // We check if the query is valid:
        String query = activity.getIntent().getExtras().getString("query");
        if (query == null || query.length() == 0) {
            isOk = false;
            error = activity.getString(R.string.errorEmptyQuery);
        }

        // If it is, we parse the JSON:
        else {
            try {
                placeList = Localisation.fromQuery(query);
                Localisation.computeDistance(placeList,query,activity.getApplicationContext());
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

    public void onPostExecute(ArrayList<Localisation> placeList) {
        activity.show(placeList, isOk, error);
        progress.dismiss();
    }
}