package omnicentre.eworky;

import omnicentre.eworky.tools.Redirections;
import omnicentre.eworky.tools.SearchCriteria;
import omnicentre.eworky.tools.TitleBar;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

/**
 * The search activity, which only contains search bar(s).
 *
 */
public class Search extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We get the layout corresponding to the type of search:
        int layout = Redirections.getWithName(this) ?
                R.layout.search_aux : R.layout.search;

        // We set the view, with the title bar:
        TitleBar.setContentView(this, layout, R.layout.title_search);

        // We listen to the search button:
        Button b = (Button) findViewById(R.id.search_button);
        Redirections.setClickListenerToSearchCriteria(b, this);
    }

    /**
     * Return the search criteria thanks to the search bar(s).
     * @return the search criteria. 
     */
    public SearchCriteria getCriteria() {
        
        EditText search_bar = (EditText) findViewById(R.id.search_main);
        EditText aux = (EditText) findViewById(R.id.search_aux);
        
        SearchCriteria criteria = new SearchCriteria();
        
        if (aux == null)
            criteria.setPlace(search_bar.getText().toString());
        else {
            criteria.setName(search_bar.getText().toString());
            criteria.setPlace(aux.getText().toString());
        }
        
        return criteria;
    }
}
