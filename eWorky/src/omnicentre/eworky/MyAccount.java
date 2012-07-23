package omnicentre.eworky;

import java.io.IOException;

import omnicentre.eworky.tools.Redirections;
import omnicentre.eworky.tools.Storage;
import omnicentre.eworky.tools.TitleBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyAccount extends Activity implements OnClickListener {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We set the view, with the title bar:
        TitleBar.setContentView(this, R.layout.my_account,
                R.layout.title_search);

        // We listen to the button:
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(this);
    }

    public void onClick(View arg0) {
        try {
            Storage.purge(this);
        } catch (IOException e) {
        }
        Redirections.index(this);
    }

}
