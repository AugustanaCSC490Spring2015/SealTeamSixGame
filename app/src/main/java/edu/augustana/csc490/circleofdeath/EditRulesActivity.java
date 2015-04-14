package edu.augustana.csc490.circleofdeath;

import android.app.Activity;
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
 * Created by Dan on 4/14/15.
 */
public class EditRulesActivity extends Activity {
    Button restoreDefaultsButton;
    Button saveRulesButton;
    ListView rulesListView;

    private List<Rule> rules;
    private List<Rule> defaultRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_rules);

        // Get references to view items
        restoreDefaultsButton = (Button) findViewById(R.id.restoreDefaultsButton);
        saveRulesButton = (Button) findViewById(R.id.saveRulesButton);
        rulesListView = (ListView) findViewById(R.id.rulesListView);

        // create the default rules and add the strings to it
        defaultRules = new ArrayList<Rule>();

        defaultRules.add(new Rule("Ace", getResources().getString(R.string.default_ace)));
        defaultRules.add(new Rule("King", getResources().getString(R.string.default_king)));
        defaultRules.add(new Rule("Queen", getResources().getString(R.string.default_queen)));
        defaultRules.add(new Rule("Jack", getResources().getString(R.string.default_jack)));
        defaultRules.add(new Rule("Ten", getResources().getString(R.string.default_ten)));
        defaultRules.add(new Rule("Nine", getResources().getString(R.string.default_nine)));
        defaultRules.add(new Rule("Eight", getResources().getString(R.string.default_eight)));
        defaultRules.add(new Rule("Seven", getResources().getString(R.string.default_seven)));
        defaultRules.add(new Rule("Six", getResources().getString(R.string.default_six)));
        defaultRules.add(new Rule("Five", getResources().getString(R.string.default_five)));
        defaultRules.add(new Rule("Four", getResources().getString(R.string.default_four)));
        defaultRules.add(new Rule("Three", getResources().getString(R.string.default_three)));
        defaultRules.add(new Rule("Two", getResources().getString(R.string.default_two)));

        // make a copy for the rules that can be changed
        rules = new ArrayList<>(defaultRules);

        RuleListArrayAdapter adapter = new RuleListArrayAdapter(this, R.layout.rule_list_item, rules);
        rulesListView.setAdapter(adapter);

        rulesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(EditRulesActivity.this, "Rule Editing not yet implemented", Toast.LENGTH_SHORT).show();
            }
        });

        restoreDefaultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditRulesActivity.this, "Restore defaults not yet implemented", Toast.LENGTH_SHORT).show();
            }
        });

        saveRulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditRulesActivity.this, "Save Rules not yet implemented", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void restoreDefaults() {
        rules = new ArrayList<>(defaultRules);
    }
}
