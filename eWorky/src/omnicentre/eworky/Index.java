package omnicentre.eworky;

import com.pixate.pxcomponentkit.view.PXTheme;

import omnicentre.eworky.tools.TitleBar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * The first activity of the application.
 *
 */
public class Index extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We set the view, with the title bar:
        TitleBar titleBar = new TitleBar(this);
        setContentView(R.layout.index);
        titleBar.setTitleBar(R.layout.title_index);

        // We listen to the search button:
        Button button = (Button) findViewById(R.id.search_button);
        PXTheme.themeButton(button, Color.parseColor("#5DAFDE"));
        button.setOnClickListener(searchListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_index, menu);
        return true;
    }

    /**
     * Redirects to the search results page when the button is pressed.
     */
    private OnClickListener searchListener = new OnClickListener() {

        public void onClick(View v) {

            // We get the text inside the search bar:
            EditText search_bar = (EditText) findViewById(R.id.search_bar);
            String query = search_bar.getText().toString();

            // We redirect to the search results page:
            Intent intent = new Intent(Index.this, SearchResults.class);
            intent.putExtra("query", query);
            startActivity(intent);
        }
    };
}
