package omnicentre.eworky.tools;

import java.util.ArrayList;

import omnicentre.eworky.R;
import omnicentre.eworky.places.Place;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * This class allows to display a custom title bar. The constructor should be
 * called at the beginning of onCreate(), and the setTitleBar() after the
 * layout is inflated.
 *
 */
public class TitleBar {
	
    /**
     * The activity containing the title bar.
     */
	private Activity activity;
	
	/**
	 * True if title bars are supported, false otherwise.
	 */
	private boolean supported;
	
	
	private ArrayList<Place> placeList;
	
	/**
	 * Initiate the title bar. This will request the feature to have the bar.
	 * @param activity this activity where the title bar must be placed.
	 */
	public TitleBar(Activity activity) {
		this.activity = activity;
		supported = activity.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	}

	/**
	 * @param layout the title bar's layout
	 */
	public void setTitleBar(int layout){
		if(supported){
			activity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, layout);
			
			ImageView logo = (ImageView) activity.findViewById(R.id.title_logo);
			if (logo != null)
				logo.setOnClickListener(logoListener);
			
			ImageView map = (ImageView) activity.findViewById(R.id.title_map_logo);
			if (map != null)
				map.setOnClickListener(mapListener);
		}
	}

	private OnClickListener logoListener = new OnClickListener() {
		public void onClick(View v) {
			Redirections.index(activity);
		}
	};
	
	private OnClickListener mapListener = new OnClickListener() {
		public void onClick(View v) {
			Redirections.map(activity, placeList);
		}
	};
	
	public void setPlaceList(ArrayList<Place> placeList) {
		this.placeList = placeList;
	}
}
