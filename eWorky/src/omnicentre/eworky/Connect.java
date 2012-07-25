package omnicentre.eworky;

import java.io.IOException;
import java.net.MalformedURLException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import omnicentre.eworky.API.AsyncRequests;

import omnicentre.eworky.tools.Dialogs;
import omnicentre.eworky.tools.Facebook;
import omnicentre.eworky.tools.FacebookJson;
import omnicentre.eworky.tools.Redirections;
import omnicentre.eworky.tools.TitleBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        TitleBar.setContentView(this, R.layout.connect, R.layout.title_connect);

        // We listen to the buttons:
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        
        Button inscription = (Button) findViewById(R.id.register);
        Redirections.setClickListenerToRegister(inscription, this);
        
        Button facebook = (Button) findViewById(R.id.register_facebook);
        Redirections.setClickListenerToFacebook(facebook, this);
    }

    public void onClick(View v) {
        TextView login = (TextView) findViewById(R.id.login);
        TextView password = (TextView) findViewById(R.id.password);
        AsyncRequests.connect(this, login.getText().toString(),
                password.getText().toString()); 
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
            Intent data) {
        
        if (requestCode == Redirections.FACEBOOK) {
            if (resultCode == RESULT_OK) {
                Facebook.authorizeCallback(requestCode, resultCode, data);
                try {
                    FacebookJson json = Facebook.me();
                    AsyncRequests.register(this, json.email, json.first_name,
                            json.last_name, json.id, json.link);
                } catch (Exception e) {
                    Dialogs.newAlertToFinish("Error", e.getMessage(), this);
                }
            } else
                finish();
        } else {
            setResult(RESULT_OK, new Intent());
            finish();
        }
            
    }
}
