package omnicentre.eworky.tools;

import java.io.IOException;
import java.util.ArrayList;


import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

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
	//private String[] amneties;
	//private comments
	//private fans

	public Place(JSONObject json) throws JSONException {

		// These fields are mandatory and cause a failure of the function :
		id = json.getInt("id");
		name = json.getString("name");
		latitude = json.getDouble("latitude");
		longitude = json.getDouble("longitude");

		// There are not and can be set to void if nonexistent:
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
	}

	public static ArrayList<Place> parseList(JSONArray jsonArray) {

		ArrayList<Place> list = new ArrayList<Place> ();

		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				list.add(new Place(jsonArray.getJSONObject(i)));
			} catch (JSONException e) {}
		}

		return list;
	}

	public static ArrayList<Place> fromQuery(String query)
			throws ClientProtocolException, IOException, JSONException {

		String url = "http://www.eworky.com/api/localisation/search?place=" +
				query + "&json=1";
		String page = Http.getUrl(url);
		JSONObject json= new JSONObject(page);
		JSONArray jsonArray = json.getJSONArray("response");

		return parseList(jsonArray);

	}

	public int getId()             { return id;	}
	public String getName()        { return name; }
	public double getLatitude()    { return latitude; }
	public double getLongitude()   { return longitude; }
	public String getDescription() { return description; }
	public String getImage()       { return image; }
	public String getAddress()     { return address; }
	public String getCity()        { return city; }
	public String getType()        { return type; }
	public String getUrl()         { return url; }
	public double getRating()      { return rating; }

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
	}

	public static final Parcelable.Creator<Place> CREATOR =
			new Parcelable.Creator<Place>() {

		public Place createFromParcel(Parcel source)
		{
			return new Place(source);
		}

		public Place[] newArray(int size)
		{
			return new Place[size];
		}
	};

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
	}
}
