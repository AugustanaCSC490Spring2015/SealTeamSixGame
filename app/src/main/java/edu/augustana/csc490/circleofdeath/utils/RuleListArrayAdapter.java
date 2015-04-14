package edu.augustana.csc490.circleofdeath.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.augustana.csc490.circleofdeath.R;
import edu.augustana.csc490.circleofdeath.Rule;

/**
 * Created by Dan on 4/14/15.
 */
public class RuleListArrayAdapter extends ArrayAdapter<Rule> {

    private final Context context;
    private int resource;
    private final List<Rule> values;

    public RuleListArrayAdapter(Context context, int resource, List<Rule> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.values = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        TextView cardValue = (TextView) rowView.findViewById(R.id.cardValue);
        TextView cardRule = (TextView) rowView.findViewById(R.id.cardRule);

        cardValue.setText(values.get(position).getCardValue());
        cardRule.setText(values.get(position).getCardRule());

        return rowView;
    }
}
