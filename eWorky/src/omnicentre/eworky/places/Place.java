package omnicentre.eworky.places;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import omnicentre.eworky.tools.Http;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class contains everything we need about places: all we have to know
 * and a lots of methods.
 *
 */
public class Place implements Parcelable {

    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private String description;
    private String image;
    private String address;
    private String city;
    private String type;
    private String url;
    private double rating;
    private Amenities amenities;
    //private comments
    //private fans

    private double distance;

    /**
     * Create an {@link Place} object from the data in the JSON.
     * @param json the JSON containing all the place's data.
     * @throws JSONException if we can not get id, name, latitude or longitude.
     */
    public Place(JSONObject json) throws JSONException {

        // These fields are mandatory and cause a failure of the function :
        id = json.getInt("id");
        name = json.getString("name");
        latitude = json.getDouble("latitude");
        longitude = json.getDouble("longitude");

        // These are not and can be set to void if nonexistent:
        try                     { description = json.getString("description");}
        catch (JSONException e) { description = ""; }
        try                     { image = json.getString("image"); }
        catch (JSONException e) { image = ""; }
        try                     { address = json.getString("address"); }
        catch (JSONException e) { address = ""; }
        try                     { city = json.getString("city"); }
        catch (JSONException e) { city = ""; }
        try                     { type = json.getString("type"); }
        catch (JSONException e) { type = ""; }
        try                     { url = json.getString("url"); }
        catch (JSONException e) { url = ""; }
        try                     { rating = json.getDouble("rating"); }
        catch (JSONException e) { rating = (-1); }
        try {
            amenities = new Amenities(json.getString("amenities"));
        } catch (JSONException e) {
            amenities = new Amenities(); }

        if (description.equals("null"))
            description = "";

        if (image.equals(""))
            image = "http://www.eworky.fr/Content/images/no_image.png";
    }

    /**
     * Compute the distance between this place and an other.
     * @param latitude the other place's latitude.
     * @param longitude the other place's longitude.
     */
    public void computeDistance(double latitude, double longitude) {
        Location pointA = new Location("A");  
        pointA.setLatitude(this.latitude);
        pointA.setLongitude(this.longitude);

        Location pointB = new Location("B");  
        pointB.setLatitude(latitude);
        pointB.setLongitude(longitude);

        // We set the result in kilometers and keep two digits after the point:
        distance = ((double) Math.round(pointA.distanceTo(pointB) / 10)) / 100;
    }

    /**
     * Parse a JSON array into a list of Place objects.
     * @param jsonArray the JSON array to be parsed.
     * @return an ArrayList of Place objects corresponding to the JSON data.
     */
    public static ArrayList<Place> parseList(JSONArray jsonArray) {

        ArrayList<Place> list = new ArrayList<Place> ();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                list.add(new Place(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {}
        }

        return list;
    }

    /**
     * Get a list of places from a query thanks to API.
     * @param query the content of the search.
     * @return an ArrayList of Place objects corresponding to the search.
     * @throws ClientProtocolException in case of an HTTP error protocol.
     * @throws IOException if we can not get to the server.
     * @throws JSONException if the JSON from the server is invalid.
     */
    public static ArrayList<Place> fromQuery(String query)
            throws ClientProtocolException, IOException, JSONException {

        // We get the content of the API:
        String url = "http://www.eworky.com/api/localisation/search?place=" +
                URLEncoder.encode(query, "UTF-8") + "&json=1";
        String page = Http.getUrl(url);

        // We select the JSON array containing all we want: 
        JSONObject json= new JSONObject(page);
        JSONArray jsonArray = json.getJSONArray("response");

        // We parse it:
        return parseList(jsonArray);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(name);
        out.writeDouble(latitude);
        out.writeDouble(longitude);
        out.writeString(description);
        out.writeString(image);
        out.writeString(address);
        out.writeString(city);
        out.writeString(type);
        out.writeString(url);
        out.writeDouble(rating);
        out.writeStringList((ArrayList<String>) amenities);
    }

    public static final Parcelable.Creator<Place> CREATOR =
            new Parcelable.Creator<Place>() {

        public Place createFromParcel(Parcel source) {
            return new Place(source);
        }

        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    /**
     * Create the object from a parcel.
     * @param in the parcel.
     */
    public Place(Parcel in) {
        id = in.readInt();
        name = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        description = in.readString();
        image = in.readString();
        address = in.readString();
        city = in.readString();
        type = in.readString();
        url = in.readString();
        rating = in.readDouble();
        ArrayList<String> a = new ArrayList<String>();
        in.readStringList(a);
        amenities = new Amenities(a);
    }


    // To the end of the file they are only getters:

    /**
     * Get the place's id.
     * @return the place's id.
     */
    public int getId() {
        return id;
    }

    /**
     * Get the place's name.
     * @return the place's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the place's latitude.
     * @return the place's latitude.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Get the place's longitude.
     * @return the place's longitude.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Get the place's description.
     * @return the place's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the place's image URL.
     * @return the place's image URL.
     */
    public String getImage() {
        return image;
    }

    /**
     * Get the place's address.
     * @return the place's address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get the place's city.
     * @return the place's city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Get the place's type.
     * @return the place's type.
     */
    public String getType() {
        return type;
    }

    /**
     * Get the place's URL in eWorky.
     * @return the place's URL in eWorky.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Get the place's rating.
     * @return the place's rating.
     */
    public double getRating() {
        return rating;
    }

    /**
     * Get the place's amenities.
     * @return the place's amenities.
     */
    public Amenities getAmenities() {
        return amenities;
    }

    /**
     * Get the place's distance to the search point.
     * @return the place's distance to the search point.
     */
    public double getDistance() {
        return distance;
    }
}
