package omnicentre.eworky;

import omnicentre.eworky.tools.TitleBar;
import android.app.Activity;
import android.os.Bundle;

public class Inscription extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We set the view, with the title bar:
        TitleBar.setContentView(this, R.layout.connect, R.layout.title_search);
        
    }

}
