package omnicentre.eworky;

import omnicentre.eworky.API.AsyncRequests;
import omnicentre.eworky.tools.TitleBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

//TODO Safe? Can't one register with an existing email (not his) and log in? 

/**
 * This activity allow an user to register, and then connect him.
 *
 */
public class Register extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We set the view, with the title bar:
        TitleBar.setContentView(this, R.layout.register,
                R.layout.title_register);

        // We listen to the confirmation button:
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    public void onClick(View v) {
        // We get the fields' content:
        String email = 
                ((TextView) findViewById(R.id.email)).getText().toString();
        String firstName = 
                ((TextView)findViewById(R.id.first_name)).getText().toString();
        String name =
                ((TextView) findViewById(R.id.name)).getText().toString();
        String phone =
                ((TextView) findViewById(R.id.phone)).getText().toString();
        
        // We register:
        AsyncRequests.register(this, email, firstName, name, phone);
    }
}
