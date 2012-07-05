package omnicentre.eworky.tools;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import omnicentre.eworky.R;
import omnicentre.eworky.places.Place;
import omnicentre.eworky.places.PlaceList;
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
		
		Place p = values.get(position);
 
		View rowView = inflater.inflate(R.layout.list, parent, false);
		TextView name = (TextView) rowView.findViewById(R.id.name);

		TextView type = (TextView) rowView.findViewById(R.id.type);
		TextView description = (TextView) rowView.findViewById(R.id.description);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.picture);
		name.setText(p.getName());
		type.setText(p.getType());
		description.setText(p.getDescription());
		
		TextView distance = (TextView) rowView.findViewById(R.id.distance);
		distance.setText(p.getDistance() + " km");
		
		TextView address = (TextView) rowView.findViewById(R.id.address);
		String add = (p.getType().length() > 0 ? " - ":"") + p.getAddress();
		add += (p.getCity().length() > 0 ? " - ":"") + p.getCity();
		address.setText(add);
		
		if (!p.getAmenities().hasFreeWifi())
			((ImageView) rowView.findViewById(R.id.wifi)).setVisibility(View.GONE);
		
		if (!p.getAmenities().hasWifi())
			((ImageView) rowView.findViewById(R.id.wifi_euros)).setVisibility(View.GONE);
		
		if (!p.getAmenities().hasFood())
			((ImageView) rowView.findViewById(R.id.food)).setVisibility(View.GONE);
		
		if (!p.getAmenities().hasCoffee())
			((ImageView) rowView.findViewById(R.id.coffee)).setVisibility(View.GONE);
		
		if (!p.getAmenities().hasParking())
			((ImageView) rowView.findViewById(R.id.parking)).setVisibility(View.GONE);
		
		if (!p.getAmenities().hasAccess())
			((ImageView) rowView.findViewById(R.id.access)).setVisibility(View.GONE);
		URL url;
		Drawable d = null;
		try {
			url = new URL(p.getImage().length() == 0 ?
					"http://www.eworky.fr/Content/images/no_image.png" : p.getImage());
			InputStream content = (InputStream)url.getContent();
			d = Drawable.createFromStream(content , "src"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
 
			imageView.setImageDrawable(d);
	
 
		return rowView;
	}
}
