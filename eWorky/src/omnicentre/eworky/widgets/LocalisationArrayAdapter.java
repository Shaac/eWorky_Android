package omnicentre.eworky.widgets;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import omnicentre.eworky.R;
import omnicentre.eworky.localisations.Localisation;
import omnicentre.eworky.localisations.PlaceList;

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
 * This class is used to display the layout of an item of the places list.
 *
 */
public class LocalisationArrayAdapter extends ArrayAdapter<String> {
    
    private final Context context;
    private final ArrayList<Localisation> places;

    /**
     * Construct the {@link ArrayAdapter} for the places list.
     * @param context the context of the activity.
     * @param places the places list.
     */
    public LocalisationArrayAdapter(Context context, ArrayList<Localisation> places) {
        super(context, R.layout.list, (new PlaceList(places)).toStrings());
        this.context = context;
        this.places = places;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Localisation p = places.get(position);

        View view = inflater.inflate(R.layout.list, parent, false);


        ((TextView) view.findViewById(R.id.name)).setText(p.getName());
        ((TextView) view.findViewById(R.id.type)).setText(p.getType());
        ((TextView) view.findViewById(R.id.description)).setText(
                p.getDescription());
        ((TextView) view.findViewById(R.id.distance)).setText(p.getDistance()
                + " km");
        ImageView imageView = (ImageView) view.findViewById(R.id.picture);
        TextView address = (TextView) view.findViewById(R.id.address);
        String add = (p.getType().length() > 0 ? " - ":"") + p.getAddress();
        add += (p.getCity().length() > 0 ? " - ":"") + p.getCity();
        address.setText(add);

        if (!p.getAmenities().hasFreeWifi())
            ((ImageView) view.findViewById(R.id.wifi)).setVisibility(View.GONE);

        if (!p.getAmenities().hasWifi())
            ((ImageView) view.findViewById(R.id.wifi_euros)).setVisibility(View.GONE);

        if (!p.getAmenities().hasFood())
            ((ImageView) view.findViewById(R.id.food)).setVisibility(View.GONE);

        if (!p.getAmenities().hasCoffee())
            ((ImageView) view.findViewById(R.id.coffee)).setVisibility(View.GONE);

        if (!p.getAmenities().hasParking())
            ((ImageView) view.findViewById(R.id.parking)).setVisibility(View.GONE);

        if (!p.getAmenities().hasAccess())
            ((ImageView) view.findViewById(R.id.access)).setVisibility(View.GONE);
        URL url;
        Drawable d = null;
        try {
            url = new URL(p.getImage());
            InputStream content = (InputStream)url.getContent();
            d = Drawable.createFromStream(content , "src"); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        imageView.setImageDrawable(d);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (16 * p.getRating()), 30);
        layoutParams.addRule(RelativeLayout.BELOW, R.id.picture);
        ((ImageView) view.findViewById(R.id.stars_blue)).setLayoutParams(layoutParams);

        return view;
    }
}
