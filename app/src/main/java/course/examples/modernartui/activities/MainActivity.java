// @author Zane Pocock
// @version 1.0.3
// @since 1.0.0
// Created 9 February 2015
// Last Edit 11 February 2015


package course.examples.modernartui.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import course.lab.modernartui.R;
import dialogs.MoreInformationDialog;

/** Main activity, with a palette holding several rectangular views linked to
 * their own background colours via seek bar at the bottom,
 * which changes the color of each rectangle (except the white and gray ones) when dragged.
 */

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getName();

    private RelativeLayout palette;

    /**
     * Called when the activity starts.
     * Sets up the main activity content view and registers a change handler for the activity's
     * seek bar.
     * @param savedInstanceState If the activity is being re-initialised after
     *                           previously being shut down then this Bundle contains the data it
     *                           most recently supplied. Otherwise it is null.
     */
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        palette = ( RelativeLayout ) findViewById( R.id.palette );
        SeekBar seek = ( SeekBar ) findViewById( R.id.seekBar );

        seek.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            /**
             * Notification that the seek bar progress level has changed.
             * When the seek bar state changes, loop through the children of the palette view and
             * change each individual view colour based on the percentage that the seek bar is
             * currently at. Ignores views that are either white or grey.
             * @param seekBar  The SeekBar whose progress has changed
             * @param progress The current progress level. This will be in the range 0 - 100.
             * @param fromUser True if the progress change was initiated by the user.
             */
            @Override
            public void onProgressChanged ( SeekBar seekBar, int progress, boolean fromUser ) {

                for ( int i = 0; i < palette.getChildCount(); i++ ) {
                    View child = palette.getChildAt( i );

                    int originalColor = Color.parseColor( ( String ) child.getTag() );
                    int invertedColor = ( 0x00FFFFFF - ( originalColor | 0xFF000000 ) ) |
                            ( originalColor & 0xFF000000 );

                    if ( getResources().getColor( R.color.white ) != originalColor &&
                            getResources().getColor( R.color.lightgrey ) != originalColor ) {

                        int origR = ( originalColor >> 16 ) & 0x000000FF;
                        int origG = ( originalColor >> 8 ) & 0x000000FF;
                        int origB = originalColor & 0x000000FF;

                        int invR = ( invertedColor >> 16 ) & 0x000000FF;
                        int invG = ( invertedColor >> 8 ) & 0x000000FF;
                        int invB = invertedColor & 0x000000FF;

                        child.setBackgroundColor( Color.rgb(
                                ( int ) ( origR + ( invR - origR ) * ( progress / 100f ) ),
                                ( int ) ( origG + ( invG - origG ) * ( progress / 100f ) ),
                                ( int ) ( origB + ( invB - origB ) * ( progress / 100f ) ) ) );
                        child.invalidate();
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }


            /**
             * Currently used for nothing.
             * @param seekBar The SeekBar in which the touch gesture began
             */
            @Override
            public void onStopTrackingTouch ( SeekBar seekBar ) {

            }
        } );
    }

    /**
     * Initialise the contents of the Activity's standard options menu. Menu items are placed
     * in menu.This is only called once, the first time the options menu is displayed.
     * @param menu The options menu in which the items are placed.
     * @return Returns true for the menu to be displayed; if it returns false it will not be shown.
     */

    @Override
    public boolean onCreateOptionsMenu ( Menu menu ) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Evaluates when the user clicks the More Information options menu and shows fragment.
     * @param item The menu item that was clicked.
     */
    public void showDialog ( MenuItem item ) {

        new MoreInformationDialog().show( getFragmentManager(), TAG );
    }
}