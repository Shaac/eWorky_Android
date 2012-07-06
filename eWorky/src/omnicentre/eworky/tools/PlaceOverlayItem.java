package omnicentre.eworky.tools;

import omnicentre.eworky.places.Place;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

/**
 * An extension of {@link OverlayItem} that also has a {@link Place} object.
 *
 */
public class PlaceOverlayItem extends OverlayItem {
    
    private Place place;

    /**
     * Create a new OverlyItem.
     * @param place the {@link Place} object corresponding.
     */
    public PlaceOverlayItem(Place place) {
        super(new GeoPoint((int)(place.getLatitude() * 1e6),
                (int) (place.getLongitude() * 1e6)), place.getName(),
                place.getAddress());
        this.place = place;
    }

    /**
     * Get the {@link Place} object.
     * @return the {@link Place} object
     */
    public Place getPlace() {
        return place;
    }

}
