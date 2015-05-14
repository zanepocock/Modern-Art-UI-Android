// @author Zane Pocock
// @version 1.0.3
// @since 1.0.0
// Last Edit 11 February 2015

package dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import course.lab.modernartui.R;

//  Builds the More Information dialog.

public class MoreInformationDialog extends DialogFragment {

    /**
     * Builds the more information alert dialog.
     * It sets the newly created dialog message, two buttons and a handler for the positive
     * button.
     * @param savedInstanceState The last saved instance state of the Fragment,
     *                           or null if this is a freshly created Fragment.
     * @return Return a new Dialog instance to be displayed by the Fragment.
     */
    @Override
    public Dialog onCreateDialog ( Bundle savedInstanceState ) {

        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        builder.setMessage( R.string.dialog_text ).setPositiveButton( R.string.dialog_visit,
                new DialogInterface.OnClickListener() {

                    /**
                     * Method  invoked when the positive button  in the dialog is clicked.
                     * Sets up an intent to visit a website with a web browser and starts an
                     * activity with it.
                     * @param dialog The dialog that received the click.
                     * @param id     The button that was clicked (the position
                     *               of the item clicked.)
                     */
                    public void onClick ( DialogInterface dialog, int id ) {

                        Intent visit = new Intent( Intent.ACTION_VIEW,
                                Uri.parse( "http://www.moma.org" ) );
                        Intent chooser = Intent.createChooser( visit,
                                getResources().getString( R.string.open_with ) );
                        startActivity( chooser );
                    }
                } ).setNegativeButton( R.string.dialog_not_now, null );

        return builder.create();
    }
}