package omnicentre.eworky;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import omnicentre.eworky.tools.Place;
import omnicentre.eworky.tools.TitleBar;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Display the results of the search in a list.
 *
 */
public class SearchResults extends ListActivity {

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

		// We create the list to be displayed:
		ArrayList<String> values = new ArrayList<String>();
		if (isOk)
			for (Place p : placeList)
				values.add(p.getName());		

		// We create the view:
		TitleBar titleBar = new TitleBar(this);
		titleBar.setPlaceList(placeList);
		setListAdapter(new ArrayAdapter<String>(this,
				R.layout.search_results, values));
		getListView().setTextFilterEnabled(true);
		titleBar.setTitleBar(R.layout.title_results);

		// If something went wrong we redirect to the main view:
		if(!isOk) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Error");
			builder.setMessage(error);
			builder.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					Intent intent = new Intent(SearchResults.this,Index.class);
					startActivity(intent);
				}
			});
			builder.create().show();
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		// If an item on the list is clicked, we go to its detail page:
		Intent intent = new Intent(this, PlaceDetails.class);
		intent.putExtra("place",  this.placeList.get(position));
		startActivity(intent);
	}
}
