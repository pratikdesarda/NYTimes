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
import com.mindstix.nytimesapp.network.model.bestSellerOverviewDetails.Book;
import com.mindstix.nytimesapp.network.model.bestSellerOverviewDetails.BuyLink;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by MindstixSoftware on 29/10/17.
 */

public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.ViewHolder> {
    private Context context;
    private List<Book> data;
    private View.OnClickListener listener;

    public OverviewAdapter(Context context, View.OnClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setData(List<Book> list) {
        data = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_overview_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book result = data.get(position);
        if (Commons.checkNotNull(String.valueOf(result.getBookImage()))) {
            Picasso.with(context).load(result.getBookImage()).into(holder.imgOverviewThumbnail);
        }
        if (Commons.checkNotNull(String.valueOf(result.getRank()))) {
            holder.txtRank.setText(String.valueOf(result.getRank()));
        }
        if (Commons.checkNotNull(String.valueOf(result.getTitle()))) {
            holder.txtBookTitle.setText(String.valueOf(result.getTitle()));
        } else {
            holder.txtBookTitle.setText(R.string.title_not_found);
            holder.txtBookTitle.setTypeface(holder.txtBookTitle.getTypeface(), Typeface.ITALIC);
            holder.txtBookTitle.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));
        }
        if (Commons.checkNotNull(String.valueOf(result.getPrice()))) {
            holder.txtPrice.setText(String.valueOf(result.getPrice()));
        }
        if (Commons.checkNotNull(String.valueOf(result.getContributor()))) {
            holder.txtAuthor.setText(String.valueOf(result.getContributor()));
        } else {
            holder.txtAuthor.setText(R.string.author_not_found);
            holder.txtAuthor.setTypeface(holder.txtAuthor.getTypeface(), Typeface.ITALIC);
            holder.txtAuthor.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));
        }
        holder.txtDesc.setText(String.valueOf(result.getDescription()));
        List<BuyLink> buyLinks = result.getBuyLinks();
        for (BuyLink buyLink : buyLinks) {
            if (buyLink.getName().equalsIgnoreCase(context.getString(R.string.amazon))) {
                holder.txtAmazonLink.setText(String.valueOf(buyLink.getUrl()));
            } else if (buyLink.getName().equalsIgnoreCase(context.getString(R.string.local_bookseller))) {
                holder.txtLocalBookSellerLink.setText(String.valueOf(buyLink.getUrl()));
            } else if (buyLink.getName().equalsIgnoreCase(context.getString(R.string.barnes_noble))) {
                holder.txtBarnesAndNobleLink.setText(String.valueOf(buyLink.getUrl()));
            }
        }
        holder.imgCheckHistory.setTag(R.string.tag_overview_type, result.getPrimaryIsbn10());
        holder.imgCheckHistory.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView txtRank, txtBookTitle, txtDesc, txtPrice, txtAuthor, txtAmazonLink, txtLocalBookSellerLink, txtBarnesAndNobleLink;
        public ImageView imgOverviewThumbnail, imgCheckHistory;

        public ViewHolder(View v) {
            super(v);
            // Get the widget reference from the custom layout
            cardView = (CardView) v.findViewById(R.id.card_view_overview);
            txtRank = (TextView) v.findViewById(R.id.tv_rank);
            txtBookTitle = (TextView) v.findViewById(R.id.tv_book_title);
            txtPrice = (TextView) v.findViewById(R.id.tv_price);
            txtAuthor = (TextView) v.findViewById(R.id.tv_author);
            txtDesc = (TextView) v.findViewById(R.id.tv_description);
            txtAmazonLink = (TextView) v.findViewById(R.id.tv_amazon_link);
            txtLocalBookSellerLink = (TextView) v.findViewById(R.id.tv_local_seller_link);
            txtBarnesAndNobleLink = (TextView) v.findViewById(R.id.tv_barnes_and_noble_link);
            imgCheckHistory = (ImageView) v.findViewById(R.id.check_history);
            imgOverviewThumbnail = (ImageView) v.findViewById(R.id.img_overview_thumbnail);
        }
    }
}