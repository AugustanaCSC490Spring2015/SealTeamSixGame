package edu.augustana.csc490.circleofdeath;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

        // Create and set the custom ArrayAdapter
        RuleListArrayAdapter adapter = new RuleListArrayAdapter(this, R.layout.rule_list_item, GameManager.rules);
        rulesListView.setAdapter(adapter);
        rulesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO
                Toast.makeText(EditRulesActivity.this, "Rule Editing not yet implemented", Toast.LENGTH_SHORT).show();
                //new CustomRuleDialog(EditRulesActivity.this, GameManager.defaultRules.get(position)).show();
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
