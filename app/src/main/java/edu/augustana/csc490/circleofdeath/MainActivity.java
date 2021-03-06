package edu.augustana.csc490.circleofdeath;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.*;

import android.view.View;
import android.widget.*;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import edu.augustana.csc490.circleofdeath.utils.Constants;

public class MainActivity extends Activity implements AnimationDoneListener {

    private Button PlayButton;
    private Button AboutButton;
    private CircleAnimationView animationView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        AboutButton = (Button) findViewById(R.id.AboutButton);
        PlayButton = (Button) findViewById(R.id.PlayButton);

        /** load preferences */
        sharedPreferences = getSharedPreferences(Constants.PREF_FILE, MODE_PRIVATE);

        /** only show disclaimer dialog on first launch */
        if (!sharedPreferences.getBoolean(Constants.PREF_DISCLAIMER, false)) {
            // show disclaimer
            showDisclaimerDialog();

            // store that dialog has been shown in preferences
            sharedPreferences.edit().putBoolean(Constants.PREF_DISCLAIMER,true).commit();
        }

        AboutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showAboutDialog();
            }
        });

        PlayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        // This is used if we decide to use the animation in the future
       // titleImageView = (TextView) findViewById(R.id.gametitle);

        animationView = (CircleAnimationView) findViewById(R.id.circle_anim_view);
        animationView.setAnimationDoneListener(this);

    }

    /*
    This is for title animation if we decide to change other minds in the future
     */
    public void animationDone() {
    //    titleTextView.setText("CIRCLE OF DEATH");
    }


    /**
     * Creates and shows the about dialog
     */
    private void showAboutDialog() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(R.string.about_title)
                .setMessage(R.string.about_text)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing, close dialog
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    /**
     * This method shows an Dialog with a disclaimer about drinking responsibly
     */
    private void showDisclaimerDialog() {
        new AlertDialog.Builder(this) //Makes the disclaimer pop up as soon as it opens.
                .setTitle(R.string.disclaimer_title)
                .setMessage(R.string.disclaimer_text)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing, close dialog
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
