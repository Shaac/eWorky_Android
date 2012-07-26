package omnicentre.eworky.tools;

import omnicentre.eworky.API.LocalisationJson;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

/**
 * An extension of {@link OverlayItem} that also has a {@link LocalisationJson} object.
 *
 */
public class MyOverlayItem extends OverlayItem {
    
    private LocalisationJson localisation;

    /**
     * Create a new OverlyItem.
     * @param localisation the {@link LocalisationJson} object corresponding.
     */
    public MyOverlayItem(LocalisationJson localisation) {
        super(new GeoPoint((int)(localisation.getLatitude() * 1e6),
                (int) (localisation.getLongitude() * 1e6)), localisation.getName(),
                localisation.getAddress());
        this.localisation = localisation;
    }

    /**
     * Get the {@link LocalisationJson} object.
     * @return the {@link LocalisationJson} object
     */
    public LocalisationJson getLocalisation() {
        return localisation;
    }

}
