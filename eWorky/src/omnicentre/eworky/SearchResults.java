package omnicentre.eworky;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import omnicentre.eworky.API.LocalisationJson;
import omnicentre.eworky.tools.Dialogs;
import omnicentre.eworky.tools.GPS;
import omnicentre.eworky.tools.MyItimizedOverlay;
import omnicentre.eworky.tools.MyOverlayItem;
import omnicentre.eworky.tools.Redirections;
import omnicentre.eworky.tools.SearchCriteria;
import omnicentre.eworky.tools.TitleBar;
import omnicentre.eworky.widgets.LocalisationArrayAdapter;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Makes the search and displays the results of the search in a list.
 *
 */
public class SearchResults extends MapActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We set the view, with the title bar:
        TitleBar.setContentView(this, R.layout.search_results,
                R.layout.title_results);

        // We set the listeners:
        SearchCriteria criteria = Redirections.getCriteria(this);

        TextView sort = (TextView) findViewById(R.id.sort);
        Redirections.setClickListenerToSort(sort, this, criteria);

        TextView crit = (TextView) findViewById(R.id.criteria);
        Redirections.setClickListenerToCriteria(crit, this, criteria);

        TextView kilometers = (TextView) findViewById(R.id.kilometers);
        Redirections.setClickListenerToBoundary(kilometers, this, criteria);
        kilometers.setText((int) criteria.getBoundary() + " km");

        // We set the list view:
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(listener);
        listView.setTextFilterEnabled(true);
        listView.setAdapter(new LocalisationArrayAdapter(
                getApplicationContext(), new ArrayList<LocalisationJson>()));

        // We charge the data in an asynchronous task:
        GPS.start(this, criteria);
    }

    /**
     * This listener redirects to the details of the clicked localisation.
     */
    private OnItemClickListener listener = new OnItemClickListener () {

        public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                long arg3) {    
            LocalisationJson l = (LocalisationJson)
                    arg0.getItemAtPosition(position);
            Redirections.localisationDetails(SearchResults.this, l.getId());
        }
    };

    /**
     * Refresh the activity with a new set of data.
     * @param localisationsList the list of localisations to show.
     */
    public void refresh(List<LocalisationJson> localisationsList) {
        
        
        // Map settings:
        MapView mapView = (MapView) this.findViewById(R.id.mapView);
        Drawable pin = this.getResources().getDrawable(R.drawable.ic_launcher);
        MyItimizedOverlay itemizedOverlay = new
                MyItimizedOverlay(pin, mapView, this); // displays the pins
        mapView.setBuiltInZoomControls(true); // add some zoom buttons

        for (LocalisationJson l : localisationsList)
            itemizedOverlay.addOverlayItem(new MyOverlayItem(l));
        mapView.getOverlays().clear();
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

    /**
     * Display an error.
     * @param error the error to show.
     */
    public void error(String error) {
        Dialogs.newAlertToIndex(getResources().getString(R.string.error),
                error, this);
    }
}
