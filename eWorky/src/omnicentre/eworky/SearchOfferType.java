package omnicentre.eworky;

import java.util.ArrayList;
import java.util.List;

import omnicentre.eworky.tools.Redirections;
import omnicentre.eworky.tools.SearchCriteria;
import omnicentre.eworky.tools.TitleBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * This activity displays the buttons in order to choose which offer we want.
 *
 */
public class SearchOfferType extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We set the view, with the title bar:
        TitleBar.setContentView(this, R.layout.search_offer_type, R.layout.title_search);

        Button b = (Button) findViewById(R.id.button);
        Redirections.setClickListenerToSearchResults(b, this);
    }

    /**
     * This method returns the parameter offerType for the search. This
     * parameter is computed thanks to the check boxes. It is also completed
     * by the parameters from the previous activities.
     * @return the parameters.
     */
    public SearchCriteria getCriteria() {
        
        // We get the previous list of parameters, which may contain the name
        // of the searched location, or its place:
        SearchCriteria criteria = Redirections.getCriteria(this);

        // We look at the check boxes:
        List<Integer> list = new ArrayList<Integer>();
        if (((CheckBox) findViewById(R.id.wifi)).isChecked())
            list.add(0);
        if (((CheckBox) findViewById(R.id.meeting)).isChecked())
            list.add(4);
        if (((CheckBox) findViewById(R.id.desktop)).isChecked()) {
            list.add(2);
            list.add(3);
        }
        
        // We construct the new parameter:
        if (! list.isEmpty())
            criteria.setOfferType(list);
        return criteria;
    }
}
