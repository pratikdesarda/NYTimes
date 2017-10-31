package com.mindstix.nytimesapp.books;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindstix.nytimesapp.R;
import com.mindstix.nytimesapp.common.Commons;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by MindstixSoftware on 30/10/17.
 */

public class BestSellerAdapter extends RecyclerView.Adapter<BestSellerAdapter.ViewHolder>{
    private Context context;
    private List<com.mindstix.nytimesapp.network.model.bestSellerOverviewDetails.List> data;
    private View.OnClickListener listener;

    public BestSellerAdapter(Context context, View.OnClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setData(List<com.mindstix.nytimesapp.network.model.bestSellerOverviewDetails.List> list) {
        data = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_best_seller_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        com.mindstix.nytimesapp.network.model.bestSellerOverviewDetails.List result = data.get(position);
        if(Commons.checkNotNull(String.valueOf(result.getListImage()))) {
            Picasso.with(context).load(result.getListImage()).into(holder.imgListImage);
        }
        if(Commons.checkNotNull(String.valueOf(result.getDisplayName()))) {
            holder.txtListDisplayName.setText(String.valueOf(result.getDisplayName()));
        } else {
            holder.txtListDisplayName.setText(R.string.display_name_not_found);
            holder.txtListDisplayName.setTypeface(holder.txtListDisplayName.getTypeface(), Typeface.ITALIC);
            holder.txtListDisplayName.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));
        }
        holder.cardView.setTag(R.string.tag_best_seller_type, result.getBooks());
        holder.cardView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        public TextView txtListDisplayName;
        public ImageView imgListImage;
        public ViewHolder(View v){
            super(v);
            // Get the widget reference from the custom layout
            cardView = (CardView)v.findViewById(R.id.card_view_best_seller);
            txtListDisplayName = (TextView)v.findViewById(R.id.tv_best_seller_name);
            imgListImage = (ImageView)v.findViewById(R.id.img_best_seller_thumbnail);
        }
    }

}
