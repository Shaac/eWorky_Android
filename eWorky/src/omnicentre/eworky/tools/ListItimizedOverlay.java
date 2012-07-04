package omnicentre.eworky.tools;

import java.util.ArrayList;

import omnicentre.eworky.PlaceDetails;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.readystatesoftware.mapviewballoons.BalloonItemizedOverlay;

public class ListItimizedOverlay extends BalloonItemizedOverlay<OverlayItem> {

	private Context c;
	private Activity a;
	private ArrayList<Parcelable> p;

	private ArrayList<OverlayItem> arrayListOverlayItem = new ArrayList<OverlayItem>();

	@Override
	protected OverlayItem createItem(int i)
	{
		return arrayListOverlayItem.get(i);
	}

	@Override
	public int size()
	{
		return arrayListOverlayItem.size();
	}

	public void addOverlayItem(OverlayItem overlay)
	{
		arrayListOverlayItem.add(overlay);
		populate();
	}

	public ListItimizedOverlay(Drawable defaultMarker, MapView mapView, Activity a,
			ArrayList<Parcelable> p)
	{
		super(boundCenterBottom(defaultMarker), mapView);
		c = mapView.getContext();
		this.a = a;
		this.p = p;
	}

	@Override
	public GeoPoint getCenter() {

		if (arrayListOverlayItem.size() == 0) {
			return new GeoPoint(0, 0);
		}

		int minLatitude = Integer.MAX_VALUE;
		int maxLatitude = Integer.MIN_VALUE;
		int minLongitude = Integer.MAX_VALUE;
		int maxLongitude = Integer.MIN_VALUE;

		for (OverlayItem overlay : arrayListOverlayItem) {
			int lat = overlay.getPoint().getLatitudeE6();
			int lon = overlay.getPoint().getLongitudeE6();
			maxLatitude = Math.max(lat, maxLatitude);
			maxLongitude = Math.max(lon, maxLongitude);
			minLatitude = Math.min(lat, minLatitude);
			minLongitude = Math.min(lon, minLongitude);
		}

		return new GeoPoint((maxLatitude + minLatitude) / 2,
				(maxLongitude + minLongitude) / 2);
	}

	@Override
	protected boolean onBalloonTap(int index, OverlayItem item) {
		Intent intent = new Intent(a, PlaceDetails.class);
		intent.putExtra("place", (Place) p.get(index));
		a.startActivity(intent);
		return true;
	}
}
