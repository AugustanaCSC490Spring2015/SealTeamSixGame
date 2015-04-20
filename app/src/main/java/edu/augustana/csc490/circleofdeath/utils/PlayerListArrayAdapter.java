package edu.augustana.csc490.circleofdeath.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.augustana.csc490.circleofdeath.Player;
import edu.augustana.csc490.circleofdeath.R;

/**
 * Created by Dan on 4/13/15.
 */
public class PlayerListArrayAdapter extends ArrayAdapter<Player> {

    private final Context context;
    private int resource;
    private final List<Player> values;

    public PlayerListArrayAdapter(Context context, int resource, List<Player> objects) {
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
        textView.setText(values.get(position).getName());

        ImageButton deletePlayerButton = (ImageButton) rowView.findViewById(R.id.deletePlayerButton);
        deletePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                values.remove(values.get(position));
                notifyDataSetChanged();
            }
        });

        ImageButton editPlayerButton = (ImageButton) rowView.findViewById(R.id.editPlayerButton);
        editPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(getContext());
                input.setText(values.get(position).getName());
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle(R.string.edit_name);
                alert.setView(input);
                alert.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Player newName = new Player(input.getText().toString());
                        if (!newName.equals(values.get(position).getName()) && !newName.equals("") && newName != null && !values.contains(newName)) {
                            values.set(position, newName).getName();
                            notifyDataSetChanged();
                        } else {
                            // do nothing
                        }
                    }
                });
                alert.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                });
                alert.show();
            }
        });

        return rowView;
    }
}
