package omnicentre.eworky;

import java.util.ArrayList;

import omnicentre.eworky.localisations.Localisation;
import omnicentre.eworky.localisations.LocalisationsLoader;
import omnicentre.eworky.tools.Dialogs;
import omnicentre.eworky.tools.Redirections;
import omnicentre.eworky.tools.TitleBar;
import omnicentre.eworky.widgets.LocalisationArrayAdapter;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/**
 * Makes the search and displays the results of the search in a list.
 *
 */
public class SearchResults extends ListActivity {

    /**
     * The list of all the found places.
     */
    private ArrayList<Localisation> localisationsList;
    
    private TitleBar titleBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We create a loading dialog:
        ProgressDialog dialog = ProgressDialog.show(this, "", 
                "Loading. Please wait...", true);
        titleBar = new TitleBar(this);

        // We charge the data in an asynchronous task:
        (new LocalisationsLoader(this, dialog)).execute();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        // If an item on the list is clicked, we go to its detail page:
        Redirections.localisationDetails(this,localisationsList.get(position));
    }

    /**
     * This method displays the view. It has to be called once the data is
     * charged.
     * @param localisationsList the localisations list.
     * @param isOk tells if something bad happened.
     * @param error an eventual error message.
     */
    public void show(ArrayList<Localisation> localisationsList, boolean isOk,
            String error) {

        this.localisationsList = localisationsList;
        setListAdapter(new LocalisationArrayAdapter(getApplicationContext(),
                localisationsList));
        getListView().setTextFilterEnabled(true);
        titleBar.setTitleBar(R.layout.title_results, localisationsList);

        // If something went wrong we redirect to the main view:
        if(!isOk)
            Dialogs.newAlertToIndex("Error", error, this);
    }
}
