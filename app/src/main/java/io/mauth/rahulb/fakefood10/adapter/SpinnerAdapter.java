package io.mauth.rahulb.fakefood10.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.mauth.rahulb.fakefood10.R;

/**
 * Created by rahulb on 2/11/17.
 */

public class SpinnerAdapter extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<String> items;
    public Resources res;
    private String currRowVal = null;
    LayoutInflater inflater;

    public SpinnerAdapter(Context context, int resource,ArrayList<String> items,Resources res) {
        super(context, resource);
        this.context = context;
        this.res = res ;
        this.items = items;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
//        View row = inflater.inflate(R.layout.status_item, parent, false);
//        currRowVal = null;
//        currRowVal = items.get(position);
//        TextView label = (TextView) row.findViewById(R.id.spinnerItem);
//        if (position == 0) {
//            label.setText("Please select status");
//        } else {
//            label.setText(currRowVal);
//        }
//
//        return row;
        return null;
    }
}
