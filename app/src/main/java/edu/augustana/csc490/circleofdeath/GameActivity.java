package edu.augustana.csc490.circleofdeath;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * GameActivity sets the layout for the game
 * Note: It currently looks like GameFragment does most of the work for this, this may be of more
 * use if we implement different modes such as phone and tablet so GameFragment could be used in
 * different ways
 */
public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    @Override
    public void onBackPressed() {
        // do nothing - overriding this method stops the player from being able to use the back
        // button to go back to the MenuActivity
        // TODO: Prompt user if they are sure they want to quit the game and go back to the menu
    }
}
