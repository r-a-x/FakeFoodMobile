package io.mauth.rahulb.fakefood10.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.DatePicker;

import java.util.Calendar;

import io.mauth.rahulb.fakefood10.activity.ProductDetailsGraphicsActivity;

/**
 * Created by rahulb on 3/11/17.
 */

public class DatePickerFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), dateSetListener,year, month, day);
    }

    public DatePickerFragment setListener(DatePickerDialog.OnDateSetListener listener) {
        this.dateSetListener = listener;
        return this;
    }
}
