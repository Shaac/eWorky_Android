package omnicentre.eworky.tools;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class ListItimizedOverlay extends ItemizedOverlay<OverlayItem> {

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

	public ListItimizedOverlay(Drawable defaultMarker)
	{
		super(boundCenterBottom(defaultMarker));
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

}
