package omnicentre.eworky.tools;

import omnicentre.eworky.R;

import android.app.Activity;
import android.view.Window;
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
    private Activity a;

    /**
     * True if title bars are supported, false otherwise.
     */
    private boolean supported;
    

    /**
     * Initiate the title bar. This will request the feature to have the bar.
     * @param activity this activity where the title bar must be placed.
     */
    public TitleBar(Activity activity) {
        this.a = activity;
        supported = activity.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
    }

    /**
     * Set the title bar. This view must have been inflated first.
     * @param layout the title bar's layout.
     */
    public void setTitleBar(int layout){
        if(supported){
            
            // We set the bar:
            a.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, layout);

            // We set an OnClickListener to the eWorky logo:
            ImageView logo = (ImageView) a.findViewById(R.id.title_logo);
            if (logo != null)
                Redirections.setClickListenerToIndex(logo, a);
            
            ImageView list = (ImageView) a.findViewById(R.id.title_list_logo);
            if (list != null)
                Redirections.setClickListenerToFinish(list, a);
            
            ImageView map = (ImageView) a.findViewById(R.id.title_map_logo);
            if (map != null)
                Redirections.setClickListenerToMap(map, a);
        }
    }
    
    /**
     * Calls the setContentView() method of the activity and place the title
     * bar.
     * @param activity this activity where the title bar must be placed.
     * @param layout the activity's layout.
     * @param title_layout the title bar's layout.
     */
    public static void setContentView(Activity activity, int layout,
            int title_layout) {
        
        TitleBar titleBar = new TitleBar(activity);
        activity.setContentView(layout);
        titleBar.setTitleBar(title_layout);
    }
}
