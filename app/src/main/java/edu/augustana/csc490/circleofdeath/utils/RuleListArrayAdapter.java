package edu.augustana.csc490.circleofdeath.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;

import edu.augustana.csc490.circleofdeath.R;
import edu.augustana.csc490.circleofdeath.enums.Number;

public class RuleListArrayAdapter extends BaseAdapter {

    private final Context context;
    private int resource;
    private HashMap<Number, String> rules;
    private String[] keys;


    public RuleListArrayAdapter(Context context, int resource, HashMap<Number, String> theRules) {
        rules = theRules;
        this.context = context;
        this.resource = resource;
        keys = Number.names();
    }


    public int getCount(){ return rules.size(); }

    @Override
    public String getItem(int position){ return rules.get(NumberUtils.getEnumNumberFromString(keys[position])).toString(); }

    public Number getEnumItem(int position){ return NumberUtils.getEnumNumberFromString(keys[position]); }

    @Override
    public long getItemId(int arg0) { return arg0; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String key = this.keys[position];
        String value = getItem(position);

        View row = convertView;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(resource, parent, false);

        TextView cardValue = (TextView) row.findViewById(R.id.cardValue);
        TextView cardRule = (TextView) row.findViewById(R.id.cardRule);

        cardValue.setText(key);
        cardRule.setText(value);

        return row;
    }
}
