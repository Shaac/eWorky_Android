package omnicentre.eworky.widgets;

import omnicentre.eworky.R;
import omnicentre.eworky.tools.Redirections;
import omnicentre.eworky.tools.SearchCriteria;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * This class is used to display the list on the home activity.
 * Also manage the on click listeners.
 *
 */
public class IndexArrayAdapter extends ArrayAdapter<String> {

    /**
     * The activity where the list is displayed.
     */
    private final Activity activity;

   
    /**
     * Construct the {@link ArrayAdapter} for the menu and set listeners.
     * @param activity the activity.
     */
    public IndexArrayAdapter(Activity activity) {
        super(activity.getApplicationContext(), R.layout.index_list,
                new String [3]);
        this.activity = activity;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        
        // We inflate the view:
        LayoutInflater inflater = (LayoutInflater) activity
                .getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.index_list, parent, false);
        
        // We write in it:
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView description = (TextView) view.findViewById(R.id.description);
        switch(position) {
        // TODO: put those strings in res directory.
        case 0:
            name.setText("Autour de moi");
            description.setText("Cherche des lieux de travail autour de moi.");
            Redirections.setClickListenerToSearchOfferType(view, activity,
                    new SearchCriteria());
            break;
        case 1:
            name.setText("Rechercher un lieu");
            description.setText("Entrez un endroit spécifique où chercher.");
            Redirections.setClickListenerToSearch(view, activity, false);
            break;
        case 2:
            name.setText("Rechecher un nom");
            description.setText("Cherche des lieux de travail par leur nom.");
            Redirections.setClickListenerToSearch(view, activity, true);
        }

        return view;
    }
}
