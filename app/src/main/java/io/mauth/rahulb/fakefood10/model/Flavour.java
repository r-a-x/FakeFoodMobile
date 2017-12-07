package io.mauth.rahulb.fakefood10.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.mauth.rahulb.fakefood10.util.Constants;

/**
 * Created by rahulb on 2/11/17.
 */

public class Flavour {

    String flavour;

    public Flavour(String flavour){
        this.flavour = flavour;
    }

    public Flavour(JSONObject object){
        try {
            this.flavour = object.getString(Constants.PRODUCT_FLAVOUR);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Factory method to convert an array of JSON objects into a list of objects
    // User.fromJson(jsonArray);
    public static ArrayList<Flavour> fromJson(JSONArray jsonObjects) {
        ArrayList<Flavour> users = new ArrayList<Flavour>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                users.add(new Flavour(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public String getFlavour() {
        return flavour;
    }

    public String toString(){
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }
}
