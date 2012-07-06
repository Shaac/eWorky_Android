package omnicentre.eworky;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import omnicentre.eworky.places.Place;
import omnicentre.eworky.tools.Dialogs;
import omnicentre.eworky.tools.Redirections;
import omnicentre.eworky.tools.TitleBar;
import omnicentre.eworky.widgets.PlaceArrayAdapter;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/**
 * Displays the results of the search in a list.
 *
 */
public class SearchResults extends ListActivity {

    /**
     * The list of all the found places.
     */
	private ArrayList<Place> placeList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// We check if the query is valid:
		boolean isOk = true;
		String query = getIntent().getExtras().getString("query");
		String error = "";
		if (query == null || query.length() == 0) {
			isOk = false;
			error = getString(R.string.errorEmptyQuery);
		}

		// If it is, we parse the JSON:
		else {
			try {
				placeList = Place.fromQuery(query);
				Place.computeDistance(placeList,query,getApplicationContext());
			} catch (ClientProtocolException e) {
				error = getString(R.string.errorClientProtocol);
				isOk = false;
			} catch (IOException e) {
				error = getString(R.string.errorIo);
				isOk = false;
			} catch (JSONException e) {
				error = getString(R.string.errorBadJson);
				isOk = false;
			}
		}

		// We create the view:
		TitleBar titleBar = new TitleBar(this);
		setListAdapter(new PlaceArrayAdapter(getApplicationContext(),
		        placeList));
		getListView().setTextFilterEnabled(true);
		titleBar.setTitleBar(R.layout.title_results, placeList);

		// If something went wrong we redirect to the main view:
		if(!isOk)
		    Dialogs.newAlertToIndex("Error", error, this);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		// If an item on the list is clicked, we go to its detail page:
		Redirections.placeDetails(this, placeList.get(position));
	}
}
