package io.mauth.rahulb.fakefood10.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by rahulb on 5/12/17.
 */

public class ImageUploadRequest implements Serializable {
    private Bitmap frontImage;
    private Bitmap backImage;
    private Bitmap logoImage;

    public ImageUploadRequest(Bitmap frontImage, Bitmap backImage, Bitmap logoImage) {
        this.frontImage = frontImage;
        this.backImage = backImage;
        this.logoImage = logoImage;
    }

    public Bitmap getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(Bitmap frontImage) {
        this.frontImage = frontImage;
    }

    public Bitmap getBackImage() {
        return backImage;
    }

    public void setBackImage(Bitmap backImage) {
        this.backImage = backImage;
    }

    public Bitmap getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(Bitmap logoImage) {
        this.logoImage = logoImage;
    }
}
