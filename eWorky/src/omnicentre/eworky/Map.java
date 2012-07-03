package omnicentre.eworky;

import java.util.List;

import omnicentre.eworky.tools.ListItimizedOverlay;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class Map extends MapActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Drawable drawable = this.getResources().getDrawable(R.drawable.ic_launcher);
		ListItimizedOverlay itemizedOverlay = new ListItimizedOverlay(drawable);

		GeoPoint geoPoint = new GeoPoint(-17595983, -149487411);
		OverlayItem overlayitem = new OverlayItem(geoPoint, "Hello from", "Tahiti");
		itemizedOverlay.addOverlayItem(overlayitem);

		MapView mapView = (MapView) this.findViewById(R.id.mapView);
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
