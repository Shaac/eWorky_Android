package omnicentre.eworky;

import java.util.HashMap;

import com.pixate.pxcomponentkit.view.PXTheme;

import omnicentre.eworky.tools.Redirections;
import omnicentre.eworky.tools.TitleBar;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

/**
 * The search activity, which only contains a search bar.
 *
 */
public class Search extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We set the view, with the title bar:
        TitleBar.setContentView(this, R.layout.search, R.layout.title_search);

        // We listen to the search button:
        Button b = (Button) findViewById(R.id.search_button);
        Redirections.setClickListenerToSearchCriteria(b, this);
        
        // We decorate the button:
        PXTheme.themeButton(b, Color.parseColor("#5DAFDE"));
    }
    
    public HashMap<String, String> getParams() {
        EditText search_bar = (EditText) findViewById(R.id.search_main);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("place", search_bar.getText().toString());
        Log.w("Txt", search_bar.getText().toString());
        return params;
    }
}
