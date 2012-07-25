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

public class Facebook {

    private static final com.facebook.android.Facebook facebook =
            new com.facebook.android.Facebook("256350137810429");

    public static void authorize(Activity activity) {
        facebook.authorize(activity, new String[] { "email" }, new DialogListener() {

            public void onComplete(Bundle values) {
                // TODO Auto-generated method stub

            }

            public void onFacebookError(FacebookError e) {
                // TODO Auto-generated method stub

            }

            public void onError(DialogError e) {
                // TODO Auto-generated method stub

            }

            public void onCancel() {
                // TODO Auto-generated method stub

            }});
    }

    public static void authorizeCallback(int requestCode, int resultCode,
            Intent data) {
        facebook.authorizeCallback(requestCode, resultCode, data);
    }

    public static FacebookJson me()
            throws JsonSyntaxException, MalformedURLException, IOException {
        Gson gson = new Gson();
        FacebookJson o = gson.fromJson(facebook.request("me"), FacebookJson.class);
        return o;
    }

}
