package edu.augustana.csc490.circleofdeath;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

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

    /**
     * onBackPressed() is called when the user hits the back key on their device. We override it
     * after the game has started to stop them from going back into MenuActivity. When the users hit
     * the back button, it prompts them with a dialog asking them to confirm if they want to go back
     * to the main menu and lose all current game data or cancel and continue the game.
     */
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.caution);
        builder.setMessage(R.string.quit_game_warning);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing, dialog will close and game continues
            }
        });
        builder.show();
    }
}
