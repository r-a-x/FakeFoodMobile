package io.mauth.rahulb.fakefood10.adapter;

import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

import io.mauth.rahulb.fakefood10.R;
import io.mauth.rahulb.fakefood10.core.AuditService;
import io.mauth.rahulb.fakefood10.model.Product;
import io.mauth.rahulb.fakefood10.util.Constants;

/**
 * Created by rahulb on 1/11/17.
 */

public class ProductListingAdapter extends RecyclerView.Adapter<ProductListingHolder>{

    private List<Product> listableProducts;
    private ContextWrapper ct;
    private Class aClass;
    private String dataKey;


    public ProductListingAdapter(ContextWrapper ct, List<Product> listableProducts, Class aClass, String dataKey){
        this.ct=ct;
        this.listableProducts = listableProducts;
        this.aClass = aClass;
        this.dataKey = dataKey;
    }

    @Override
    public ProductListingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ct);
        View view = inflater.inflate(R.layout.recycler_layout ,parent,false);
        return new ProductListingHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductListingHolder productListingHolder, final int position) {

        Product localProduct = listableProducts.get(position);
        productListingHolder.recyclerTextView.setText(
                localProduct.getData()
        );

        processImageRequestForList(localProduct, productListingHolder);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Need to send the product from here to the Next Activity
                Toast.makeText(ct,"This is the position number"+ String.valueOf(position),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ct,aClass);
                intent.putExtra(dataKey,listableProducts.get(position));
                ct.startActivity(intent);

            }
        };

        productListingHolder.recyclerTextView.setOnClickListener(onClickListener);
        productListingHolder.imageView.setOnClickListener(onClickListener);

    }

    private void processImageRequestForList(Product product, final ProductListingHolder holder){

        Response.Listener<Bitmap> listener = new Response.Listener<Bitmap>() { // Bitmap listener
            @Override
            public void onResponse(Bitmap response) {

                holder.imageView.setImageBitmap(
                        response
                );

            }

        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ct,"Oops !! Error downloading the image",Toast.LENGTH_SHORT).show();
            }
        };

        AuditService auditService = new AuditService(ct);
        auditService.getImages(Constants.getAndroidId(ct),product.getImage(),listener,errorListener);
    }
    @Override
    public int getItemCount() {
        return listableProducts.size();
    }

}

class ProductListingHolder extends RecyclerView.ViewHolder{

    TextView recyclerTextView;
    ImageView imageView;

    public ProductListingHolder(View itemView) {
        super(itemView);
        recyclerTextView = (TextView) itemView.findViewById(R.id.recyclerTextView);
        imageView = (ImageView) itemView.findViewById(R.id.recyclerImageView);
    }

}