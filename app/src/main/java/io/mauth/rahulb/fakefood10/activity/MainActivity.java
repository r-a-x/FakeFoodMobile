package io.mauth.rahulb.fakefood10.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

import io.mauth.rahulb.fakefood10.R;
import io.mauth.rahulb.fakefood10.adapter.AuditResponseAdapter;
import io.mauth.rahulb.fakefood10.core.AuditService;
import io.mauth.rahulb.fakefood10.dto.ProductAuditResponse;
import io.mauth.rahulb.fakefood10.model.ProductAuditRequest;
import io.mauth.rahulb.fakefood10.util.Constants;
import io.mauth.rahulb.fakefood10.util.Util;

public class MainActivity extends AppCompatActivity {

    private RecyclerView r1;
    private List<ProductAuditRequest> productAuditRequestList;
    private AuditService auditService ;

    private String androidId ;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        androidId = Util.androidId(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        processAuditResponse();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearch(view);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);

        // Get the SearchView and set the searchable configuration

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.searchView).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;

//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
//        // Assumes current activity is the searchable activity
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
//
//        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return   true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void initProductCheckRequestsView(List<ProductAuditResponse> productAuditRequests){

        r1= (RecyclerView) findViewById(R.id.recycler_view);
        AuditResponseAdapter adapter  = new AuditResponseAdapter(productAuditRequests,this,
                ProductAuditActivity.class, Constants.PRODUCT_AUDIT_REQUEST_KEY);

        r1.setAdapter(adapter);
        r1.setLayoutManager(new LinearLayoutManager(this));

    }

    public void openSearch(View view) {
        Intent intent = new Intent(this,ProductListingActivity.class);
        startActivity(intent);
    }

    public void fuckYeah(View view) {
        Toast.makeText(this,"You The thing got clicked",Toast.LENGTH_LONG).show();
    }

    private void processAuditResponse(){

        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("API",error.toString());
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }

        };

        Response.Listener<JSONArray> response=new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {

                    Type token = new TypeToken<List<ProductAuditResponse>>() {}.getType();
                    List<ProductAuditResponse> lists = Util.gson.fromJson(response.toString(), token);

//                List<ProductAuditRequest> lists = Util.getListFromJSON(new ProductAuditRequest(),response.toString());
                    initProductCheckRequestsView(lists);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            }
        };

        auditService = new AuditService(this);

        auditService.getAudits(androidId,
                response,errorListener);
    }
}
