package com.mindstix.nytimesapp.home;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindstix.nytimesapp.R;
import com.mindstix.nytimesapp.common.Commons;
import com.mindstix.nytimesapp.network.model.topStoriesDetails.Multimedium;
import com.mindstix.nytimesapp.network.model.topStoriesDetails.Result;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.List;

/**
 * Created by MindstixSoftware on 29/10/17.
 */

public class TopStoryAdapter extends RecyclerView.Adapter<TopStoryAdapter.ViewHolder> {

    private Context context;
    private List<Result> data;

    public TopStoryAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Result> list) {
        data = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_top_story_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result result = data.get(position);
        String url = null;
        List<Multimedium> multimedium = result.getMultimedia();
        for(Multimedium singleMultimedium:multimedium) {
            if(singleMultimedium.getFormat().equalsIgnoreCase(context.getString(R.string.standard_thumbnail))) {
                url = singleMultimedium.getUrl();
                break;
            }
        }
        Picasso.with(context).load(url).into(holder.imgThumbnail);
        holder.txtTitle.setText(String.valueOf(result.getTitle()));
        holder.txtDesc.setText(String.valueOf(result.getAbstract()));
        try {
            holder.txtDate.setText(context.getString(R.string.last_updated) + String.valueOf(Commons.getFormattedDateOrTime(result.getUpdatedDate(), Commons.TOP_STORIES_ORIGINAL_DATE_FORMAT, Commons.TOP_STORIES_REQUIRED_DATE_FORMAT)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        public TextView txtTitle, txtDesc, txtDate;
        public ImageView imgThumbnail;
        public ViewHolder(View v){
            super(v);
            // Get the widget reference from the custom layout
            cardView = (CardView)v.findViewById(R.id.card_view_top_story);
            txtTitle = (TextView)v.findViewById(R.id.tv_top_story_title);
            txtDesc = (TextView)v.findViewById(R.id.tv_top_story_desc);
            txtDate = (TextView)v.findViewById(R.id.tv_top_story_date);
            imgThumbnail = (ImageView)v.findViewById(R.id.img_top_story_thumbnail);
        }
    }
}