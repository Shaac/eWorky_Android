package omnicentre.eworky.tools;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.maps.OverlayItem;
import com.readystatesoftware.mapviewballoons.BalloonOverlayView;
import com.readystatesoftware.mapviewballoons.R;

// used if we want to customize the bubble in the map.

public class PlaceOverlayView<Item extends OverlayItem>
        extends BalloonOverlayView<MyOverlayItem> {
    
    private TextView name;
    private ImageView picture;

    public PlaceOverlayView(Context context, int balloonBottomOffset) {
        super(context, balloonBottomOffset);
    }
    
    @Override
    protected void setupView(Context context, final ViewGroup parent) {
        
        // inflate our custom layout into parent
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list, parent);
        
        // setup our fields
        name = (TextView) v.findViewById(R.id.name);
        picture = (ImageView) v.findViewById(R.id.picture);
    }

    @Override
    protected void setBalloonData(MyOverlayItem item, ViewGroup parent) {
        
        // map our custom item data to fields
        name.setText(item.getTitle());
        
        // get remote image from network.
        // bitmap results would normally be cached, but this is good enough for demo purpose.
        //picture.setImageResource(R.drawable.icon);
        new FetchImageTask() { 
            protected void onPostExecute(Bitmap result) {
                if (result != null) {
                    picture.setImageBitmap(result);
                }
            }
        }.execute(item.getPlace().getImage());
        
    }
    
    private class FetchImageTask extends AsyncTask<String, Integer, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... arg0) {
            Bitmap b = null;
            try {
                 b = BitmapFactory.decodeStream((InputStream) new URL(arg0[0]).getContent());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } 
            return b;
        }   
    }
}
