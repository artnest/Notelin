package artnest.notelin.view.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

/**
 * Created by nesterenko_a on 03.10.2017.
 */

public class CustomDialogs {

    public static void showAddNewNoteAlertDialog(Context context,
                                                 DialogInterface.OnClickListener listener) {
        final EditText taskEditText = new EditText(context);
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("Add a new task")
                .setMessage("What do you want to do next?")
                .setView(taskEditText)
                .setPositiveButton("Add", listener)
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }
}
