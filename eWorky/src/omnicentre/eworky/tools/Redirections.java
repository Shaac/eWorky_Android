package omnicentre.eworky.tools;

import java.util.ArrayList;

import omnicentre.eworky.Index;
import omnicentre.eworky.Map;
import omnicentre.eworky.places.Place;
import android.app.Activity;
import android.content.Intent;

/**
 * This class creates the intents to go from one view to another.
 * If the structure of the application changes, this class should be the only
 * one the be modified to redirects correctly.
 *
 */
public final class Redirections {

    /**
     * Redirects to the main view.
     * @param from the current activity.
     */
    public static void index(Activity from) {
        Intent intent = new Intent(from, Index.class);
        from.startActivity(intent);
    }

    /**
     * Redirects to the map view.
     * @param from the current activity.
     * @param placeList the places list to display on the map.
     */
    public static void map(Activity from, ArrayList<Place> placeList) {
        Intent intent = new Intent(from, Map.class);
        intent.putParcelableArrayListExtra("placeList", placeList);
        from.startActivity(intent);
    }
}
