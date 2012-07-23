package omnicentre.eworky;

import omnicentre.eworky.API.AsyncRequests;
import omnicentre.eworky.tools.Redirections;
import omnicentre.eworky.tools.TitleBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * This class handles the action to connect or register.
 *
 */
public class Connect extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We set the view, with the title bar:
        TitleBar.setContentView(this, R.layout.connect, R.layout.title_search);

        // We listen to the buttons:
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        
        Button inscription = (Button) findViewById(R.id.inscription);
        Redirections.setClickListenerToInscription(inscription, this);
        
        Button facebook = (Button) findViewById(R.id.inscription_facebook);
        Redirections.setClickListenerToFacebook(facebook, this);
    }

    public void onClick(View v) {
        TextView login = (TextView) findViewById(R.id.login);
        TextView password = (TextView) findViewById(R.id.password);
        AsyncRequests.connect(this, login.getText().toString(),
                password.getText().toString()); 
    }
}
