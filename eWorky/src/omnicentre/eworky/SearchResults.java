package omnicentre.eworky;

import java.util.ArrayList;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import omnicentre.eworky.API.LocalisationJson;
import omnicentre.eworky.localisations.LocalisationsLoader;
import omnicentre.eworky.localisations.MyItimizedOverlay;
import omnicentre.eworky.tools.Dialogs;
import omnicentre.eworky.tools.MyOverlayItem;
import omnicentre.eworky.tools.Redirections;
import omnicentre.eworky.tools.TitleBar;
import omnicentre.eworky.widgets.LocalisationArrayAdapter;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * Makes the search and displays the results of the search in a list.
 *
 */
public class SearchResults extends MapActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        TitleBar.setContentView(this, R.layout.search_results, R.layout.title_results);

        // We create a loading dialog:
        ProgressDialog dialog = ProgressDialog.show(this, "", 
                "Loading. Please wait...", true);



        // We charge the data in an asynchronous task:
        (new LocalisationsLoader(this, dialog)).execute();
    }

    private OnItemClickListener listener = new OnItemClickListener () {

        public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                long arg3) {
            LocalisationJson l = (LocalisationJson) arg0.getItemAtPosition(position);
            Redirections.localisationDetails(SearchResults.this, l.getId());
            

        }

    };

    /**
     * This method displays the view. It has to be called once the data is
     * charged.
     * @param localisationsList the localisations list.
     * @param isOk tells if something bad happened.
     * @param error an eventual error message.
     */
    public void show(ArrayList<LocalisationJson> localisationsList,
            String error) {

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(listener);
        listView.setAdapter(new LocalisationArrayAdapter(getApplicationContext(),
                localisationsList));
        listView.setTextFilterEnabled(true);

        // Map settings:
        MapView mapView = (MapView) this.findViewById(R.id.mapView);
        Drawable pin = this.getResources().getDrawable(R.drawable.ic_launcher);
        MyItimizedOverlay itemizedOverlay = new
                MyItimizedOverlay(pin, mapView, this); // displays the pins
        mapView.setBuiltInZoomControls(true); // add some zoom buttons

        for (LocalisationJson l : localisationsList)
            itemizedOverlay.addOverlayItem(new MyOverlayItem(l));
        mapView.getOverlays().add(itemizedOverlay);


        // If something went wrong we redirect to the main view:
        if(error.length() > 0)
            Dialogs.newAlertToIndex("Error", error, this);

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
