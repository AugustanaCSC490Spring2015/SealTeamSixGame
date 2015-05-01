package edu.augustana.csc490.circleofdeath;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
        final RuleListArrayAdapter adapter = new RuleListArrayAdapter(this, R.layout.rule_list_item, GameManager.rules);
        rulesListView.setAdapter(adapter);
        rulesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomRuleDialog crd = new CustomRuleDialog(EditRulesActivity.this, adapter.getItem(position), adapter.getEnumItem(position));
                crd.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        adapter.notifyDataSetChanged();
                    }
                });
                crd.show();
                crd.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                crd.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
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
