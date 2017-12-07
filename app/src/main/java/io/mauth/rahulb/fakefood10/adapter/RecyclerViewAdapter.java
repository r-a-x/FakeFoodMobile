package io.mauth.rahulb.fakefood10.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.mauth.rahulb.fakefood10.R;
import io.mauth.rahulb.fakefood10.model.ListableProduct;

/**
 * Created by rahulb on 1/11/17.
 */

public class RecyclerViewAdapter<T extends ListableProduct> extends RecyclerView.Adapter<Holder>{

    private List<T> listableProducts;
    private Context ct;
    private Class aClass;
    private String dataKey;


    public RecyclerViewAdapter(Context ct, List<T> listableProducts,Class aClass,String dataKey){
        this.ct=ct;
        this.listableProducts = listableProducts;
        this.aClass = aClass;
        this.dataKey = dataKey;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ct);
        View view = inflater.inflate(R.layout.recycler_layout ,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        holder.recyclerTextView.setText(
                listableProducts.get(position).getData()
        );

        holder.imageView.setImageBitmap(
                listableProducts.get(position).getImage()
        );

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

        holder.recyclerTextView.setOnClickListener(onClickListener);
        holder.imageView.setOnClickListener(onClickListener);

    }

    @Override
    public int getItemCount() {
        return listableProducts.size();
    }

}

class Holder extends RecyclerView.ViewHolder{

    TextView recyclerTextView;
    ImageView imageView;

    public Holder(View itemView) {
        super(itemView);
        recyclerTextView = (TextView) itemView.findViewById(R.id.recyclerTextView);
        imageView = (ImageView) itemView.findViewById(R.id.recyclerImageView);
    }

}