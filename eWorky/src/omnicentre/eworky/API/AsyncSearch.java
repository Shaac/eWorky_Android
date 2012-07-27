package omnicentre.eworky.API;

import java.util.List;

import omnicentre.eworky.R;
import omnicentre.eworky.API.LocalisationJson;
import omnicentre.eworky.API.NoSuccessException;
import omnicentre.eworky.API.Requests;
import omnicentre.eworky.tools.SearchCriteria;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Run on background the request to the API and the parsing.
 *
 */
public class AsyncSearch
extends AsyncTask<SearchCriteria, Void, List<LocalisationJson>> {

    private ProgressDialog progress;
    private String error = "";
    private ArrayAdapter<LocalisationJson> a;

    public AsyncSearch(ProgressDialog dialog, ArrayAdapter<LocalisationJson>a){
        progress = dialog;
        this.a = a;
    }

    public void onPreExecute() {
        progress.show();
    }

    public List<LocalisationJson> doInBackground(SearchCriteria... criteria) {

        try {
            return Requests.search(criteria[0]);
        } catch (NoSuccessException e) {
            error = e.getError();
            return null;
        }
    }

    public void onPostExecute(List<LocalisationJson> placeList) {
        if (error.length() == 0) {
            a.clear();
            for (LocalisationJson l : placeList)
                a.add(l);
            a.notifyDataSetChanged();
        }
        progress.dismiss();
    }
    
    @SuppressWarnings("unchecked")
    public static void start(Activity activity, SearchCriteria criteria) {
        ListView list = (ListView) activity.findViewById(R.id.list);
        ArrayAdapter<LocalisationJson> a = (ArrayAdapter<LocalisationJson>) list.getAdapter();
        (new AsyncSearch(ProgressDialog.show(activity ,"title","message"), a)).execute(criteria);
    }
}