package omnicentre.eworky;

import java.io.InputStream;
import java.net.URL;

import omnicentre.eworky.places.Place;
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

        TitleBar titleBar = new TitleBar(this);
        setContentView(R.layout.place_details);

        Place p = getIntent().getExtras().getParcelable("place");
        
        // We fill the fields:
        ((TextView) findViewById(R.id.name)).setText(p.getName());
        ((TextView) findViewById(R.id.address)).setText(p.getAddress());
        ((TextView) findViewById(R.id.type)).setText(p.getType());
        ((TextView) findViewById(R.id.city)).setText(p.getCity());
        ((TextView) findViewById(R.id.content)).setText(p.getDescription());
        ((TextView)findViewById(R.id.list)).setText(p.getAmenities().toText());
        
        if (p.getImage().length() > 0) {
            URL url;
            Drawable d = null;
            try {
                url = new URL(p.getImage());
                InputStream content = (InputStream)url.getContent();
                d = Drawable.createFromStream(content , "src"); 
            } catch (Exception e) {
                e.printStackTrace();
            }
            ((AspectRatioImageView)
                    findViewById(R.id.picture)).setImageDrawable(d);
        }
        titleBar.setTitleBar(R.layout.title_index);
    }

}
