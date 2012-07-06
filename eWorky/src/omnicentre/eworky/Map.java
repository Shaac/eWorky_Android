package omnicentre.eworky;

import java.util.ArrayList;

import omnicentre.eworky.places.Place;
import omnicentre.eworky.places.PlaceItimizedOverlay;
import omnicentre.eworky.tools.PlaceOverlayItem;
import omnicentre.eworky.tools.TitleBar;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;

/**
 * This activity displays a map.
 *
 */
public class Map extends MapActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We set the view, with the title bar:
        TitleBar.setContentView(this, R.layout.map, R.layout.title_map);

        // We initiate the map parameters:
        MapView mapView = (MapView) this.findViewById(R.id.mapView);
        Drawable pin = this.getResources().getDrawable(R.drawable.ic_launcher);
        PlaceItimizedOverlay itemizedOverlay = new
                PlaceItimizedOverlay(pin, mapView, this); // displays the pins
        mapView.setBuiltInZoomControls(true); // add some zoom buttons

        // We get the places list:
        ArrayList<Parcelable> placeList = (ArrayList<Parcelable>)
                getIntent().getParcelableArrayListExtra("placeList");

        // We place the places:
        if (placeList != null) {
            for (Parcelable p : placeList) {
                PlaceOverlayItem overlayitem = new PlaceOverlayItem((Place) p);
                itemizedOverlay.addOverlayItem(overlayitem);
            }
            mapView.getOverlays().add(itemizedOverlay);
        }

        // We go to the right part of the map:
        MapController controller = mapView.getController();
        controller.setCenter(itemizedOverlay.getCenter());
        controller.zoomToSpan(itemizedOverlay.getLatSpanE6(),
                itemizedOverlay.getLonSpanE6());
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

}
