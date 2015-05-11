package edu.augustana.csc490.circleofdeath;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * The GameModeDialog class extends Dialog and is called from the game setup screen. It contains
 * spinners to select game rules such as the mode, break circle action, and pop tab action.
 */
public class GameModeDialog extends Dialog {

    Button closeGameRulesButton;
    Spinner gameModeSpinner;
    Spinner popTabSpinner;

    public GameModeDialog(Context context) {
        super(context);

        // Set the dialogs view
        this.setContentView(R.layout.dialog_game_rules);
        // Set the dialog's title
        this.setTitle(R.string.edit_rules_dialog_title);


        //TODO: Spinners should load current game setting from settings

        // Set up the spinners
        gameModeSpinner = (Spinner) findViewById(R.id.gameModeSpinner);
        ArrayAdapter<CharSequence> gameModeSpinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.game_modes, android.R.layout.simple_spinner_item);
        gameModeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameModeSpinner.setAdapter(gameModeSpinnerAdapter);
        gameModeSpinner.setSelection(GameManager.getGameMode());
        gameModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // single deck mode
                        GameManager.setGameMode(GameManager.DECK_SINGLE_MODE);
                        break;
                    case 1: // endless mode
                        GameManager.setGameMode(GameManager.DECK_ENDLESS_MODE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });

        popTabSpinner = (Spinner) findViewById(R.id.popTabSpinner);
        ArrayAdapter<CharSequence> popTabSpinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.pop_tab_mode, android.R.layout.simple_spinner_item);
        popTabSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        popTabSpinner.setAdapter(popTabSpinnerAdapter);
        popTabSpinner.setSelection(GameManager.getPopTabMode());
        popTabSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // Random
                        GameManager.setPopTabMode(GameManager.POP_TAB_RANDOM);
                        break;
                    case 1: // After turn 20
                        GameManager.setPopTabMode(GameManager.POP_TAB_AFTER_TURN_20);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });

        // Setup close button and add action listener
        closeGameRulesButton = (Button) findViewById(R.id.saveGameRulesButton);
        closeGameRulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // Close Dialog
            }
        });

    }
}
