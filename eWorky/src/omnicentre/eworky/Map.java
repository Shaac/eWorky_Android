package omnicentre.eworky;

import java.util.ArrayList;

import omnicentre.eworky.localisations.Localisation;
import omnicentre.eworky.localisations.MyItimizedOverlay;
import omnicentre.eworky.tools.MyOverlayItem;
import omnicentre.eworky.tools.Redirections;
import omnicentre.eworky.tools.TitleBar;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

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
        MyItimizedOverlay itemizedOverlay = new
                MyItimizedOverlay(pin, mapView, this); // displays the pins
        mapView.setBuiltInZoomControls(true); // add some zoom buttons

        // We get the localisations list:
        ArrayList<Localisation> localisationsList = 
                Redirections.getLocalisationsList(this);

        // We place the localisations:
        for (Localisation l : localisationsList)
            itemizedOverlay.addOverlayItem(new MyOverlayItem(l));
        mapView.getOverlays().add(itemizedOverlay);

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
