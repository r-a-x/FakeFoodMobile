package io.mauth.rahulb.fakefood10.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.util.Map;

import io.mauth.rahulb.fakefood10.model.ProductAuditRequest;
import io.mauth.rahulb.fakefood10.util.Util;

/**
 * Created by rahulb on 4/11/17.
 */

public class AuditService {

//    private static final String host = "http://192.168.0.103:8080";
    private static final String host = "http://35.202.86.142:8080";
    private static final String AUDITS_PATH = "/audits";
    private static final String PRODUCTS_PATH="/products";
    private static final String ANDROID_ID_PARAM = "?androidId=";
    private static final String FILE_PATH ="/file";
    public static final String LOGO_IMAGE_PARAM = "logoImage";
    public static final String BACK_IMAGE_PARAM = "backImage";
    public static final String FRONT_IMAGE_PARAM = "frontImage";
    public static final String FILE_PARAM ="?file=";
    private static final String RESELLER_PATH = "/resellers";


    private Context context;
    private Singleton singleton;

    public AuditService(Context context) {
        this.context = context;
        singleton = Singleton.getInstance(context);
    }

// I need to specify the steps that need to be taken for executing this

    public void getAudits(String androidId,Response.Listener<JSONArray> listener,
                                         Response.ErrorListener errorListener){

        String url = host  + AUDITS_PATH + ANDROID_ID_PARAM + androidId;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url,null ,
                listener,errorListener);
        singleton.addToRequestQueue(jsonArrayRequest);

    }

    public void postAudits(String androidId, ProductAuditRequest productAuditRequest,
                           Response.Listener<JSONArray> listener,
                           Response.ErrorListener errorListener) throws JSONException {

        String url = host  + AUDITS_PATH ;
        String request =  Util.getJson(productAuditRequest,ProductAuditRequest.class) ;
        CustomJSONArrayRequest jsonArrayRequest = new CustomJSONArrayRequest(Request.Method.POST,url,request,listener,errorListener);
        singleton.addToRequestQueue(jsonArrayRequest);

    }

    public void getProducts(Response.Listener<JSONArray> listener,
                            Response.ErrorListener errorListener){
        String url = host + PRODUCTS_PATH;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url,null,listener,errorListener);
        singleton.addToRequestQueue(jsonArrayRequest);
    }

    public void postImages(String androidId, Map<String,File> files,
                           Response.Listener<String>listener,
                           Response.ErrorListener errorListener){
        String url = host + FILE_PATH;
        MultiPartRequest multiPartRequest = new MultiPartRequest(errorListener,listener,files,url);
        singleton.addToRequestQueue(multiPartRequest);
    }

    public void getImages(String androidId,
                          String imageName,
                          Response.Listener<Bitmap> listener,
                          Response.ErrorListener errorListener){

        String url = host + FILE_PATH + FILE_PARAM + imageName;
        ImageRequest imageRequest = new ImageRequest(url,listener,0,0, ImageView.ScaleType.CENTER_CROP,Bitmap.Config.RGB_565,errorListener);
        singleton.addToRequestQueue(imageRequest);

    }

    public void getResller(String androidId,
                           Response.Listener<JSONArray> listener,
                           Response.ErrorListener errorListener
                           ){
        String url = host + RESELLER_PATH;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url,null,listener,errorListener);
        singleton.addToRequestQueue(jsonArrayRequest);
    }
}
