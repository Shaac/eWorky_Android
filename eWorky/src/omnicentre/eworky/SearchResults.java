package omnicentre.eworky;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import omnicentre.eworky.API.LocalisationJson;
import omnicentre.eworky.localisations.MyItimizedOverlay;
import omnicentre.eworky.tools.Dialogs;
import omnicentre.eworky.tools.GPS;
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

        // We set the title bar:
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

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(listener);
        listView.setTextFilterEnabled(true);

        // We charge the data in an asynchronous task:
        (new GPS(this, criteria)).execute();
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
     * This method displays the view. It has to be called once the data is
     * charged.
     * @param localisationsList the localisations list.
     * @param isOk tells if something bad happened.
     * @param error an eventual error message.
     */
    public void show(ArrayList<LocalisationJson> localisationsList,
            String error) {

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

    public void refresh(List<LocalisationJson> localisationsList) {
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(new LocalisationArrayAdapter(getApplicationContext(),
                (ArrayList<LocalisationJson>) localisationsList));
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

    public void error(String error) {
        Dialogs.newAlertToIndex(getResources().getString(R.string.error),
                error, this);
    }
}
