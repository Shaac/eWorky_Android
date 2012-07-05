package omnicentre.eworky.places;

import java.util.ArrayList;


public class PlaceList extends ArrayList<Place> {

    private static final long serialVersionUID = -5818756543886625048L;

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
