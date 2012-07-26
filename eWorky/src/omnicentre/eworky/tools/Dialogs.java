package omnicentre.eworky.tools;

import omnicentre.eworky.R;
import omnicentre.eworky.SearchResults;
import omnicentre.eworky.API.AsyncSearch;
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

    public static void newSortAlert(final SearchResults activity,
            final SearchCriteria criteria) {
        final CharSequence[] items = {
                activity.getResources().getString(R.string.rating),
                activity.getResources().getString(R.string.distance)};

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getResources().getString(R.string.sort));
        builder.setSingleChoiceItems(items, criteria.getOrderBy(),
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                dialog.dismiss();
                criteria.setOrderBy(item);
                (new AsyncSearch(activity, criteria)).execute();
            }
        });
        builder.create().show();
    }

}
