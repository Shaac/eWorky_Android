package omnicentre.eworky;

import omnicentre.eworky.tools.TitleBar;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

/*
 * This class is the main view of this application.
 * 
 * It contains the search bar.
 */

public class Index extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TitleBar titleBar = new TitleBar(this);
		setContentView(R.layout.index);
		titleBar.setTitleBar(R.layout.title_layout);

		Button b = (Button) findViewById(R.id.search_button);
		b.setOnClickListener(searchListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_index, menu);
		return true;
	}

	private OnClickListener searchListener = new OnClickListener() {

		public void onClick(View v) {

			EditText search_bar = (EditText) findViewById(R.id.search_bar);
			String query = search_bar.getText().toString();

			Intent intent = new Intent(Index.this, SearchResults.class);
			intent.putExtra("query", query);
			startActivity(intent);
		}

	};
}
