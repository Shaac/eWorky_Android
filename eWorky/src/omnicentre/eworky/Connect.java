package omnicentre.eworky;

import omnicentre.eworky.tools.Redirections;
import omnicentre.eworky.tools.TitleBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class Connect extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We set the view, with the title bar:
        TitleBar.setContentView(this, R.layout.connect, R.layout.title_index);

        // We listen to the button:
        Button b = (Button) findViewById(R.id.button);
        Redirections.setClickListenerToFinish(b, this);
    }
}
