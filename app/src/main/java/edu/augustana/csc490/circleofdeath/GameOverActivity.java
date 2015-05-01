package edu.augustana.csc490.circleofdeath;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class GameOverActivity extends Activity {
ImageView cupView;
Button playAgainButton, menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        cupView = (ImageView) findViewById(R.id.gameOverImage);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        menuButton = (Button) findViewById(R.id.menuButton);

        // Cup animation
        cupView.setBackgroundResource((R.drawable.cup_animation));
        AnimationDrawable cupAnimation = (AnimationDrawable) cupView.getBackground();
        cupAnimation.start();

        // button click listeners
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: restart game
            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOverActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Do nothing - don't let user go back into GameActivity
    }
}
