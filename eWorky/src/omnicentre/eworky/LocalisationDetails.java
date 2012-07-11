package omnicentre.eworky;

import java.util.ArrayList;

import omnicentre.eworky.localisations.Localisation;
import omnicentre.eworky.tools.Http;
import omnicentre.eworky.tools.Redirections;
import omnicentre.eworky.tools.TitleBar;
import omnicentre.eworky.widgets.AspectRatioImageView;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.drawable.Drawable;

/**
 * This activity shows everything there is to show about a localisation.
 *
 */
public class LocalisationDetails extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We get the place's data:
        Localisation l = Redirections.getLocalisation(this);
        ArrayList<Localisation> oneElementList = new ArrayList<Localisation>();
        oneElementList.add(l);

        // We set the view, with the title bar:
        TitleBar.setContentView(this, R.layout.details, R.layout.title_details,
                oneElementList);
        
        // We fill the fields:
        ((TextView) findViewById(R.id.name)).setText(l.getName());
        ((TextView) findViewById(R.id.address)).setText(l.getAddress());
        ((TextView) findViewById(R.id.type)).setText(l.getType());
        ((TextView) findViewById(R.id.city)).setText(l.getCity());
        ((TextView) findViewById(R.id.content)).setText(l.getDescription());
        ((TextView)findViewById(R.id.list)).setText(l.getAmenities().toText());

        Drawable image = Http.getImage(l.getImage());
        if (image != null)
            ((AspectRatioImageView)
                    findViewById(R.id.picture)).setImageDrawable(image);

    }
}
