package io.mauth.rahulb.fakefood10.data;

import java.util.ArrayList;
import java.util.List;

import io.mauth.rahulb.fakefood10.adapter.SpinnerAdapter;

/**
 * Created by rahulb on 2/11/17.
 */

public class FlavourData {

    private List<String> flavour ;
    private SpinnerAdapter spinnerAdapter;

    public FlavourData() {

        flavour = new ArrayList<>();
        flavour.add("Double Choclate");
        flavour.add("Whip Cream");
        flavour.add("Choclate Cookie");

//        spinnerAdapter = new SpinnerAdapter(flavour);
    }

    public List<String> getFlavour() {
        return flavour;
    }

    public void setFlavour(List<String> flavour) {
        this.flavour = flavour;
    }

    public SpinnerAdapter getSpinnerAdapter() {
        return spinnerAdapter;
    }

    public void setSpinnerAdapter(SpinnerAdapter spinnerAdapter) {
        this.spinnerAdapter = spinnerAdapter;
    }
}
