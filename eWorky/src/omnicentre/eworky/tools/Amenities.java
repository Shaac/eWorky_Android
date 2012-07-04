package omnicentre.eworky.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Amenities {
	
	private ArrayList<String> amenities;
	
	public Amenities(String jsonString) {
		List<String> strings = Arrays.asList(jsonString.split("\""));
		amenities = new ArrayList<String>();
		int i =0;
		for (String s : strings) {
			if (i % 2 == 1)
				amenities.add(s);
			i++;
		}
	}
	
	public Amenities() {
		amenities = new ArrayList<String>();
	}
	
	public Amenities(ArrayList<String> a) {
		amenities = a;
	}
	
	public ArrayList<String> getAmenities() {
		return amenities;
	}


	public boolean hasFreeWifi() {
		return amenities.contains("Wifi gratuit");
	}
	
	public boolean hasWifi() {
		return amenities.contains("Wifi payant");
	}
	
	public boolean hasFood() {
		return amenities.contains("Restauration");
	}
	
	public boolean hasCoffee() {
		return amenities.contains("Café \\/ Bar");
	}
	
	public boolean hasParking() {
		return amenities.contains("Parking");
	}
	
	public boolean hasAccess() {
		return amenities.contains("Accès handicapés");
	}
}
