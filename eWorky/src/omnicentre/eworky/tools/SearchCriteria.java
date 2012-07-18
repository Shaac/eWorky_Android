package omnicentre.eworky.tools;

import java.util.Arrays;
import java.util.HashMap;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchCriteria implements Parcelable {

    private String place = null;
    private String name = null;
    private double latitude = 10000;
    private double longitude = 10000;
    private double boundary = -1;
    private int[] offerType = null;
    private int orderBy = -1;
    private int[] types = null;
    private int[] features = null;
    private int maxCount = -1;

    public SearchCriteria() {
    }

    public HashMap<String, String> getParams() {
        HashMap<String, String> params = new HashMap<String, String>();
        if (place != null)
            params.put("place", place);
        if (name != null)
            params.put("name", name);
        if (latitude != 10000)
            params.put("latitude", String.valueOf(latitude));
        if (longitude != 10000) 
            params.put("longitude", String.valueOf(longitude));
        if (boundary != -1)
            params.put("boundary", String.valueOf(boundary));
        if (offerType != null)
            params.put("offerType",Arrays.toString(offerType).replace(" ",""));
        if (orderBy != -1)
            params.put("orderBy", String.valueOf(orderBy));
        if (types != null)
            params.put("types", Arrays.toString(types).replace(" ", ""));
        if (features != null)
            params.put("features", Arrays.toString(features).replace(" ", ""));
        if (maxCount != -1)
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
        out.writeIntArray(offerType);
        out.writeInt(orderBy);
        out.writeIntArray(types);
        out.writeIntArray(features);
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
        in.readIntArray(offerType);
        orderBy = in.readInt();
        in.readIntArray(types);
        in.readIntArray(features);
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
        return boundary;
    }

    public void setBoundary(double boundary) {
        this.boundary = boundary;
    }

    public int[] getOfferType() {
        return offerType;
    }

    public void setOfferType(int[] offerType) {
        this.offerType = offerType;
    }

    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    public int[] getTypes() {
        return types;
    }

    public void setTypes(int[] types) {
        this.types = types;
    }

    public int[] getFeatures() {
        return features;
    }

    public void setFeatures(int[] features) {
        this.features = features;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

}
