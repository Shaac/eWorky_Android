package omnicentre.eworky;

import omnicentre.eworky.tools.Place;
import omnicentre.eworky.tools.TitleBar;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;

public class PlaceDetails extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TitleBar titleBar = new TitleBar(this);
		setContentView(R.layout.place_details);
		
		Place p = getIntent().getExtras().getParcelable("place");
		TextView t = (TextView) findViewById(R.id.name);
		t.setText(p.getName() + "\n" + p.getDescription());
		
		titleBar.setTitleBar(R.layout.title_index);

	}

}
