package omnicentre.eworky;

import java.io.InputStream;
import java.net.URL;

import omnicentre.eworky.places.Place;
import omnicentre.eworky.tools.TitleBar;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.drawable.Drawable;

public class PlaceDetails extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TitleBar titleBar = new TitleBar(this);
		setContentView(R.layout.place_details);
		
		Place p = getIntent().getExtras().getParcelable("place");
		TextView t = (TextView) findViewById(R.id.content);
		URL url;
		Drawable d = null;
		try {
			url = new URL(p.getImage());
			InputStream content = (InputStream)url.getContent();
			d = Drawable.createFromStream(content , "src"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		//ImageView iv = (ImageView) findViewById(R.id.picture);
		//iv.setImageDrawable(d);
		t.setCompoundDrawablesWithIntrinsicBounds(null,d,null,null);
		t.setText(p.getDescription());
		
		titleBar.setTitleBar(R.layout.title_index);

	}

}
