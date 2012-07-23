package omnicentre.eworky.widgets;

import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import omnicentre.eworky.R;
import omnicentre.eworky.API.LocalisationJson;
import omnicentre.eworky.localisations.Amenities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * This class is used to display the layout of an item of the locations list.
 *
 */
public class LocalisationArrayAdapter extends ArrayAdapter<LocalisationJson> {

    /**
     * The application's context.
     */
    private final Context context;

    /**
     * Construct the {@link ArrayAdapter} for the places list.
     * @param context the context of the activity.
     * @param localisations the places list.
     */
    public LocalisationArrayAdapter(Context context,
            ArrayList<LocalisationJson> localisations) {
        super(context, R.layout.list, localisations);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // We inflate the view:
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list, parent, false);

        // We write the texts:
        LocalisationJson l = this.getItem(position);
        ((TextView) view.findViewById(R.id.name)).setText(l.getName());
        ((TextView) view.findViewById(R.id.type)).setText(l.getType());
        DecimalFormat f = new DecimalFormat("0.00");
        ((TextView) view.findViewById(R.id.distance)).setText(
                f.format(l.getDistance()) + " km");
        ((TextView) view.findViewById(R.id.address)).setText(
                " - " + l.getCity() + " (" + l.getPostalCode() + ")");

        // We choose whether we show the prices or the amenities:
        boolean b = l.getOffers().isEmpty();
        Amenities a = new Amenities((ArrayList<String>) l.getAmenities());

        if (!b || !a.hasFreeWifi())
            ((ImageView) view.findViewById(R.id.wifi)).setVisibility(
                    View.GONE);

        if (!b || !a.hasWifi())
            ((ImageView) view.findViewById(R.id.wifi_euros)).setVisibility(
                    View.GONE);

        if (!b || !a.hasFood())
            ((ImageView) view.findViewById(R.id.food)).setVisibility(
                    View.GONE);

        if (!b || !a.hasCoffee())
            ((ImageView) view.findViewById(R.id.coffee)).setVisibility(
                    View.GONE);

        if (!b || !a.hasParking())
            ((ImageView) view.findViewById(R.id.parking)).setVisibility(
                    View.GONE);

        if (!b || !a.hasAccess())
            ((ImageView) view.findViewById(R.id.access)).setVisibility(
                    View.GONE);

        ImageView imageView = (ImageView) view.findViewById(R.id.picture);
        Drawable d = null;
        try {
            URL url = new URL(l.getImageThumb());
            InputStream content = (InputStream)url.getContent();
            d = Drawable.createFromStream(content , "src"); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        imageView.setImageDrawable(d);

        // We display the stars:
        if (l.getRating() > 0) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.
                    LayoutParams((int)(16 * l.getRating()), 30);
            layoutParams.addRule(RelativeLayout.BELOW, R.id.picture);
            ((ImageView) view.findViewById(R.id.stars_blue)).setLayoutParams(
                    layoutParams);
        } else {
            ((ImageView) view.findViewById(R.id.stars_blue)).setVisibility(
                    View.GONE);
            ((ImageView) view.findViewById(R.id.stars)).setVisibility(
                    View.GONE);
        }

        return view;
    }
}
