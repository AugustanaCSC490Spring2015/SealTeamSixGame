package edu.augustana.csc490.circleofdeath;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
    private TextView customOptionText;
    private TextView cardName;
    private EditText customizedRuleField;
    private Rule rule;
    private Button okButton;
    private Button cancelButton;

    protected CustomRuleDialog(Context context, Rule standardRule)
    {
        super(context);
        rule = standardRule;
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

        firstOption.setText(rule.cardValue);
        firstOptionText = (TextView) findViewById(R.id.optionOneText);
        firstOptionText.setText(rule.cardRule);

        //Makes the view scrollable in case the rule is long
        firstOptionText.setMovementMethod(new ScrollingMovementMethod());
        cardName = (TextView) findViewById(R.id.cardName);

        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(firstOption.isChecked()){
                    //Todo once gamemanager is complete, return what needs to be returned.
                    //return the standard rule
                } else {
                    String text = customizedRuleField.getText().toString();
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //return the standard rule
            }
        });
        /*
        //The OK button
        this.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        //The cancel button
        this.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        */
    }
}
