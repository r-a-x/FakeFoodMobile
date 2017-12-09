package io.mauth.rahulb.fakefood10.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

import io.mauth.rahulb.fakefood10.R;
import io.mauth.rahulb.fakefood10.adapter.ProductListingAdapter;
import io.mauth.rahulb.fakefood10.core.AuditService;
import io.mauth.rahulb.fakefood10.model.Product;
import io.mauth.rahulb.fakefood10.util.Constants;
import io.mauth.rahulb.fakefood10.util.Util;

public class ProductListingActivity extends AppCompatActivity {
    private RecyclerView r1;
    private AuditService auditService;

    private Response.ErrorListener errorListener = new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d("API",error.toString());
            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
        }

    };

    private Response.Listener<JSONArray> response = new Response.Listener<JSONArray>() {

        @Override
        public void onResponse(JSONArray response) {
            try {

                Type token = new TypeToken<List<Product>>() {}.getType();
                List<Product> products = Util.gson.fromJson(response.toString(),token);
                initProductView(products);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);
        auditService = new AuditService(this);
        auditService.getProducts(response,errorListener);
    }

    private void initProductView(List<Product> products){

        r1= (RecyclerView) findViewById(R.id.recycler_view);
        ProductListingAdapter adapter  = new ProductListingAdapter(this,products,ProductTextDetailsActivity.class, Constants.PRODUCT_DETAIL_KEY);
        r1.setAdapter(adapter);
        r1.setLayoutManager(new LinearLayoutManager(this));

    }

}
