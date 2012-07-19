package omnicentre.eworky.API;

import java.util.List;

/**
 * This class will contain the results. It should be the Java equivalent
 * of LocalisationJson on JsonModels.cs.
 *
 */
public class LocalisationJson {
    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private String description;
    private String image;
    private String imageThumb;
    private String address;
    private String postalCode;
    private String city;
    private double distance;
    private String type;
    private boolean isFree;
    private String url;
    private double rating;
    private List<String> amenities;
    private PricesJson prices;
    private List<OfferJson> offers;
    private OpeningTimesJson openingTimes;
    private List<CommentJson> comments;
    private List<MemberJson> fans;

    public LocalisationJson() {
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getImageThumb() {
        return imageThumb;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getType() {
        return type;
    }

    public double getDistance() {
        return distance;
    }

    public String getCity() {
        return city;
    }

    public boolean isFree() {
        return isFree;
    }

    public String getUrl() {
        return url;
    }

    public double getRating() {
        return rating;
    }

    public List<String> getAmenities() {
        return amenities;
    }


    public PricesJson getPrices() {
        return prices;
    }


    public List<OfferJson> getOffers() {
        return offers;
    }

    public OpeningTimesJson getOpeningTimes() {
        return openingTimes;
    }

    public List<CommentJson> getComments() {
        return comments;
    }

    public List<MemberJson> getFans() {
        return fans;
    }
}
