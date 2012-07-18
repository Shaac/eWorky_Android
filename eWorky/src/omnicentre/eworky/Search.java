package omnicentre.eworky;

import com.pixate.pxcomponentkit.view.PXTheme;

import omnicentre.eworky.tools.Redirections;
import omnicentre.eworky.tools.TitleBar;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
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
        //Redirections.setClickListenerToSearchResults(b, this, null);
        
        EditText search_bar = (EditText) findViewById(R.id.search_bar);
        String query = search_bar.getText().toString();
        
        // We decorate the button:
        PXTheme.themeButton(b, Color.parseColor("#5DAFDE"));
    }
}
