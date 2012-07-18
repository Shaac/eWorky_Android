package omnicentre.eworky;

import java.util.ArrayList;

import omnicentre.eworky.API.LocalisationJson;
import omnicentre.eworky.localisations.Localisation;
import omnicentre.eworky.localisations.LocalisationsLoader;
import omnicentre.eworky.tools.Dialogs;
import omnicentre.eworky.tools.Redirections;
import omnicentre.eworky.tools.TitleBar;
import omnicentre.eworky.widgets.LocalisationArrayAdapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * Makes the search and displays the results of the search in a list.
 *
 */
public class SearchResults extends Activity {

    /**
     * The list of all the found places.
     */
    private ArrayList<Localisation> localisationsList = new ArrayList<Localisation>();

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
            Redirections.localisationDetails(SearchResults.this,
                    localisationsList.get(position));

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

        //this.localisationsList = localisationsList;
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(listener);
        listView.setAdapter(new LocalisationArrayAdapter(getApplicationContext(),
                localisationsList));
        listView.setTextFilterEnabled(true);
        //titleBar.setTitleBar(R.layout.title_results);//, localisationsList);

        // If something went wrong we redirect to the main view:
        if(error.length() > 0)
            Dialogs.newAlertToIndex("Error", error, this);
    }
}
