package io.mauth.rahulb.fakefood10.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import io.mauth.rahulb.fakefood10.R;
import io.mauth.rahulb.fakefood10.dto.ProductAuditResponse;
import io.mauth.rahulb.fakefood10.model.RequestStatus;
import io.mauth.rahulb.fakefood10.util.Constants;

public class ProductAuditActivity extends AppCompatActivity {

    private TextView productNameTextView;
    private TextView companyTextView;
    private ImageView imageView;
    private TextView statusTextView;
    private TextView barCodeTextView;
    private Bitmap image;
    private ProductAuditResponse auditResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audit_status_activity);
        auditResponse = (ProductAuditResponse) getIntent().getSerializableExtra(Constants.PRODUCT_AUDIT_RESPONSE_TEXT_KEY);
        image=getIntent().getParcelableExtra(Constants.PRODUCT_AUDIT_RESPONSE_IMAGE_KEY);
        init();
    }

    private void init(){

        productNameTextView = (TextView) findViewById(R.id.productNameTextView);
        productNameTextView.setText(auditResponse.getName());

        companyTextView = (TextView) findViewById(R.id.companyTextView);
        companyTextView.setText(auditResponse.getCompany());

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(  image );

        statusTextView = (TextView) findViewById(R.id.statusTextView);
        if ( auditResponse.getStatus() == RequestStatus.FAKE)
            statusTextView.setTextColor(Color.RED);
        if (auditResponse.getStatus() == RequestStatus.ORIGINAL)
            statusTextView.setTextColor(Color.GREEN);

        statusTextView.setText(auditResponse.getStatus().toString().trim());

        barCodeTextView = (TextView) findViewById(R.id.barCodeTextView);
        barCodeTextView.setText(auditResponse.getBarCode());

    }
}
