package omnicentre.eworky.tools;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import omnicentre.eworky.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PlaceArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final ArrayList<Place> values;
 
	public PlaceArrayAdapter(Context context, PlaceList places) {
		super(context, R.layout.list, places.toStrings());
		this.context = context;
		this.values = places;
	}
 
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.list, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.picture);
		textView.setText(values.get(position).getName());
 
		URL url;
		Drawable d = null;
		try {
			url = new URL(values.get(position).getImage());
			InputStream content = (InputStream)url.getContent();
			d = Drawable.createFromStream(content , "src"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
 
			imageView.setImageDrawable(d);
	
 
		return rowView;
	}
}
