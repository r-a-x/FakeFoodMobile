package io.mauth.rahulb.fakefood10.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by rahulb on 11/11/17.
 */

public interface ListableProduct extends Serializable{
    public Bitmap getImage();
    public String getData();
}
