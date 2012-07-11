package omnicentre.eworky.localisations;

import java.util.ArrayList;

/**
 * This is a simple extension of {@link ArrayList<Place>}, but it is useful for
 * its methods to convert into a  {@link ArrayList<String>}.
 *
 */
public class PlaceList extends ArrayList<Localisation> {

    private static final long serialVersionUID = -5818756543886625048L;

    /**
     * Create from an already existent {@link ArrayList<Place>}.
     * @param list the existing list.
     */
    public PlaceList(ArrayList<Localisation> list) {
		super();
		if (list != null)
			for (Localisation p : list)
				this.add(p);
	}

    /**
     * Return an {@link ArrayList<String>} with the name of the places.
     * @return the {@link ArrayList<String>}.
     */
	public ArrayList<String> toStrings () {
		ArrayList<String> l = new ArrayList<String>();
		for (Localisation p : this)
			l.add(p.getName());
		return l;
	}
}
