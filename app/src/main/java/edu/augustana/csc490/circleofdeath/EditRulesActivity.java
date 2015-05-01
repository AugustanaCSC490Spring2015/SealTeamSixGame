package edu.augustana.csc490.circleofdeath;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import edu.augustana.csc490.circleofdeath.utils.RuleListArrayAdapter;

/**
 * The EditRulesActivity displays a list of rules and will eventually allow editing of them
 */
public class EditRulesActivity extends ActionBarActivity {
    ListView rulesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout view
        setContentView(R.layout.activity_edit_rules);

        // Set the up arrow in the ActionBar to the home screen
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Get references to view items
        rulesListView = (ListView) findViewById(R.id.rulesListView);

        // Create and set the custom ArrayAdapter
        final RuleListArrayAdapter adapter = new RuleListArrayAdapter(this, R.layout.rule_list_item, GameManager.rules);
        rulesListView.setAdapter(adapter);
        rulesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomRuleDialog customRuleDialog = new CustomRuleDialog(EditRulesActivity.this, adapter.getItem(position), adapter.getEnumItem(position));
                customRuleDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        // refresh the rule list after dialog closes
                        adapter.notifyDataSetChanged();
                    }
                });
                customRuleDialog.show();

            }
        });
    }
}
