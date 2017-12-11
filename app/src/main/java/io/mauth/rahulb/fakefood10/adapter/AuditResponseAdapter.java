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
import io.mauth.rahulb.fakefood10.dto.ProductAuditResponse;
import io.mauth.rahulb.fakefood10.util.Constants;

/**
 * Created by rahulb on 7/12/17.
 */

public class AuditResponseAdapter extends RecyclerView.Adapter<AuditResponseHolder> {

    private List<ProductAuditResponse> auditResponses;
    private ContextWrapper ct;
    private Class aClass;
    private String dataKey;

    public AuditResponseAdapter(List<ProductAuditResponse> auditResponses, ContextWrapper ct, Class aClass, String dataKey) {
        this.auditResponses = auditResponses;
        this.ct = ct;
        this.aClass = aClass;
        this.dataKey = dataKey;
    }

    @Override
    public AuditResponseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ct);
        View view = inflater.inflate(R.layout.recycler_layout ,parent,false);
        return new AuditResponseHolder(view);
    }

    @Override
    public void onBindViewHolder(AuditResponseHolder holder, final int position) {

        holder.recyclerTextView.setText(
                auditResponses.get(position).getData()
        );

        processImageRequest(auditResponses.get(position),holder);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Need to send the product from here to the Next Activity
                Toast.makeText(ct,"This is the position number"+ String.valueOf(position),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ct,aClass);
                intent.putExtra(dataKey,auditResponses.get(position));
                ct.startActivity(intent);

            }
        };

        holder.recyclerTextView.setOnClickListener(onClickListener);
        holder.imageView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return auditResponses.size();
    }

    private void processImageRequest(ProductAuditResponse productAuditResponse, final AuditResponseHolder holder){

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
        auditService.getImages(Constants.getAndroidId(ct),productAuditResponse.getFrontCanisterImageName(),listener,errorListener);
    }
}

class AuditResponseHolder extends RecyclerView.ViewHolder{

    TextView recyclerTextView;
    ImageView imageView;

    public AuditResponseHolder(View itemView) {
        super(itemView);
        recyclerTextView = (TextView) itemView.findViewById(R.id.recyclerTextView);
        imageView = (ImageView) itemView.findViewById(R.id.recyclerImageView);
    }

}