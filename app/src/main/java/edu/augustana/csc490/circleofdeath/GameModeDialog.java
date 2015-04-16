package edu.augustana.csc490.circleofdeath;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * The GameModeDialog class extends Dialog and is called from the game setup screen. It contains
 * spinners to select game rules such as the mode, break circle action, and pop tab action.
 */
public class GameModeDialog extends Dialog {

    Button saveGameRulesButton;
    Spinner gameModeSpinner;
    Spinner breakCircleSpinner;
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
        ArrayAdapter<CharSequence> gameModeSpinnerAdapter = ArrayAdapter.createFromResource(getContext(),R.array.game_modes, android.R.layout.simple_spinner_item);
        gameModeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameModeSpinner.setAdapter(gameModeSpinnerAdapter);

        breakCircleSpinner = (Spinner) findViewById(R.id.breakCircleSpinner);
        ArrayAdapter<CharSequence> breakCircleSpinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.break_circle_mode, android.R.layout.simple_spinner_item);
        breakCircleSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breakCircleSpinner.setAdapter(breakCircleSpinnerAdapter);

        popTabSpinner = (Spinner) findViewById(R.id.popTabSpinner);
        ArrayAdapter<CharSequence> popTabSpinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.pop_tab_mode, android.R.layout.simple_spinner_item);
        popTabSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        popTabSpinner.setAdapter(popTabSpinnerAdapter);

        // Setup save button and add action listener
        saveGameRulesButton = (Button) findViewById(R.id.saveGameRulesButton);
        saveGameRulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Add saving functionality
                dismiss(); // Close Dialog
            }
        });

    }
}
