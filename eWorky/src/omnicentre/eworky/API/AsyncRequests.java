package omnicentre.eworky.API;

import java.io.IOException;

import com.readystatesoftware.mapviewballoons.R;

import omnicentre.eworky.tools.Dialogs;
import omnicentre.eworky.tools.Storage;
import android.app.Activity;
import android.app.ProgressDialog;

public class AsyncRequests {

    public static void connect(final Activity activity, final String login, final String password) {
        final ProgressDialog dialog = ProgressDialog.show(activity, "", 
                "Loading. Please wait...", true);
        final Thread getAPI = new Thread() {
            public void run() {
                String exception = null;
                try {
                    new Storage(activity, Requests.connect(login, password));
                    exception = "OK";
                } catch (NoSuccessException e) {
                    exception = e.getError();
                } catch (IOException e) {
                    exception = activity.getResources().getString(
                            R.string.errorFile);
                }
                final String error = exception;
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        dialog.dismiss();
                        Dialogs.newAlertToFinish("Connexion", error, activity);
                    }
                });
            }
        };
        getAPI.start();
    }

}
