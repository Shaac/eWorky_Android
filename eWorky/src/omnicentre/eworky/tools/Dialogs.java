package omnicentre.eworky.tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

/**
 * This class is used to create Dialogs.
 *
 */
public class Dialogs {
    
    /**
     * Creates a dialog that displays an alert and redirects to the index when
     * closed.
     * @param title the alert's title.
     * @param description the alert's description.
     * @param activity the current activity.
     */
    public static void newAlertToIndex(String title, String description,
            final Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(description);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                activity.setResult(Activity.RESULT_OK, new Intent());
                Redirections.index(activity);
            }
        });
        builder.create().show();
    }
    
    public static void newAlertToFinish(String title, String description,
            final Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(description);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                activity.setResult(Activity.RESULT_OK, new Intent());
                activity.finish();
            }
        });
        builder.create().show();
    }

}
