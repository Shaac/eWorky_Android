package omnicentre.eworky.tools;

import java.io.IOException;
import java.util.ArrayList;


import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;

public class Place {
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
		try                     { image = json.getString("description"); }
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
	
	public void toExtras(Intent intent) {
		intent.putExtra("id", id);
		intent.putExtra("name", name);
		intent.putExtra("latitude", latitude);
		intent.putExtra("longitude", longitude);
		intent.putExtra("description", description);
		intent.putExtra("image", image);
		intent.putExtra("address", address);
		intent.putExtra("city", city);
		intent.putExtra("type", type);
		intent.putExtra("url", url);
		intent.putExtra("rating", rating);
	}
	
	public Place(Bundle b) {
		id = b.getInt("id");
		name = b.getString("name");
		latitude = b.getDouble("latitude");
		longitude = b.getDouble("longitude");
		description = b.getString("description");
		image = b.getString("description");
		address = b.getString("address");
		city = b.getString("city");
		type = b.getString("type");
		url = b.getString("url");
		rating = b.getDouble("rating");
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
}
