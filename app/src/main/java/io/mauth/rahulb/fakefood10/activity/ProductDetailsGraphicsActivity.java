package io.mauth.rahulb.fakefood10.activity;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import io.mauth.rahulb.fakefood10.R;
import io.mauth.rahulb.fakefood10.core.AuditService;
import io.mauth.rahulb.fakefood10.core.IntentIntegrator;
import io.mauth.rahulb.fakefood10.core.IntentResult;
import io.mauth.rahulb.fakefood10.core.Singleton;
import io.mauth.rahulb.fakefood10.dto.ImageUploadResponse;
import io.mauth.rahulb.fakefood10.fragment.DatePickerFragment;
import io.mauth.rahulb.fakefood10.model.ProductAuditRequest;
import io.mauth.rahulb.fakefood10.util.Constants;
import io.mauth.rahulb.fakefood10.util.Util;

public class ProductDetailsGraphicsActivity extends AppCompatActivity {


    private ProductAuditRequest productAuditRequest;
    private AuditService auditService;
    private Bitmap logoImage;
    private Bitmap backImage;
    private Bitmap frontImage;
    private ImageUploadResponse imageUploadResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details_graphics);
        auditService = new AuditService(this);
        productAuditRequest = (ProductAuditRequest) getIntent().getSerializableExtra(Constants.PRODUCT_AUDIT_REQUEST_KEY);
        if ( productAuditRequest == null)
            productAuditRequest = new ProductAuditRequest();
    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void openBarCodeScanner(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
        Intent barCodeScannerIntent = new Intent("com.google.zxing.client.android.SCAN");
        barCodeScannerIntent.putExtra("SCAN_MODE", "BAR_CODE_MODE");
        startActivityForResult(barCodeScannerIntent, Constants.SCANNER_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            Toast.makeText(this,"Oops !! Error opening one of the input ",Toast.LENGTH_SHORT).show();
        }
        if ( resultCode !=  RESULT_OK){
            switch (requestCode){
                case Constants.SCANNER_CODE:
                    Toast.makeText(this,"Unable to Choose the BarCode! Try again",Toast.LENGTH_SHORT).show();
                    return;
                case Constants.FRONT_IMAGE:
                    Toast.makeText(this,"Unable to Choose the Front Image ! Try again",Toast.LENGTH_SHORT).show();
                    return;
                case Constants.BACK_IMAGE:
                    Toast.makeText(this,"Unable to Choose the Back Image ! Try again",Toast.LENGTH_SHORT).show();
                    return;
                case Constants.LOGO_IMAGE:
                    Toast.makeText(this,"Unable to Choose the Logo Image ! Try again",Toast.LENGTH_SHORT).show();
                    return;
            }
        }

        switch (requestCode){
            case Constants.SCANNER_CODE:
                productAuditRequest.setBarCode( data.getStringExtra("SCAN_RESULT"));
                return;
            case Constants.BACK_IMAGE:
                backImage = getBitmap(data);
                processImageUploadRequest();
                return;
            case Constants.FRONT_IMAGE:
                frontImage = getBitmap(data);
                processImageUploadRequest();
                return;
            case Constants.LOGO_IMAGE:
                logoImage = getBitmap(data);
                processImageUploadRequest();
                return;
        }
    }

    private String extractBitmap(Intent data){
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        return Util.toBase64(Util.bitmapToByteArray(imageBitmap));
    }


    public void submitRequest(View view) {
        try {
            processAuditRequest();
        } catch (JSONException e) {
            Toast.makeText(this,"Oops !! Error uploading the Audit Request",Toast.LENGTH_SHORT).show();
        }
    }

    public void  openCamera(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, Constants.FRONT_IMAGE);
        }
    }

    public void takeBackImage(View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, Constants.BACK_IMAGE);
        }
    }

    public void takeLogoImage(View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, Constants.LOGO_IMAGE);
        }
    }

    private Bitmap getBitmap(Intent data){
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        return imageBitmap;
    }

    private Map<String,File> createImageUploadRequest(){
        Map<String,File> hashMap = new HashMap<>();
        File logoImage = Util.convertBitmapToFile(this.logoImage,this);
        File backImage = Util.convertBitmapToFile(this.backImage,this);
        File frontImage = Util.convertBitmapToFile(this.frontImage,this);
        hashMap.put(AuditService.LOGO_IMAGE_PARAM,logoImage);
        hashMap.put(AuditService.BACK_IMAGE_PARAM,backImage);
        hashMap.put(AuditService.FRONT_IMAGE_PARAM,frontImage);
        return hashMap;
    }

    private void processImageUploadRequest(){

        if ( logoImage == null || backImage == null || frontImage == null)
            return;

        final Response.Listener<String> imageResponse = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {

                Type token = new TypeToken<ImageUploadResponse>() {}.getType();
                imageUploadResponse = Util.gson.fromJson(response, token);

            }
        };

        Response.ErrorListener imageError = new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        };

        AuditService auditService = new AuditService(this);
        auditService.postImages(Constants.androidId,
                createImageUploadRequest(),
                imageResponse,
                imageError);
    }

    private void processAuditRequest() throws JSONException {

        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("API",error.toString());
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }

        };

        Response.Listener<JSONArray> responseArray=new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(getApplicationContext(),"The request submitted to server",Toast.LENGTH_LONG).show();
                processMainActivity();
            }
        };

        productAuditRequest.setLogoImageName(imageUploadResponse.getLogoImageName());
        productAuditRequest.setBackImageName(imageUploadResponse.getBackImageName());
        productAuditRequest.setFrontImageName(imageUploadResponse.getFrontImageName());

        if ( !isAllPropertyScanned(productAuditRequest))
            return;

        AuditService auditService = new AuditService(this);
        auditService.postAudits(Constants.androidId,
                productAuditRequest,
                responseArray,
                errorListener);

    }

    private void processMainActivity() {
    }

    private Boolean isAllPropertyScanned(ProductAuditRequest productAuditRequest){

        if ( productAuditRequest.getBarCode() == null) {
            Toast.makeText(this, "Please choose the BarCode", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( this.backImage == null) {
            Toast.makeText(this, "Please choose the BackImage", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( this.frontImage == null) {
            Toast.makeText(this, "Please choose the FrontImage", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( this.logoImage == null) {
            Toast.makeText(this, "Please choose the BackImage", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( productAuditRequest.getExpirationDate() == null){
            Toast.makeText(this, "Please choose the ExpirationDate", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
