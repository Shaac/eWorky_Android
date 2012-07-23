package omnicentre.eworky;

import omnicentre.eworky.tools.Redirections;
import omnicentre.eworky.tools.Storage;
import omnicentre.eworky.tools.TitleBar;
import omnicentre.eworky.widgets.IndexArrayAdapter;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

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

        // We create the listeners for the navigation bar:
        Storage storage = new Storage(this);
        
        TextView mySpaces = (TextView) findViewById(R.id.my_spaces);
        Redirections.setClickListenerToMySpaces(mySpaces, this, storage);
        
        TextView myAccount = (TextView) findViewById(R.id.my_account);
        Redirections.setClickListenerToMyAccount(myAccount, this, storage);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
            Intent data) {
        switch (requestCode) {
        case Redirections.MY_SPACES:
            if (resultCode == RESULT_OK) {
                Redirections.mySpaces(this, new Storage(this), false);
            }
        }
    }
}
