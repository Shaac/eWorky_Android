package omnicentre.eworky;

import omnicentre.eworky.tools.Http;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class SearchResults extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			JSONObject json= new JSONObject(Http.getUrl("http://www.eworky.com/api/localisation/search?place=" + getIntent().getExtras().getString("query") + "&json=1"));
			JSONArray jsonArray = json.getJSONArray("response");
			String[] values = new String[jsonArray.length()];

			setListAdapter(new ArrayAdapter<String>(this, R.layout.search_results,values));

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				values[i] = jsonObject.getString("name");
			}


			getListView().setTextFilterEnabled(true);
		} catch (Exception e) {
			setContentView(R.layout.error);
		}
	}
}
