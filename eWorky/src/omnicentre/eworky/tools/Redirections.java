package omnicentre.eworky.tools;

import java.util.ArrayList;

import omnicentre.eworky.Index;
import omnicentre.eworky.Map;
import omnicentre.eworky.LocalisationDetails;
import omnicentre.eworky.Search;
import omnicentre.eworky.SearchOfferType;
import omnicentre.eworky.SearchResults;
import omnicentre.eworky.localisations.Localisation;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;

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
     * Redirects to the search activity.
     * @param from the current activity.
     * @param withName whether or not we want a search bar for the name.
     */
    public static void search(Activity from, boolean withName) {
        Intent intent = new Intent(from, Search.class);
        intent.putExtra("omnicentre.eworki.withName", withName);
        from.startActivity(intent);
    }

    /**
     * Redirects to the map activity.
     * @param from the current activity.
     * @param placeList the places list to display on the map.
     */
    public static void map(Activity from, ArrayList<Localisation> placeList) {
        Intent intent = new Intent(from, Map.class);
        intent.putParcelableArrayListExtra(
                "omnicentre.eworki.localisationsList", placeList);
        from.startActivity(intent);
    }

    public static void searchOfferType(Activity from, SearchCriteria criteria) {
        Intent intent = new Intent(from, SearchOfferType.class);
        intent.putExtra("omnicentre.eworki.criteria", criteria);
        from.startActivity(intent);
    }

    /**
     * Redirects to the search results activity.
     * @param from the current activity.
     * @param params the parameters for the search.
     */
    public static void searchResults(Activity from, SearchCriteria criteria) {
        Intent intent = new Intent(from, SearchResults.class);
        intent.putExtra("omnicentre.eworki.criteria", criteria);
        from.startActivity(intent);
    }

    /**
     * Redirects to the place details activity.
     * @param from the current activity.
     * @param localisation the {@link Localisation} object we want to know about.
     */
    public static void localisationDetails(Activity from,
            Localisation localisation) {
        Intent intent = new Intent(from, LocalisationDetails.class);
        intent.putExtra("omnicentre.eworki.localisation",  localisation);
        from.startActivity(intent);
    }

    /**
     * Redirects to the search results activity.
     * @param from the current activity.
     * @param localisationsList the localisations list to display on the list.
     */
    public static void searchResults(Activity from,
            ArrayList<Localisation> localisationsList){
        Intent intent = new Intent(from, SearchResults.class);
        intent.putParcelableArrayListExtra("omnicentre.eworki.localisationsList",
                localisationsList);
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
     * Set an OnClickListener from the view that will redirect to the search
     * activity.
     * @param view the view which will have the listener.
     * @param from the current activity.
     * @param withName whether or not we want a search bar for the name.
     */
    public static void setClickListenerToSearch(View view, Activity from,
            boolean withName) {

        final Activity activity = from;
        final boolean b = withName;
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                search(activity, b);
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
            ArrayList<Localisation> placeList) {

        final Activity activity = from;
        final ArrayList<Localisation> p = placeList;
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                map(activity, p);
            }
        });
    }

    public static void setClickListenerToSearchOfferType(View view,
            Activity from, SearchCriteria criteria) {

        final Activity activity = from;
        final SearchCriteria c = criteria;

        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                searchOfferType(activity, c);
            }
        });

    }

    public static void setClickListenerToSearchCriteria(View view,
            Search from) {
        final Search activity = from;

        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                searchOfferType(activity, activity.getCriteria());
            }
        });
    }

    /**
     * Set an OnClickListener from the view that will redirect to the search
     * results activity.
     * @param view the view which will have the listener.
     * @param from the current activity.
     * @param params the parameters for the search.
     */
    public static void setClickListenerToSearchResults(View view,
            SearchOfferType from) {

        final SearchOfferType activity = from;

        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                searchResults(activity, activity.getCriteria());
            }
        });
    }

    /**
     * Set an OnClickListener from the view that will finish the activity
     * @param view the view which will have the listener.
     * @param from the current activity.
     */
    public static void setClickListenerToPrevious(View view, Activity from) {

        final Activity activity = from;

        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    /**
     * Get the localisation given in the extras of the intent.
     * @param activity the current activity.
     * @return the wanted localisation.
     */
    public static Localisation getLocalisation(Activity activity) {
        return (Localisation)
                activity.getIntent().getExtras().getParcelable("omnicentre.eworki.localisation");
    }

    public static ArrayList<Localisation> getLocalisationsList (Activity
            activity) {
        ArrayList<Parcelable> l = (ArrayList<Parcelable>) activity.getIntent().
                getParcelableArrayListExtra("omnicentre.eworki.localisationsList");
        ArrayList<Localisation> ret = new ArrayList<Localisation>();
        if (l != null) {
            for (Parcelable p : l)
                ret.add((Localisation) p);
        }
        return ret;
    }

    public static SearchCriteria getCriteria(Activity activity) {
        Parcelable p = activity.getIntent().getExtras().getParcelable(
                "omnicentre.eworki.criteria");

        SearchCriteria criteria = new SearchCriteria();
        if (p != null)       
            criteria = (SearchCriteria) p;

        return criteria;
    }

    public static boolean getWithName(Activity activity) {
        return activity.getIntent().getExtras().getBoolean("omnicentre.eworki.withName");
    }
}
