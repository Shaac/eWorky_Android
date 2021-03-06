package omnicentre.eworky;

import java.io.InputStream;
import java.net.URL;

import omnicentre.eworky.API.FeatureJson;
import omnicentre.eworky.API.ImageJson;
import omnicentre.eworky.API.LocalisationJson;
import omnicentre.eworky.API.NoSuccessException;
import omnicentre.eworky.API.Requests;

import omnicentre.eworky.tools.Redirections;
import omnicentre.eworky.tools.TitleBar;
import omnicentre.eworky.widgets.AspectRatioImageView;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.drawable.Drawable;

// TODO redo entirely
/**
 * This activity shows everything there is to show about a localisation.
 *
 */
public class LocalisationDetails extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We get the place's data:
        LocalisationJson l = new LocalisationJson();
        try {
            l = Requests.details(Redirections.getLocalisationId(this));
        } catch (NoSuccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // We set the view, with the title bar:
        TitleBar.setContentView(this, R.layout.details,R.layout.title_details);
        
        // We fill the fields:
        ((TextView) findViewById(R.id.name)).setText(l.getName());
        ((TextView) findViewById(R.id.address)).setText(l.getAddress());
        ((TextView) findViewById(R.id.type)).setText(l.getType());
        ((TextView) findViewById(R.id.city)).setText(l.getCity());
        ((TextView) findViewById(R.id.content)).setText(l.getDescription());
        ((TextView)findViewById(R.id.list)).setText(FeatureJson.toText(
                l.getFeatures()));

        Drawable d = null;
        try {
            URL url = new URL(ImageJson.getThumbURL(l.getImages()));
            InputStream content = (InputStream)url.getContent();
            d = Drawable.createFromStream(content , "src"); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (d != null)
            ((AspectRatioImageView)
                    findViewById(R.id.picture)).setImageDrawable(d);

    }
}
