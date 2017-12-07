package io.mauth.rahulb.fakefood10.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.mauth.rahulb.fakefood10.R;
import io.mauth.rahulb.fakefood10.model.Flavour;

/**
 * Created by rahulb on 2/11/17.
 */

public class FlavourAdapter extends ArrayAdapter<String>{

    public FlavourAdapter(Context context, int resource, List<String> flavours) {
        super(context, resource,flavours);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String flavour = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_layout, parent, false);
        }

        // Lookup view for data population

        TextView flavourTextView = (TextView) convertView.findViewById(R.id.spinnerTextView);
        // Populate the data into the template view using the data object
        flavourTextView.setText(flavour);

        return convertView;
    }

}
