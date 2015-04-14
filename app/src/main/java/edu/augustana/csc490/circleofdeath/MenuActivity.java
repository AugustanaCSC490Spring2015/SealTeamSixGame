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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.augustana.csc490.circleofdeath.utils.PlayerListArrayAdapter;

/**
 * Created by Dan on 4/13/15.
 */
public class MenuActivity extends Activity {

    Button editCardRulesButton;
    ImageButton addPlayerButton;
    ListView playerListView;
    Button startGameButton;

    private List<String> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);

        //Get References to view items
        editCardRulesButton = (Button) findViewById(R.id.editCardRulesButton);
        addPlayerButton = (ImageButton) findViewById(R.id.addPlayerButton);
        playerListView = (ListView) findViewById(R.id.playersListView);
        startGameButton = (Button) findViewById(R.id.startButton);


        players = new ArrayList<String>();

        PlayerListArrayAdapter adapter = new PlayerListArrayAdapter(this, R.layout.player_list_item, players);
        playerListView.setAdapter(adapter);

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
                        if (!players.contains(name.toString()) && !name.toString().equals("") && name.toString() != null){
                            players.add(name.toString());
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
            //TODO: Check players were added, etc..
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        editCardRulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Not implemented yet", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MenuActivity.this, EditRulesActivity.class);
                startActivity(intent);
            }
        });
    }
}
