package omnicentre.eworky.tools;

import omnicentre.eworky.Index;
import omnicentre.eworky.Register;
import omnicentre.eworky.LocalisationDetails;
import omnicentre.eworky.MyAccount;
import omnicentre.eworky.R;
import omnicentre.eworky.Connect;
import omnicentre.eworky.Search;
import omnicentre.eworky.SearchOfferType;
import omnicentre.eworky.SearchResults;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewSwitcher;

/**
 * This class creates the intents to go from one view to another.
 * If the structure of the application changes, this class should be the only
 * one the be modified to redirects correctly.
 *
 */
public class Redirections {

    // Constants:

    /**
     * The constant for when we want an intent to redirect to my spaces.
     */
    public static final int MY_SPACES = 1;

    /**
     * The constant for when we want an intent to redirect to my account.
     */
    public static final int MY_ACCOUNT = 2;
    
    /**
     * The constant for when the action is from Facebook.
     */
    public static final int FACEBOOK = 32665;



    // View changers:

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
     */
    public static void map(Activity from) {
        ViewSwitcher switcher =
                (ViewSwitcher) from.findViewById(R.id.switcher);
        if (switcher != null)
            switcher.showNext();
    }

    public static void searchOfferType(Activity from, SearchCriteria criteria){
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
    
    public static void sort(SearchResults from, SearchCriteria criteria) {
        Dialogs.newSortAlert(from, criteria);
    }
    
    public static void boundary(SearchResults from, SearchCriteria criteria) {
        Dialogs.newBoundaryAlert(from, criteria);
    }
    
    public static void criteria(SearchResults from, SearchCriteria criteria) {
        Dialogs.newCriteriaAlert(from, criteria);
    }

    /**
     * Redirects to the place details activity.
     * @param from the current activity.
     * @param id the id of the localisation.
     */
    public static void localisationDetails(Activity from, int id) {
        Intent intent = new Intent(from, LocalisationDetails.class);
        intent.putExtra("omnicentre.eworki.id",  id);
        from.startActivity(intent);
    }

    public static void mySpaces(Activity from) {
        mySpaces(from, true);
    }
    public static void mySpaces(Activity from, boolean connect) {
        if (Storage.isConnected(from)) {
            // TODO redirect to my spaces
        } else if (connect) {
            Intent intent = new Intent(from, Connect.class);
            from.startActivityForResult(intent, MY_SPACES);
        }
    }

    public static void myAccount(Activity from) {
        myAccount(from, true);
    }
    public static void myAccount(Activity from, boolean connect) {
        if (Storage.isConnected(from)) {
            Intent intent = new Intent(from, MyAccount.class);
            from.startActivity(intent);
        } else if (connect) {
            Intent intent = new Intent(from, Connect.class);
            from.startActivityForResult(intent, MY_ACCOUNT);
        }
    }

    public static void register(Activity from) {
        Intent intent = new Intent(from, Register.class);
        from.startActivityForResult(intent, 0);
    }


    // Click Listeners:

    /**
     * Set an OnClickListener from the view that will redirect to the main
     * activity.
     * @param view the view which will have the listener.
     * @param from the current activity.
     */
    public static void setClickListenerToIndex(View view, final Activity from){

        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                index(from);
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
    public static void setClickListenerToSearch(View view, final Activity from,
            final boolean withName) {

        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                search(from, withName);
            }
        });
    }

    /**
     * Set an OnClickListener from the view that will redirect to map
     * activity.
     * @param view the view which will have the listener.
     * @param from the current activity.
     */
    public static void setClickListenerToMap(View view, final Activity from) {

        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                map(from);
            }
        });
    }

    public static void setClickListenerToSearchOfferType(View view,
            final Activity from, final SearchCriteria criteria) {

        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                searchOfferType(from, criteria);
            }
        });

    }

    public static void setClickListenerToSearchCriteria(View view,
            final Search from) {

        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                searchOfferType(from, from.getCriteria());
            }
        });
    }

    /**
     * Set an OnClickListener from the view that will redirect to the search
     * results activity.
     * @param view the view which will have the listener.
     * @param from the current activity.
     */
    public static void setClickListenerToSearchResults(View view,
            final SearchOfferType from) {

        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                searchResults(from, from.getCriteria());
            }
        });
    }

    /**
     * Set an OnClickListener from the view that will finish the activity
     * @param view the view which will have the listener.
     * @param from the current activity.
     */
    public static void setClickListenerToFinish(View view,
            final Activity from) {

        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                from.setResult(Activity.RESULT_OK, new Intent());
                from.finish();
            }
        });
    }

    public static void setClickListenerToMySpaces(View view,
            final Activity from) {

        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mySpaces(from);
            }
        });
    }

    public static void setClickListenerToMyAccount(View view,
            final Activity from) {

        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                myAccount(from);
            }
        });
    }

    public static void setClickListenerToRegister(View view,
            final Activity from) {

        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                register(from);
            }
        });

    }

    /**
     * Get the localisation given in the extras of the intent.
     * @param activity the current activity.
     * @return the wanted localisation.
     */
    public static int getLocalisationId(Activity activity) {
        return activity.getIntent().getExtras().getInt("omnicentre.eworki.id");
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

    public static void setClickListenerToFacebook(View view,
            final Activity activity) {
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Facebook.authorize(activity);
            }
        });

    }

    public static void setClickListenerToSort(View view,
            final SearchResults from, final SearchCriteria criteria) {
        
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                sort(from, criteria);
            }
        });
        
    }

    public static void setClickListenerToBoundary(View view,
            final SearchResults from, final SearchCriteria criteria) {

        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                boundary(from, criteria);
            }
        });  
    }

    public static void setClickListenerToCriteria(View view,
            final SearchResults from, final SearchCriteria criteria) {
        
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                criteria(from, criteria);
            }
        });
    }
    
    public static void share(Activity activity, String message, String subject) {
        Intent i = null;

        i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        i.putExtra(Intent.EXTRA_TEXT, message);
        i.putExtra(Intent.EXTRA_SUBJECT, subject);

        activity.startActivity(Intent.createChooser(i, "share"));
    }
}
