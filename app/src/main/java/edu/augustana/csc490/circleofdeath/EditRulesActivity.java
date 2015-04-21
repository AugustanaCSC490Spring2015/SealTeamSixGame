package edu.augustana.csc490.circleofdeath;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.augustana.csc490.circleofdeath.utils.RuleListArrayAdapter;

/**
 * The EditRulesActivity displays a list of rules and will eventually allow editing of them
 */
public class EditRulesActivity extends Activity {
    Button restoreDefaultsButton;
    Button saveRulesButton;
    ListView rulesListView;
    AlertDialog ruleAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout view
        setContentView(R.layout.activity_edit_rules);

        // Get references to view items
        restoreDefaultsButton = (Button) findViewById(R.id.restoreDefaultsButton);
        saveRulesButton = (Button) findViewById(R.id.saveRulesButton);
        rulesListView = (ListView) findViewById(R.id.rulesListView);


        // create the default rules and add the strings to it
        // NOTE: This may not be the best way to do this, maybe they could be kept in a RuleSet
        GameManager.defaultRules = new ArrayList<Rule>();
        GameManager.defaultRules.add(new Rule("Ace", getResources().getString(R.string.default_ace)));
        GameManager.defaultRules.add(new Rule("King", getResources().getString(R.string.default_king)));
        GameManager.defaultRules.add(new Rule("Queen", getResources().getString(R.string.default_queen)));
        GameManager.defaultRules.add(new Rule("Jack", getResources().getString(R.string.default_jack)));
        GameManager.defaultRules.add(new Rule("Ten", getResources().getString(R.string.default_ten)));
        GameManager.defaultRules.add(new Rule("Nine", getResources().getString(R.string.default_nine)));
        GameManager.defaultRules.add(new Rule("Eight", getResources().getString(R.string.default_eight)));
        GameManager.defaultRules.add(new Rule("Seven", getResources().getString(R.string.default_seven)));
        GameManager.defaultRules.add(new Rule("Six", getResources().getString(R.string.default_six)));
        GameManager.defaultRules.add(new Rule("Five", getResources().getString(R.string.default_five)));
        GameManager.defaultRules.add(new Rule("Four", getResources().getString(R.string.default_four)));
        GameManager.defaultRules.add(new Rule("Three", getResources().getString(R.string.default_three)));
        GameManager.defaultRules.add(new Rule("Two", getResources().getString(R.string.default_two)));

        // make a copy for the rules that can be changed
        GameManager.rules = new ArrayList<>(GameManager.defaultRules);

        // Create and set the custom ArrayAdapter
        RuleListArrayAdapter adapter = new RuleListArrayAdapter(this, R.layout.rule_list_item, GameManager.rules);
        rulesListView.setAdapter(adapter);
        rulesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO
                Toast.makeText(EditRulesActivity.this, "Rule Editing not yet implemented", Toast.LENGTH_SHORT).show();
                new CustomRuleDialog(EditRulesActivity.this, GameManager.defaultRules.get(position)).show();
            }
        });

        // Button on click listeners
        restoreDefaultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Toast.makeText(EditRulesActivity.this, "Restore defaults not yet implemented", Toast.LENGTH_SHORT).show();
            }
        });

        saveRulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Toast.makeText(EditRulesActivity.this, "Save Rules not yet implemented", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
