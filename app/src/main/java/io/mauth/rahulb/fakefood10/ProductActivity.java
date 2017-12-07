package io.mauth.rahulb.fakefood10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import io.mauth.rahulb.fakefood10.activity.ProductTextDetailsActivity;
import io.mauth.rahulb.fakefood10.model.ProductAuditRequest;
import io.mauth.rahulb.fakefood10.util.Constants;

// The Activity which will serve the purpose

public class ProductActivity extends AppCompatActivity {

    private ProductAuditRequest productAuditRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        productAuditRequest = (ProductAuditRequest) getIntent().getSerializableExtra(Constants.PRODUCT_AUDIT_REQUEST_KEY);
    }

    public void submitCheckRequest(View view) {
        Intent intent = new Intent(this, ProductTextDetailsActivity.class);
        intent.putExtra(Constants.PRODUCT_AUDIT_REQUEST_KEY, productAuditRequest);
        startActivity(intent);
    }
}
