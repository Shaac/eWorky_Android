package omnicentre.eworky.localisations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This class should contain the list of the amenities of a localisation.
 * It is meant to have its instances parameters of a {@link Localisation} object.
 *
 */
public class Amenities extends ArrayList<String> {

	private static final long serialVersionUID = 9018982469643236951L;

	/**
	 * Instantiate with an empty list.
	 */
	public Amenities() {
		super();
	}

	/**
	 * Instantiate from an existing list.
	 * @param list the existing list.
	 */
	public Amenities(ArrayList<String> list) {
		if (list != null)
			for (String s : list)
				this.add(s.replace("\\/", "/"));
	}

	/**
	 * Instantiate from an string containing a list.
	 * @param list the string with a list syntax.
	 */
	public Amenities(String list) {
		super();
		List<String> strings = Arrays.asList(list.split("\""));
		int i =0;
		for (String s : strings) {
			if (i % 2 == 1)
				this.add(s.replace("\\/", "/"));
			i++;
		}
	}
	
	/**
	 * Return the amenities as a text list.
	 * @return a string with the list.
	 */
	public String toText() {
	    String out = "";
            for(String s : this)
                out += " - " + s + "\n";
        return out;
	}

	/**
	 * Tell if the localisation has some free WiFi.
	 * @return true the localisation has some free WiFi, false otherwise. 
	 */
	public boolean hasFreeWifi() {
		return this.contains("Wifi gratuit");
	}

	/**
	 * Tell if the localisation has some WiFi, but not free.
	 * @return true the localisation has some WiFi, false otherwise.
	 */
	public boolean hasWifi() {
		return this.contains("Wifi payant");
	}

	/**
	 * Tell if it is possible to eat on this localisation.
	 * @return true the localisation sells food, false otherwise.
	 */
	public boolean hasFood() {
		return this.contains("Restauration");
	}

	/**
	 * Tell if the localisation is a bar or a coffee shop.
	 * @return true the localisation has a bar, false otherwise.
	 */
	public boolean hasCoffee() {
		return this.contains("Café / Bar");
	}

	/**
	 * Tell if the localisation has a parking.
	 * @return true the localisation has a parking, false otherwise.
	 */
	public boolean hasParking() {
		return this.contains("Parking");
	}

	/**
	 * Tell if the localisation's access has been improved for disabled people.
	 * @return true the localisation has an improved access, false otherwise.
	 */
	public boolean hasAccess() {
		return this.contains("Accès handicapés");
	}
}
