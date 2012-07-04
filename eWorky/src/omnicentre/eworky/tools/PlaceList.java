package omnicentre.eworky.tools;

import java.util.ArrayList;

public class PlaceList extends ArrayList<Place> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlaceList(ArrayList<Place> a) {
		super();
		if (a != null)
			for (Place p : a)
				this.add(p);
	}

	public ArrayList<String> toStrings () {
		ArrayList<String> l = new ArrayList<String>();
		for (Place p : this)
			l.add(p.getName());
		return l;
	}
}
