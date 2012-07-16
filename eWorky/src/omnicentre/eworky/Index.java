package omnicentre.eworky;

import omnicentre.eworky.tools.TitleBar;
import omnicentre.eworky.widgets.IndexArrayAdapter;

import android.os.Bundle;
import android.widget.ListView;
import android.app.Activity;

/**
 * The first activity of the application.
 *
 */
public class Index extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We set the view, with the title bar:
        TitleBar.setContentView(this, R.layout.index, R.layout.title_index);

        // We display the menu list:
        ListView list = (ListView)findViewById(R.id.mainList);  
        list.setAdapter(new IndexArrayAdapter(this));
    }
}
