package io.mauth.rahulb.fakefood10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import io.mauth.rahulb.fakefood10.activity.MainActivity;

public class ProductSubmitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_submit);
        Toast.makeText(this,"Yo some text",Toast.LENGTH_SHORT).show();
    }

    public void goToMain(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
