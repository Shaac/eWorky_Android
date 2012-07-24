package omnicentre.eworky;

import omnicentre.eworky.tools.Redirections;
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
        TextView mySpaces = (TextView) findViewById(R.id.my_spaces);
        Redirections.setClickListenerToMySpaces(mySpaces, this);

        TextView myAccount = (TextView) findViewById(R.id.my_account);
        Redirections.setClickListenerToMyAccount(myAccount, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
            Intent data) {

        // If we are here we come from an connection / register attempt.

        // We see which action we do next:
        switch (requestCode) {
        case Redirections.MY_SPACES:
            if (resultCode == RESULT_OK)
                Redirections.mySpaces(this, false);
            break;
        case Redirections.MY_ACCOUNT:
            if (resultCode == RESULT_OK)
                Redirections.myAccount(this, false);
            break;
        }
    }
}
