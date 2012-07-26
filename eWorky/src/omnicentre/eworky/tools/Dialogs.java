package omnicentre.eworky.tools;

import omnicentre.eworky.R;
import omnicentre.eworky.SearchResults;
import omnicentre.eworky.API.AsyncSearch;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.TextView;

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

    /**
     * Creates a dialog that displays an alert and finish the activity when
     * closed.
     * @param title the alert's title.
     * @param description the alert's description.
     * @param activity the current activity.
     */
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

    /**
     * Creates a dialog box where the user can choose how to sort the results.
     * Then it starts the search.
     * @param activity the current activity.
     * @param criteria the current criteria for the search.
     */
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

    /**
     * Creates a dialog box where the user can choose the boundary for the
     * search.
     * Then it starts the search.
     * @param activity the current activity.
     * @param criteria the current criteria for the search.
     */
    public static void newBoundaryAlert(final SearchResults activity,
            final SearchCriteria criteria) {
        final CharSequence[] items = {"1", "5", "10", "15", "20", "25", "30",
                "35", "40", "45", "50"};

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getResources().getString(R.string.distance));
        builder.setSingleChoiceItems(items, -1,
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                dialog.dismiss();
                criteria.setBoundary(item == 0 ? 1 : item * 5);
                TextView kilometers = (TextView)
                        activity.findViewById(R.id.kilometers);
                kilometers.setText((int) criteria.getBoundary() + " km");
                (new AsyncSearch(activity, criteria)).execute();
            }
        });
        builder.create().show();
    }
    
    /**
     * Creates a dialog box where the user can choose criteria for his search.
     * Then it starts the search.
     * @param activity the current activity.
     * @param criteria the current criteria for the search.
     */
    public static void newCriteriaAlert(final SearchResults activity,
            final SearchCriteria criteria) {
        final CharSequence[] items = {
                activity.getResources().getString(R.string.type_0),
                activity.getResources().getString(R.string.type_1),
                activity.getResources().getString(R.string.type_2),
                activity.getResources().getString(R.string.type_3),
                activity.getResources().getString(R.string.type_4),
                activity.getResources().getString(R.string.type_5),
                activity.getResources().getString(R.string.type_6),
                activity.getResources().getString(R.string.type_7),
                activity.getResources().getString(R.string.type_8),
                activity.getResources().getString(R.string.type_9),
                activity.getResources().getString(R.string.type_10),
                "------------",
                activity.getResources().getString(R.string.feature_0),
                activity.getResources().getString(R.string.feature_1),
                activity.getResources().getString(R.string.feature_2),
                activity.getResources().getString(R.string.feature_3),
                activity.getResources().getString(R.string.feature_4)};

        boolean[] b = new boolean[17];
        for (int i : criteria.getTypes())
            b[i] = true;
        for (int i : criteria.getFeatures())
            b[i + 12] = true;
        
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getResources().getString(R.string.distance));
        builder.setMultiChoiceItems(items, b,
                new DialogInterface.OnMultiChoiceClickListener() {

            public void onClick(DialogInterface dialog, int which,
                    boolean isChecked) {
                if (which < 11) {
                    if (isChecked)
                        criteria.addType(which);
                    else
                        criteria.removeType(which);
                } else if (which > 11) {
                    if (isChecked)
                        criteria.addFeature(which - 12);
                    else
                        criteria.removeFeature(which - 12);
                }
            }
        });
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                dialog.dismiss();
                (new AsyncSearch(activity, criteria)).execute();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
