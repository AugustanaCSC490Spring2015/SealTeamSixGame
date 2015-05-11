package edu.augustana.csc490.circleofdeath;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import edu.augustana.csc490.circleofdeath.utils.PlayerListArrayAdapter;

/**
 * The MenuActivity class is used to display a view containing options to go to a different screen
 * to edit the card rules, as well as add, edit, and remove players from the game. The game begins
 * when the startGameButton is clicked
 */
public class MenuActivity extends ActionBarActivity {

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
        GameManager.players = new ArrayList<>();

        // Set the adapter for the player list
        final PlayerListArrayAdapter adapter = new PlayerListArrayAdapter(this, R.layout.player_list_item, GameManager.players);
        playerListView.setAdapter(adapter);

        // create the default rules and add the strings to it
        // NOTE: This may not be the best way to do this, maybe they could be kept in a RuleSet  --Or a Map<Suit, Rule/String>?
        GameManager.defaultRules = new HashMap<>();
        GameManager.loadDefaultRules(this);

        // make a copy for the rules that can be changed
        GameManager.rules = new HashMap<>();
        GameManager.rules.putAll(GameManager.defaultRules);

        // Button Listeners
        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(MenuActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                AlertDialog.Builder alert = new AlertDialog.Builder(MenuActivity.this);
                alert.setTitle(R.string.enter_name_prompt);
                alert.setView(input);
                alert.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = input.getText().toString();

                        // Check that a unique name was entered
                        if(!name.equals("") && name != null && !GameManager.containsPlayer(name) ){
                            // Store valid name
                            GameManager.addPlayer(name);
                            adapter.notifyDataSetChanged();
                        } else {
                            if (name.equals("") || name == null) {
                                //player entered blank name
                                Toast.makeText(MenuActivity.this, R.string.blank_player_error, Toast.LENGTH_SHORT).show();
                            } else {
                                //player name not unique
                                Toast.makeText(MenuActivity.this, R.string.duplicate_name_error, Toast.LENGTH_SHORT).show();
                            }
                        }
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
            @Override
            public void onClick(View v) {
                if (GameManager.getNumberOfPlayers() == 0) {
                    // Tell user they must have at least one player
                    Toast.makeText(MenuActivity.this, getString(R.string.no_players_error), Toast.LENGTH_SHORT).show();
                } else {
                    // Start the game
                    Intent intent = new Intent(MenuActivity.this, GameActivity.class);
                    startActivity(intent);
                }
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
