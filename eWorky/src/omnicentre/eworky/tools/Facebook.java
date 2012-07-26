package omnicentre.eworky.tools;

import java.io.IOException;
import java.net.MalformedURLException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.android.*;
import com.facebook.android.Facebook.*;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * This class allows to make Facebook operations.
 *
 */
public class Facebook {

    /**
     * This object allows to communicate with Facebook. It contains the
     * application's key.
     */
    private static final com.facebook.android.Facebook facebook =
            new com.facebook.android.Facebook("256350137810429");

    /**
     * Starts a new Facebook activity requesting authorization to get data.
     * @param activity the current activity.
     */
    public static void authorize(Activity activity) {
        facebook.authorize(activity, new String[] { "email" }, new DialogListener() {
            public void onComplete(Bundle values) {}
            public void onFacebookError(FacebookError e) {}
            public void onError(DialogError e) {}
            public void onCancel() {}});
    }

    /**
     * Get the data into the Facebook object.
     * Must be called on top of onActivityResult().
     * @param requestCode the requestCode from onActivityResult().
     * @param resultCode the resultCode from onActivityResult().
     * @param data the data from onActivityResult().
     */
    public static void authorizeCallback(int requestCode, int resultCode,
            Intent data) {
        facebook.authorizeCallback(requestCode, resultCode, data);
    }

    /**
     * Get the Facebook data about the user.
     * @return the data.
     * @throws JsonSyntaxException if a parsing error occurs.
     * @throws MalformedURLException if accessing an invalid endpoint.
     * @throws IOException if a network error occurs.
     */
    public static FacebookJson me()
            throws JsonSyntaxException, MalformedURLException, IOException {
        Gson gson = new Gson();
        FacebookJson o = gson.fromJson(facebook.request("me"), FacebookJson.class);
        return o;
    }
}
