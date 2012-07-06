package omnicentre.eworky;

import java.util.ArrayList;

import omnicentre.eworky.places.Place;
import omnicentre.eworky.tools.Http;
import omnicentre.eworky.tools.TitleBar;
import omnicentre.eworky.widgets.AspectRatioImageView;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.drawable.Drawable;

/**
 * This activity shows everything there is to show about a place.
 *
 */
public class PlaceDetails extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We get the place's data:
        Place p = (Place) getIntent().getExtras().getParcelable("place");
        ArrayList<Place> oneElementArrayList = new ArrayList<Place>();
        oneElementArrayList.add(p);

        // We set the view, with the title bar:
        TitleBar.setContentView(this, R.layout.details, R.layout.title_details,
                oneElementArrayList);
        
        // We fill the fields:
        ((TextView) findViewById(R.id.name)).setText(p.getName());
        ((TextView) findViewById(R.id.address)).setText(p.getAddress());
        ((TextView) findViewById(R.id.type)).setText(p.getType());
        ((TextView) findViewById(R.id.city)).setText(p.getCity());
        ((TextView) findViewById(R.id.content)).setText(p.getDescription());
        ((TextView)findViewById(R.id.list)).setText(p.getAmenities().toText());

        Drawable image = Http.getImage(p.getImage());
        if (image != null)
            ((AspectRatioImageView)
                    findViewById(R.id.picture)).setImageDrawable(image);

    }
}
