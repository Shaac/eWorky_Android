package omnicentre.eworky.tools;

import omnicentre.eworky.localisations.Localisation;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

/**
 * An extension of {@link OverlayItem} that also has a {@link Localisation} object.
 *
 */
public class MyOverlayItem extends OverlayItem {
    
    private Localisation place;

    /**
     * Create a new OverlyItem.
     * @param place the {@link Localisation} object corresponding.
     */
    public MyOverlayItem(Localisation place) {
        super(new GeoPoint((int)(place.getLatitude() * 1e6),
                (int) (place.getLongitude() * 1e6)), place.getName(),
                place.getAddress());
        this.place = place;
    }

    /**
     * Get the {@link Localisation} object.
     * @return the {@link Localisation} object
     */
    public Localisation getPlace() {
        return place;
    }

}
