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
Button playAgain, menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        cupView = (ImageView) findViewById(R.id.gameOverImage);
       cupView.setBackgroundResource((R.drawable.cup_animation));
        AnimationDrawable cupAnimation = (AnimationDrawable) cupView.getBackground();
        cupAnimation.start();

        playAgain = (Button) findViewById(R.id.playAgainButton);
        menu = (Button) findViewById(R.id.menuButton);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: restart game
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
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
