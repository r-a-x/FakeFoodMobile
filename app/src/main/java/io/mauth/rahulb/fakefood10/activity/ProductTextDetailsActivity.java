package io.mauth.rahulb.fakefood10.activity;

import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.mauth.rahulb.fakefood10.R;
import io.mauth.rahulb.fakefood10.adapter.FlavourAdapter;
import io.mauth.rahulb.fakefood10.adapter.ResellerAdapter;
import io.mauth.rahulb.fakefood10.adapter.SizeAdapter;
import io.mauth.rahulb.fakefood10.core.AuditService;
import io.mauth.rahulb.fakefood10.model.Product;
import io.mauth.rahulb.fakefood10.model.ProductAuditRequest;
import io.mauth.rahulb.fakefood10.model.Reseller;
import io.mauth.rahulb.fakefood10.util.Constants;
import io.mauth.rahulb.fakefood10.util.Util;

public class ProductTextDetailsActivity extends AppCompatActivity {

    private ProductAuditRequest productAuditRequest;
    private Product product;
    private Spinner sizeSpinner;
    private Spinner flavourSpinner;
    private Spinner resellerSpinner;
    private RadioGroup onlineOfline;
    private EditText lotNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details_text);
        init(savedInstanceState);

        processResellerRequest();
        initFlavourSpinner();
        initSizeSpinner();
        initLotNumber();
        initOfflineOnline();

    }


    private List<String> initFlavourData(){
        return product.getFlavours();
    }

    private List<String> initSizeData(){
        return product.getSizes();
    }

    private List<String> initResellerData(List<Reseller> resellers){
        List<String> finalResellers = new ArrayList<>();
        for( Reseller reseller : resellers){
            finalResellers.add( reseller.getName()+" ( " + reseller.getEcom() +" ) ");
        }
        return finalResellers;
    }

    private void initFlavourSpinner(){
        flavourSpinner = (Spinner) findViewById(R.id.flavourSpinner);
        FlavourAdapter flavourAdapter = new FlavourAdapter(this,R.layout.item_layout,initFlavourData());
        flavourSpinner.setAdapter( flavourAdapter);
    }

    private void initSizeSpinner(){
        sizeSpinner = (Spinner) findViewById(R.id.sizeSpinner);
        SizeAdapter sizeAdapter = new SizeAdapter(this, R.layout.item_layout,initSizeData());
        sizeSpinner.setAdapter(sizeAdapter);
    }

    private void initLotNumber(){
        lotNumber = (EditText) findViewById(R.id.lotNumber);
    }

    private void initOfflineOnline() {
        onlineOfline = (RadioGroup) findViewById(R.id.onlineOfline);
    }

    private void initPurchsePlace(){
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.online:
                if (checked)
                    productAuditRequest.setPurchasePlaceEnum(ProductAuditRequest.PurchasePlaceEnum.ONLINE);
                    break;
            case R.id.offline:
                if (checked)
                    productAuditRequest.setPurchasePlaceEnum(ProductAuditRequest.PurchasePlaceEnum.OFFLINE);
                    break;
        }
    }

    public void updateProductDetails(View view) {

        Boolean isComplete = Boolean.TRUE;
        String messaage = "All the required options are selected";

        productAuditRequest.setFlavour( flavourSpinner.getSelectedItem().toString());
        productAuditRequest.setSize(sizeSpinner.getSelectedItem().toString());
        productAuditRequest.setLotNumber(lotNumber.getText().toString());
        productAuditRequest.setAndroidId(Constants.getAndroidId(this));
        productAuditRequest.setName(product.getCompanyName());
        productAuditRequest.setProductId(product.getId());
        productAuditRequest.setCompanyId(product.getCompanyId());
        productAuditRequest.setPlaceOfPurchase(resellerSpinner.getSelectedItem().toString());
        productAuditRequest.setPurchasePlaceEnum(ProductAuditRequest.PurchasePlaceEnum.ONLINE);

//        if ( onlineOfline.getCheckedRadioButtonId() == R.id.offline)
//            productAuditRequest.setPurchasePlaceEnum(ProductAuditRequest.PurchasePlaceEnum.OFFLINE);
//        else if ( onlineOfline.getCheckedRadioButtonId() == R.id.online)
//            productAuditRequest.setPurchasePlaceEnum(ProductAuditRequest.PurchasePlaceEnum.ONLINE);
//        else{
//            isComplete = Boolean.FALSE;
//            messaage = "Please select the purchase mode Online or Offline";
//        }

        if ( !isComplete){
            Toast.makeText(this,messaage, Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(this,ProductDetailsGraphicsActivity.class);
        intent.putExtra(Constants.PRODUCT_AUDIT_REQUEST_KEY, productAuditRequest);
        startActivity(intent);

    }

    private void init(Bundle savedInstanceState){

        productAuditRequest = (ProductAuditRequest) getIntent().getSerializableExtra(Constants.PRODUCT_AUDIT_REQUEST_KEY);
        product = (Product) getIntent().getSerializableExtra(Constants.PRODUCT_DETAIL_KEY);

        if ( product == null)
            product = new Product();

        if (productAuditRequest == null)
            productAuditRequest = new ProductAuditRequest();

    }

    private void processResellerRequest(){

        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("API",error.toString());
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }

        };
        final ContextWrapper contextWrapper = this;
        Response.Listener<JSONArray> response=new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {

                    Type token = new TypeToken<List<Reseller>>() {}.getType();
                    List<Reseller> lists = Util.gson.fromJson(response.toString(), token);

                    resellerSpinner = (Spinner) findViewById(R.id.resellerSpinner);
                    ResellerAdapter resellerAdapter = new ResellerAdapter(contextWrapper,R.layout.item_layout,initResellerData(lists));
                    resellerSpinner.setAdapter(resellerAdapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            }
        };

        AuditService auditService = new AuditService(this);

        auditService.getReseller(Constants.getAndroidId(this),
                response,errorListener);
    }
}
