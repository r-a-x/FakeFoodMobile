package io.mauth.rahulb.fakefood10.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import io.mauth.rahulb.fakefood10.R;

public class SearchActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        handleIntent(getIntent());
    }



    private void handleIntent(Intent intent){
        if( Intent.ACTION_SEARCH.equals(intent.getAction())){
            String querry = intent.getStringExtra(SearchManager.QUERY);
            Log.d("Querry","The Text");
//            Toast.makeText(this,"The querry is ", Toast.LENGTH_SHORT).show();
            findProducts(querry);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }
    private void findProducts(String querry) {
    }
}
