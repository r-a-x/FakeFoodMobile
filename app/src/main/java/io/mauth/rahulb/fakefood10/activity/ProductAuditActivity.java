package io.mauth.rahulb.fakefood10.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import io.mauth.rahulb.fakefood10.R;
import io.mauth.rahulb.fakefood10.core.AuditService;
import io.mauth.rahulb.fakefood10.model.ProductAuditRequest;
import io.mauth.rahulb.fakefood10.util.Constants;

public class ProductAuditActivity extends AppCompatActivity {

    private ProductAuditRequest productAuditRequest;
    private TextView productNameTextView;
    private TextView companyTextView;
    private TextView flavourTextView;
    private TextView sizeTextView;
    private TextView typeTextView;
    private TextView descriptionTextView;
    private Button auditButton;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product2);
        productAuditRequest = (ProductAuditRequest) getIntent().getSerializableExtra(Constants.PRODUCT_AUDIT_REQUEST_KEY);
        AuditService auditService = new AuditService(this);
        init(productAuditRequest);
    }

    private void init(ProductAuditRequest product){

        productNameTextView = (TextView) findViewById(R.id.productNameTextView);
        productNameTextView.setText(product.getName());

        companyTextView = (TextView) findViewById(R.id.companyTextView);
        companyTextView.setText(product.getCompanyId().toString());

        flavourTextView = (TextView) findViewById(R.id.flavourTextView);
        flavourTextView.setVisibility(View.INVISIBLE);

//        flavourTextView.setText(product.ge);

        sizeTextView = (TextView) findViewById(R.id.sizeTextView);
        sizeTextView.setVisibility(View.INVISIBLE);

        typeTextView = (TextView) findViewById(R.id.typeTextView);
        typeTextView.setText(product.getCompany());

        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(product.getDescription());

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(  product.getImage() );

//        auditButton = (Button) (Button) findViewById(R.id.auditButton);
//        auditButton.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(v.getContext(),ProductListingActivity.class);
//                        startActivity(intent);
//                    }
//                }
//        );

    }

    private void startProductTextDetailsActivity(View v){
        Intent intent = new Intent(this, ProductTextDetailsActivity.class);
        intent.putExtra(Constants.PRODUCT_AUDIT_REQUEST_KEY,new ProductAuditRequest());
        intent.putExtra(Constants.PRODUCT_DETAIL_KEY, productAuditRequest);
        startActivity(intent);
    }
}
