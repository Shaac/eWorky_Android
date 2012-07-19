package omnicentre.eworky.tools;

import omnicentre.eworky.API.LocalisationJson;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

/**
 * An extension of {@link OverlayItem} that also has a {@link LocalisationJson} object.
 *
 */
public class MyOverlayItem extends OverlayItem {
    
    private LocalisationJson place;

    /**
     * Create a new OverlyItem.
     * @param place the {@link LocalisationJson} object corresponding.
     */
    public MyOverlayItem(LocalisationJson place) {
        super(new GeoPoint((int)(place.getLatitude() * 1e6),
                (int) (place.getLongitude() * 1e6)), place.getName(),
                place.getAddress());
        this.place = place;
    }

    /**
     * Get the {@link LocalisationJson} object.
     * @return the {@link LocalisationJson} object
     */
    public LocalisationJson getPlace() {
        return place;
    }

}
