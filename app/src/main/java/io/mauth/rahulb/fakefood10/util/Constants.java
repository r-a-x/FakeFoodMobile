package io.mauth.rahulb.fakefood10.util;

import android.content.ContextWrapper;
import android.provider.Settings;

/**
 * Created by rahulb on 1/11/17.
 */

public class Constants {
    public static final String PRODUCT_AUDIT_REQUEST_KEY = "product_check_request";

    public static final String PRODUCT_FLAVOUR ="flavour" ;

    public static final int SCANNER_CODE=0;

    public static final int FRONT_IMAGE=1;

    public static final int BACK_IMAGE=2;

    public static final int LOGO_IMAGE=3;

    public static final String androidId="b" ;

    public static String getAndroidId( ContextWrapper contextWrapper ){
        return Settings.Secure.getString(contextWrapper.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
    public static final String PRODUCT_DETAIL_KEY = "product_detail_key" ;
}
