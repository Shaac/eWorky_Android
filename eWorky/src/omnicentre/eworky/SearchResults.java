package omnicentre.eworky;

import java.util.ArrayList;

import omnicentre.eworky.tools.Place;
import omnicentre.eworky.tools.TitleBar;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchResults extends ListActivity {
	
	private ArrayList<Place> l;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TitleBar titleBar = new TitleBar(this);

		try {
			l = Place.fromQuery(getIntent().getExtras().getString("query"));

			String[] values = new String[l.size()];

			setListAdapter(new ArrayAdapter<String>(this, R.layout.search_results,values));

			int i = 0;
			for (Place p : l) {
				values[i] = p.getName();
				i++;
			}

		} catch (Exception e) {
			String[] values = new String[] {"error"};
			setListAdapter(new ArrayAdapter<String>(this, R.layout.search_results,values));
		}
		getListView().setTextFilterEnabled(true);
		titleBar.setTitleBar(R.layout.title_results);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Intent intent = new Intent(this, PlaceDetails.class);
		intent.putExtra("place",  this.l.get(position));
		startActivity(intent);
	}
}
