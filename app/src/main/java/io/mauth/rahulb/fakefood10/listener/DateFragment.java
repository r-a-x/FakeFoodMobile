package io.mauth.rahulb.fakefood10.listener;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;

/**
 * Created by rahulb on 9/12/17.
 */

public class DateFragment extends DialogFragment {

    public interface DateFragmentListener{
       public void onDateSelected(DialogFragment dialogFragment);
    }
    DateFragmentListener dateFragmentListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            dateFragmentListener = (DateFragmentListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

}
