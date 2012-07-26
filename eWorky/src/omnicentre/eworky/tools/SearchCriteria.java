package omnicentre.eworky.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchCriteria implements Parcelable {

    private String place = null;
    private String name = null;
    private double latitude = 10000;
    private double longitude = 10000;
    private double boundary = -1;
    private List<Integer> offerType = null;
    private int orderBy = -1;
    private List<Integer> types = null;
    private List<Integer> features = null;
    private int maxCount = -1;

    public SearchCriteria() {
    }

    public HashMap<String, String> getParams() {
        HashMap<String, String> params = new HashMap<String, String>();
        if (place != null && place != "")
            params.put("place", place);
        if (name != null && name != "")
            params.put("name", name);
        if (latitude != 10000)
            params.put("latitude", String.valueOf(latitude));
        if (longitude != 10000) 
            params.put("longitude", String.valueOf(longitude));
        if (boundary != -1)
            params.put("boundary", String.valueOf(boundary));
        if (offerType != null && !offerType.isEmpty())
            params.put("offerType",Arrays.toString(offerType.toArray()).replace(" ",""));
        if (orderBy != -1)
            params.put("orderBy", String.valueOf(orderBy));
        if (types != null && !types.isEmpty())
            params.put("types", Arrays.toString(types.toArray()).replace(" ", ""));
        if (features != null && !features.isEmpty())
            params.put("features", Arrays.toString(features.toArray()).replace(" ", ""));
        if (maxCount > 0)
            params.put("maxCount", String.valueOf(maxCount));
        return params;
    }

    public int describeContents() {
        // Needed to implement Parcelable but not useful.
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(place);
        out.writeString(name);
        out.writeDouble(latitude);
        out.writeDouble(longitude);
        out.writeDouble(boundary);
        out.writeList(offerType);
        out.writeInt(orderBy);
        out.writeList(types);
        out.writeList(features);
        out.writeInt(maxCount);
    }

    public static final Parcelable.Creator<SearchCriteria> CREATOR = new
            Parcelable.Creator<SearchCriteria>() {
        public SearchCriteria createFromParcel(Parcel in) {
            return new SearchCriteria(in);
        }

        public SearchCriteria[] newArray(int size) {
            return new SearchCriteria[size];
        }
    };

    private SearchCriteria(Parcel in) {
        place = in.readString();
        name = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        boundary = in.readDouble();
        offerType = new ArrayList<Integer>();
        in.readList(offerType, null);
        types = new ArrayList<Integer>();
        types = new ArrayList<Integer>();
        in.readList(types, null);
        features = new ArrayList<Integer>();
        in.readList(features, null);
        maxCount = in.readInt();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getBoundary() {
        if (boundary <= 0)
            return 50;
        else
            return boundary;
    }

    public void setBoundary(double boundary) {
        this.boundary = boundary;
    }

    public List<Integer> getOfferType() {
        return offerType;
    }

    public void setOfferType(List<Integer> offerType) {
        this.offerType = offerType;
    }

    public int getOrderBy() {
        if (orderBy == -1)
            return 1;
        else
            return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    public List<Integer> getTypes() {
        return types;
    }

    public void setTypes(List<Integer> types) {
        this.types = types;
    }

    public List<Integer> getFeatures() {
        return features;
    }

    public void setFeatures(List<Integer> features) {
        this.features = features;
    }

    public int getMaxCount() {
        if (maxCount == -1)
            return 30;
        else
            return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

}
