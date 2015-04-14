package edu.augustana.csc490.circleofdeath.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.augustana.csc490.circleofdeath.R;

/**
 * Created by Dan on 4/13/15.
 */
public class PlayerListArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private int resource;
    private final List<String> values;

    public PlayerListArrayAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.values = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.playerName);
        textView.setText(values.get(position));

        ImageButton deletePlayerButton = (ImageButton) rowView.findViewById(R.id.deletePlayerButton);
        deletePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                values.remove(values.get(position));
                notifyDataSetChanged();
            }
        });

        return rowView;
    }
}
