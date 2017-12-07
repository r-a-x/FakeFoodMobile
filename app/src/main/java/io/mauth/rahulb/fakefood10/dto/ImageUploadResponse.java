package io.mauth.rahulb.fakefood10.dto;

import java.io.Serializable;

/**
 * Created by rahulb on 5/12/17.
 */

public class ImageUploadResponse implements Serializable {

    private String frontImageName;
    private String logoImageName;
    private String backImageName;

    public ImageUploadResponse() {
    }

    public ImageUploadResponse(String frontImageName, String logoImageName, String backImageName) {
        this.frontImageName = frontImageName;
        this.logoImageName = logoImageName;
        this.backImageName = backImageName;
    }

    public String getFrontImageName() {
        return frontImageName;
    }

    public void setFrontImageName(String frontImageName) {
        this.frontImageName = frontImageName;
    }

    public String getLogoImageName() {
        return logoImageName;
    }

    public void setLogoImageName(String logoImageName) {
        this.logoImageName = logoImageName;
    }

    public String getBackImageName() {
        return backImageName;
    }

    public void setBackImageName(String backImageName) {
        this.backImageName = backImageName;
    }
}
