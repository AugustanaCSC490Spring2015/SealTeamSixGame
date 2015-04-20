package edu.augustana.csc490.circleofdeath;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import edu.augustana.csc490.circleofdeath.utils.PlayerListArrayAdapter;

/**
 * The MenuActivity class is used to display a view containing options to go to a different screen
 * to edit the card rules, as well as add, edit, and remove players from the game. The game begins
 * when the startGameButton is clicked
 */
public class MenuActivity extends Activity {

    Button editCardRulesButton;
    ImageButton addPlayerButton;
    ListView playerListView;
    Button startGameButton;
    Button editGameModeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set layout view
        setContentView(R.layout.activity_game_setup);

        //Get References to view items
        editCardRulesButton = (Button) findViewById(R.id.editCardRulesButton);
        addPlayerButton = (ImageButton) findViewById(R.id.addPlayerButton);
        playerListView = (ListView) findViewById(R.id.playersListView);
        startGameButton = (Button) findViewById(R.id.startButton);
        editGameModeButton = (Button) findViewById(R.id.editGameModeButton);

        // ArrayList to hold players
        GameManager.players = new ArrayList<String>();


        // Set the adapter for the player list
        final PlayerListArrayAdapter adapter = new PlayerListArrayAdapter(this, R.layout.player_list_item, GameManager.players);
        playerListView.setAdapter(adapter);

        // Button Listeners
        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(MenuActivity.this);
                AlertDialog.Builder alert = new AlertDialog.Builder(MenuActivity.this);
                alert.setTitle(R.string.enter_name_prompt);
                alert.setView(input);
                alert.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Editable name = input.getText();
                        GameManager.addPlayer(name.toString());
                        adapter.notifyDataSetChanged();
                    }
                });
                alert.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                });
                alert.show();
            }
        });

        startGameButton.setOnClickListener(new View.OnClickListener() {
            //TODO: Check players were added, etc..
            @Override
            public void onClick(View v) {
                // TODO: Make sure minimum number of players are added
                Intent intent = new Intent(MenuActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        editCardRulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, EditRulesActivity.class);
                startActivity(intent);
            }
        });

        editGameModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameModeDialog gameModeDialog = new GameModeDialog(MenuActivity.this);
                gameModeDialog.show();
            }
        });
    }
}
