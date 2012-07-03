package omnicentre.eworky.tools;

import java.util.ArrayList;

import omnicentre.eworky.Index;
import omnicentre.eworky.Map;
import omnicentre.eworky.R;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class TitleBar {
	
	private Activity a;
	private boolean titled;
	private ArrayList<Place> placeList;
	
	public TitleBar(Activity activity) {
		a = activity;
		titled = a.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	}

	public void setTitleBar(int layout){
		if(titled){
			a.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, layout);
			
			ImageView logo = (ImageView) a.findViewById(R.id.title_logo);
			if (logo != null)
				logo.setOnClickListener(logoListener);
			
			ImageView map_logo = (ImageView) a.findViewById(R.id.title_map_logo);
			if (map_logo != null)
				map_logo.setOnClickListener(map_logoListener);
		}
	}

	private OnClickListener logoListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(a, Index.class);
			a.startActivity(intent);
		}
	};
	
	private OnClickListener map_logoListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(a, Map.class);
			intent.putParcelableArrayListExtra("placeList", placeList);
			a.startActivity(intent);
		}
	};
	
	public void setPlaceList(ArrayList<Place> placeList) {
		this.placeList = placeList;
	}
}
