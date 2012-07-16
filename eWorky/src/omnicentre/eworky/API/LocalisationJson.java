package omnicentre.eworky.API;

import java.util.List;

/**
 * This class will contain the results. It should be the Java equivalent
 * of LocalisationJson on JsonModels.cs.
 *
 */
public class LocalisationJson {
    public int id;
    public String name;
    public double latitude;
    public double longitude;
    public String description;
    public String image;
    public String imageThumb;
    public String address;
    public String postalCode;
    public String city;
    public String type;
    public String url;
    public double rating;
    public List<String> amenities;
    public PricesJson prices;
    public List<OfferJson> offers;
    public OpeningTimesJson openingTimes;
    public List<CommentJson> comments;
    public List<MemberJson> fans;
    
    public LocalisationJson() {
    }
}
