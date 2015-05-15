package edu.augustana.csc490.circleofdeath;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import edu.augustana.csc490.circleofdeath.utils.NumberUtils;
import edu.augustana.csc490.circleofdeath.enums.Number;

/**
 * Created by horuscuevas11 on 4/17/2015.
 */
public class CustomRuleDialog extends AlertDialog{

    private RadioGroup radioButtons;
    private RadioButton defaultOption;
    private RadioButton customOption;
    private TextView firstOptionText;
    private EditText customizedRuleField;
    private Button okButton;
    private Button cancelButton;
    private TextView cardName;
    private String rule;
    private String ruleName;
    private Number key;

    protected CustomRuleDialog(Context context, String tempRule, Number tempKey)
    {
        super(context);
        rule = tempRule;
        String [] tempArray = tempRule.split(" ", 2);
        ruleName = tempArray[0];
        key = tempKey;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_custom_rules);

        radioButtons = (RadioGroup) findViewById(R.id.ruleButtons);
        defaultOption = (RadioButton) findViewById(R.id.optionOne);
        customOption = (RadioButton) findViewById(R.id.customOption);
        customizedRuleField = (EditText) findViewById(R.id.customizedRuleField);
        okButton = (Button) findViewById(R.id.okButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        cardName = (TextView) findViewById(R.id.cardName);

        customizedRuleField.setEnabled(false);

        //sets the defaultOption radiobutton to true.
        defaultOption.setChecked(true);

        //If a custom rule has been set, it sets the customOption radio button to true and displays
        //the rule in the customizedRuleField
        if(!rule.equals(GameManager.defaultRules.get(key))){
            customOption.setChecked(true);
            customizedRuleField.setText(rule);
            customizedRuleField.setEnabled(true);
        }

        //Sets the default rule and its name from the GameManager's defaultRules
        String[] tempDefaultRuleName = GameManager.defaultRules.get(key).split(" ", 2);
        String temp = tempDefaultRuleName[0];
        defaultOption.setText(temp);
        firstOptionText = (TextView) findViewById(R.id.optionOneText);
        firstOptionText.setText(GameManager.defaultRules.get(key));
        cardName.setText(NumberUtils.getStringFromEnumNumber(key));

        //Makes the view scrollable in case the rule is long
        firstOptionText.setMovementMethod(new ScrollingMovementMethod());

        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(defaultOption.isChecked()){
                    //return the standard rule
                    GameManager.rules.put(key, GameManager.defaultRules.get(key));
                } else {
                    String text = customizedRuleField.getText().toString();
                    if(text == null || text.equals("")){
                        //return the standard rule
                        Toast.makeText(getContext(), R.string.blank_rule_error, Toast.LENGTH_SHORT).show();
                    } else {
                        GameManager.rules.put(key, customizedRuleField.getText().toString());
                    }
                }
                dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });

        //The customOption listener and default option listener make it so that the customizedRuleField is only enabled when the customOption button is checked
        customOption.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                customizedRuleField.setEnabled(true);
            }
        });

        defaultOption.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                customizedRuleField.setEnabled(false);
            }
        });
    }
}
