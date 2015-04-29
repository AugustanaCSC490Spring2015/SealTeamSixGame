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

/**
 * Created by horuscuevas11 on 4/17/2015.
 */
public class CustomRuleDialog extends AlertDialog{

    private RadioGroup radioButtons;
    private RadioButton firstOption;
    private RadioButton customOption;
    private TextView firstOptionText;
    private EditText customizedRuleField;
    private Button okButton;
    private Button cancelButton;
    private String rule;
    private String ruleName;

    protected CustomRuleDialog(Context context, String tempRule)
    {
        super(context);
        rule = tempRule;
        String [] tempArray = tempRule.split(" ", 2);
        ruleName = tempArray[0];
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_custom_rules);

        radioButtons = (RadioGroup) findViewById(R.id.ruleButtons);
        firstOption = (RadioButton) findViewById(R.id.optionOne);
        customOption = (RadioButton) findViewById(R.id.customOption);
        customizedRuleField = (EditText) findViewById(R.id.customizedRuleField);
        okButton = (Button) findViewById(R.id.okButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        firstOption.setText(ruleName);
        firstOptionText = (TextView) findViewById(R.id.optionOneText);
        firstOptionText.setText(rule);

        //Makes the view scrollable in case the rule is long
        firstOptionText.setMovementMethod(new ScrollingMovementMethod());

        //getWindow().getCurrentFocus().
        //final InputMethodManager keyboard = (InputMethodManager) (Context.INPUT_METHOD_SERVICE);

        customizedRuleField.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                customizedRuleField.requestFocus();
                //keyboard.showSoftInput(customizedRuleField, 0);

            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(firstOption.isChecked()){
                    //Todo once gamemanager is complete, return what needs to be returned.
                    //return the standard rule
                } else {
                    String text = customizedRuleField.getText().toString();
                    if(text == "Type in your rule here!" || text == ""){
                        //return the standard rule
                    } else {
                        customizedRuleField.getText();
                    }
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //return the standard rule
            }
        });
    }
}
