package edu.augustana.csc490.circleofdeath;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.*;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import android.content.pm.ActivityInfo;

public class MainActivity extends Activity {

    private Button PlayButton;
    private Button AboutButton;
    private ImageView cupView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        AboutButton = (Button) findViewById(R.id.AboutButton);
        PlayButton = (Button) findViewById(R.id.PlayButton);
        cupView = (ImageView) findViewById(R.id.gameOverImage);
        cupView.setBackgroundResource((R.drawable.cup_animation));
        AnimationDrawable cupAnimation = (AnimationDrawable) cupView.getBackground();
        cupAnimation.start();


        showDisclaimerDialog();

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
