package omnicentre.eworky;

import java.util.ArrayList;
import java.util.List;

import omnicentre.eworky.tools.ListItimizedOverlay;
import omnicentre.eworky.tools.Place;
import omnicentre.eworky.tools.TitleBar;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;

public class Map extends MapActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TitleBar titleBar = new TitleBar(this);
		setContentView(R.layout.main);
		titleBar.setTitleBar(R.layout.title_index);
		
		MapView mapView = (MapView) this.findViewById(R.id.mapView);

		Drawable drawable = this.getResources().getDrawable(R.drawable.ic_launcher);
		
		
		ArrayList<Parcelable> placeList = (ArrayList<Parcelable>)
				getIntent().getParcelableArrayListExtra("placeList");
		
		ListItimizedOverlay itemizedOverlay = new ListItimizedOverlay(drawable, mapView, this, placeList);
		// TODO placeList = null
		for (Parcelable p : placeList) {
			Place place = (Place) p;
			GeoPoint geoPoint = new GeoPoint((int)(place.getLatitude() * 1e6),
					(int) (place.getLongitude() * 1e6));
			OverlayItem overlayitem = new OverlayItem(geoPoint, place.getName(),
					place.getAddress());
			itemizedOverlay.addOverlayItem(overlayitem);
		}

		
		mapView.setBuiltInZoomControls(true);
		List<Overlay> mapOverlays = mapView.getOverlays();
		mapOverlays.add(itemizedOverlay);

		MapController controller = mapView.getController();
		controller.zoomToSpan(itemizedOverlay.getLatSpanE6(), itemizedOverlay.getLonSpanE6());
		// use the newly defined getCenter method
		controller.setCenter(itemizedOverlay.getCenter());
	}

	@Override
	protected boolean isRouteDisplayed()
	{
		return false;
	}

}
