package omnicentre.eworky.tools;

import java.util.ArrayList;

import omnicentre.eworky.Index;
import omnicentre.eworky.Map;
import omnicentre.eworky.PlaceDetails;
import omnicentre.eworky.SearchResults;
import omnicentre.eworky.places.Place;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

/**
 * This class creates the intents to go from one view to another.
 * If the structure of the application changes, this class should be the only
 * one the be modified to redirects correctly.
 *
 */
public class Redirections {

    /**
     * Redirects to the main activity.
     * @param from the current activity.
     */
    public static void index(Activity from) {
        Intent intent = new Intent(from, Index.class);
        from.startActivity(intent);
    }

    /**
     * Redirects to the map activity.
     * @param from the current activity.
     * @param placeList the places list to display on the map.
     */
    public static void map(Activity from, ArrayList<Place> placeList) {
        Intent intent = new Intent(from, Map.class);
        intent.putParcelableArrayListExtra("placeList", placeList);
        from.startActivity(intent);
    }

    /**
     * Redirects to the search results activity.
     * @param from the current activity.
     * @param query the query for the search.
     */
    public static void searchResults(Activity from, String query) {
        Intent intent = new Intent(from, SearchResults.class);
        intent.putExtra("query", query);
        from.startActivity(intent);
    }

    /**
     * Redirects to the place details activity.
     * @param from the current activity.
     * @param place the {@link Place} object we want to know about.
     */
    public static void placeDetails(Activity from, Place place) {
        Intent intent = new Intent(from, PlaceDetails.class);
        intent.putExtra("place",  place);
        from.startActivity(intent);
    }
    
    /**
     * Redirects to the search results activity.
     * @param from the current activity.
     * @param placeList the places list to display on the list.
     */
    public static void searchResults(Activity from, ArrayList<Place>placeList){
        Intent intent = new Intent(from, SearchResults.class);
        intent.putParcelableArrayListExtra("placeList", placeList);
        from.startActivity(intent);
    }

    /**
     * Set an OnClickListener from the view that will redirect to the main
     * activity.
     * @param view the view which will have the listener.
     * @param from the current activity.
     */
    public static void setClickListenerToIndex(View view, Activity from) {

        final Activity activity = from;
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                index(activity);
            }
        });
    }

    /**
     * Set an OnClickListener from the view that will redirect to map
     * activity.
     * @param view the view which will have the listener.
     * @param from the current activity.
     * @param placeList the places list to display on the map.
     */
    public static void setClickListenerToMap(View view, Activity from,
            ArrayList<Place> placeList) {

        final Activity activity = from;
        final ArrayList<Place> p = placeList;
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                map(activity, p);
            }
        });
    }

    /**
     * Set an OnClickListener from the view that will redirect to the search
     * results activity.
     * @param view the view which will have the listener.
     * @param from the current activity.
     * @param search_bar_id the search {@link EditText} id.
     */
    public static void setClickListenerToSearchResults(View view,
            Activity from, int search_bar_id) {

        final Activity activity = from;
        final int id = search_bar_id;
        
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                EditText search_bar = (EditText) activity.findViewById(id);
                String query = search_bar.getText().toString();
                searchResults(activity, query);
            }
        });
    }
}
