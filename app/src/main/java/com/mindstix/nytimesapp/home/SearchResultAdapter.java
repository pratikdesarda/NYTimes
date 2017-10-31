package com.mindstix.nytimesapp.home;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mindstix.nytimesapp.R;
import com.mindstix.nytimesapp.common.Commons;
import com.mindstix.nytimesapp.network.model.articleSearchDetails.Doc;

import java.util.List;

/**
 * Created by MindstixSoftware on 29/10/17.
 */

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private Context context;
    private List<Doc> data;

    public SearchResultAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Doc> list) {
        data = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_article_search_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Doc doc = data.get(position);
        if(Commons.checkNotNull(String.valueOf(doc.getSource()))) {
            holder.txtSource.setText(String.valueOf(doc.getSource()));
        } else {
            holder.txtSource.setText(R.string.source_not_found);
            holder.txtSource.setTypeface(holder.txtSource.getTypeface(), Typeface.ITALIC);
            holder.txtSource.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));
        }
        if(Commons.checkNotNull(String.valueOf(doc.getSnippet()))) {
            holder.txtSnippet.setText(String.valueOf(doc.getSnippet()));
        } else {
            holder.txtSnippet.setText(R.string.snippet_not_found);
            holder.txtSnippet.setTypeface(holder.txtSnippet.getTypeface(), Typeface.ITALIC);
            holder.txtSnippet.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));
        }
        if(Commons.checkNotNull(String.valueOf(doc.getHeadline().getMain()))) {
            holder.txtHeadlines.setText(String.valueOf(doc.getHeadline().getMain()));
        } else {
            holder.txtHeadlines.setText(R.string.headlines_not_found);
            holder.txtHeadlines.setTypeface(holder.txtHeadlines.getTypeface(), Typeface.ITALIC);
            holder.txtHeadlines.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public CardView cardView;
        public TextView txtSource, txtSnippet, txtHeadlines;

        public ViewHolder(View v){
            super(v);
            // Get the widget reference from the custom layout
            cardView = (CardView)v.findViewById(R.id.card_view_article_search);
            txtSource = (TextView)v.findViewById(R.id.tv_article_search_source);
            txtSnippet = (TextView)v.findViewById(R.id.tv_article_search_snippet);
            txtHeadlines = (TextView)v.findViewById(R.id.tv_article_search_headline);
        }
    }
}