package omnicentre.eworky.localisations;

import java.util.ArrayList;

import omnicentre.eworky.LocalisationDetails;
import omnicentre.eworky.tools.MyOverlayItem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.readystatesoftware.mapviewballoons.BalloonItemizedOverlay;

/**
 * This class is used to display the list of points on the map.
 * It also contains the methods when the points are tapped.
 *
 */
public class MyItimizedOverlay extends BalloonItemizedOverlay<MyOverlayItem> {

    /**
     * The map activity.
     */
	private Activity a;

	/**
	 * The list of points.
	 */
	private ArrayList<MyOverlayItem> list = new ArrayList<MyOverlayItem>();

	@Override
	protected MyOverlayItem createItem(int i) {
		return list.get(i);
	}

	@Override
	public int size() {
		return list.size();
	}

	public void addOverlayItem(MyOverlayItem overlay) {
		list.add(overlay);
		populate();
	}

	public MyItimizedOverlay(Drawable defaultMarker, MapView mapView,
	        Activity activity) {
		super(boundCenterBottom(defaultMarker), mapView);
		this.a = activity;
	}

	@Override
	public GeoPoint getCenter() {

		if (list.size() == 0) {
			return new GeoPoint(0, 0);
		}

		int minLatitude = Integer.MAX_VALUE;
		int maxLatitude = Integer.MIN_VALUE;
		int minLongitude = Integer.MAX_VALUE;
		int maxLongitude = Integer.MIN_VALUE;

		for (MyOverlayItem overlay : list) {
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
	protected boolean onBalloonTap(int index, MyOverlayItem item) {
		Intent intent = new Intent(a, LocalisationDetails.class);
		intent.putExtra("place", item.getPlace());
		a.startActivity(intent);
		return true;
	}
	
	// This allows to customize the bubble:
	/*
    @Override
    protected BalloonOverlayView<PlaceOverlayItem> createBalloonOverlayView() {
        return new PlaceOverlayView<PlaceOverlayItem>(getMapView().getContext(), getBalloonBottomOffset());
    }
    */
}
