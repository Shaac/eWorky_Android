package omnicentre.eworky.API;

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
    public String[] amenities;
    public PricesJson prices;
    public OfferJson[] offers;
    public OpeningTimesJson openingTimes;
    public CommentJson[] comments;
    public MemberJson[] fans;
    
    public LocalisationJson(int id, String name, double latitude,
            double longitude, String description, String image,
            String imageThumb, String address, String postalCode, String city,
            String type, String url, double rating, String[] amenities,
            PricesJson prices, OfferJson[] offers,
            OpeningTimesJson openingTimes, CommentJson[] comments,
            MemberJson[] fans) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.image = image;
        this.imageThumb = imageThumb;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.type = type;
        this.url = url;
        this.rating = rating;
        this.amenities = amenities;
        this.prices = prices;
        this.offers = offers;
        this.openingTimes = openingTimes;
        this.comments = comments;
        this.fans = fans;
    }
}
