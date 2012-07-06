package omnicentre.eworky;

import java.util.ArrayList;
import java.util.List;

import omnicentre.eworky.places.Place;
import omnicentre.eworky.places.PlaceItimizedOverlay;
import omnicentre.eworky.tools.PlaceOverlayItem;
import omnicentre.eworky.tools.TitleBar;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

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
		
		PlaceItimizedOverlay itemizedOverlay = new PlaceItimizedOverlay(drawable, mapView, this);
		// TODO placeList = null
		for (Parcelable p : placeList) {
			PlaceOverlayItem overlayitem = new PlaceOverlayItem((Place) p);
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
	protected boolean isRouteDisplayed() {
		return false;
	}

}
