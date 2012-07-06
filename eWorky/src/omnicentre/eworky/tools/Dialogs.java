package omnicentre.eworky.tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

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
            Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(description);
        final Activity a = activity;
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                Redirections.index(a);
            }
        });
        builder.create().show();
    }

}
