package com.mindstix.nytimesapp.books;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mindstix.nytimesapp.MainActivity;
import com.mindstix.nytimesapp.R;
import com.mindstix.nytimesapp.common.Commons;
import com.mindstix.nytimesapp.network.RetrofitController;
import com.mindstix.nytimesapp.network.model.historyDetails.History;
import com.mindstix.nytimesapp.network.model.historyDetails.RanksHistory;
import com.mindstix.nytimesapp.network.model.historyDetails.Result;
import com.mindstix.nytimesapp.network.model.historyDetails.Review;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragment extends Fragment {
    private static MainActivity mainActivity;
    private String CLASS_NAME = getClass().getName() + " ";
    private History history;
    private String isbnFromBundle;
    private TextView txtTitle, txtAuthor, txtDescription;
    private TextView txtCurrentRank, txtLastWeekRank, txtListName, txtWeeksOnList;
    private TextView txtBookReviewLink, txtFirstChapterLink, txtSundayReviewLink, txtArticleChapterLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Commons.LOG_TAG, CLASS_NAME + "In onCreate");
        mainActivity = (MainActivity) getActivity();
        Bundle historyBundle = getArguments();
        if (historyBundle != null) {
            if(historyBundle.containsKey(Commons.PRIMARY_ISBN_NUMBER_10_KEY)) {
                isbnFromBundle = historyBundle.getString(Commons.PRIMARY_ISBN_NUMBER_10_KEY);
            }
        }
        getHistoryData(isbnFromBundle);
    }

    private void getHistoryData(String isbnFromBundle) {
        mainActivity.showProgressDialog(true, getResources().getString(R.string.loading));
        Log.d(Commons.LOG_TAG, CLASS_NAME + "In getHistoryData");
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put(Commons.NY_TIMES_API_KEY, Commons.NY_TIMES_API_KEY_VAL);
        queryMap.put(Commons.PRIMARY_ISBN_NUMBER_10_KEY, isbnFromBundle);
        Call<History> call = RetrofitController.getInstance().getNYTimesDetails().getHistory(queryMap);
        Log.d(Commons.LOG_TAG, CLASS_NAME + "History Url: " + call.request().url());
        call.enqueue(new Callback<History>() {
            @Override
            public void onResponse(Call<History> call, Response<History> response) {
                mainActivity.showProgressDialog(false, "");
                if(response.isSuccessful()) {
                    Log.d(Commons.LOG_TAG, CLASS_NAME + "In getHistoryData: onResponse");
                    history = response.body();
                    setHistoryData();
                } else {
                    Toast.makeText(mainActivity, "Error occured while getting history, please try again...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<History> call, Throwable t) {
                mainActivity.showProgressDialog(false, "");
                Toast.makeText(mainActivity, "Failure while getting history, please try again...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View historyView = inflater.inflate(R.layout.fragment_history, container, false);
        resolveViewsFromXml(historyView);
        if(null != history) {
            setHistoryData();
        }
        return historyView;
    }

    private void setHistoryData() {
        List<Result> result = history.getResults();
        Result singleResult = result.get(0);
        if(Commons.checkNotNull(String.valueOf(singleResult.getTitle()))) {
            txtTitle.setText(String.valueOf(singleResult.getTitle()));
        } else {
            txtTitle.setText(R.string.title_not_found);
            txtTitle.setTypeface(txtTitle.getTypeface(), Typeface.ITALIC);
            txtTitle.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        }
        if(Commons.checkNotNull(String.valueOf(singleResult.getAuthor()))) {
            txtAuthor.setText(String.valueOf(singleResult.getAuthor()));
        } else {
            txtAuthor.setText(R.string.author_not_found);
            txtAuthor.setTypeface(txtAuthor.getTypeface(), Typeface.ITALIC);
            txtAuthor.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        }
        if(Commons.checkNotNull(String.valueOf(singleResult.getDescription()))) {
            txtDescription.setText(String.valueOf(singleResult.getDescription()));
        } else {
            txtDescription.setText(R.string.desc_not_found);
            txtDescription.setTypeface(txtDescription.getTypeface(), Typeface.ITALIC);
            txtDescription.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        }
        List<RanksHistory> ranksHistoryList = singleResult.getRanksHistory();
        RanksHistory singleRankHistory = ranksHistoryList.get(0);
        if(Commons.checkNotNull(String.valueOf(singleRankHistory.getRank()))) {
            txtCurrentRank.setText(String.valueOf(singleRankHistory.getRank()));
        }
        String lastWeekRank = (String) singleRankHistory.getRanksLastWeek();
        if(null == lastWeekRank || "".equalsIgnoreCase(lastWeekRank)) {
            lastWeekRank = "-";
        }
        txtLastWeekRank.setText(String.valueOf(lastWeekRank));
        if(Commons.checkNotNull(String.valueOf(singleRankHistory.getDisplayName()))) {
            txtListName.setText(String.valueOf(singleRankHistory.getDisplayName()));
        } else {
            txtListName.setText(R.string.list_name_not_found);
            txtListName.setTypeface(txtListName.getTypeface(), Typeface.ITALIC);
            txtListName.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        }
        txtWeeksOnList.setText(String.valueOf(singleRankHistory.getWeeksOnList()));
        List<Review> reviewList = singleResult.getReviews();
        Review singleReviewv = reviewList.get(0);
        if(Commons.checkNotNull(String.valueOf(singleReviewv.getBookReviewLink()))) {
            txtBookReviewLink.setText(String.valueOf(singleReviewv.getBookReviewLink()));
            Linkify.addLinks(txtBookReviewLink, Linkify.WEB_URLS);
            txtBookReviewLink.setLinkTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        } else {
            txtBookReviewLink.setText(R.string.book_review_link_not_found);
            txtBookReviewLink.setTypeface(txtBookReviewLink.getTypeface(), Typeface.ITALIC);
            txtBookReviewLink.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        }
        if(Commons.checkNotNull(String.valueOf(singleReviewv.getFirstChapterLink()))) {
            txtFirstChapterLink.setText(String.valueOf(singleReviewv.getFirstChapterLink()));
            Linkify.addLinks(txtFirstChapterLink, Linkify.WEB_URLS);
            txtFirstChapterLink.setLinkTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        } else {
            txtFirstChapterLink.setText(R.string.first_chapter_link_not_found);
            txtFirstChapterLink.setTypeface(txtFirstChapterLink.getTypeface(), Typeface.ITALIC);
            txtFirstChapterLink.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        }
        if(Commons.checkNotNull(String.valueOf(singleReviewv.getSundayReviewLink()))) {
            txtSundayReviewLink.setText(String.valueOf(singleReviewv.getSundayReviewLink()));
            Linkify.addLinks(txtSundayReviewLink, Linkify.WEB_URLS);
            txtSundayReviewLink.setLinkTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        } else {
            txtSundayReviewLink.setText(R.string.sunday_review_link_not_found);
            txtSundayReviewLink.setTypeface(txtSundayReviewLink.getTypeface(), Typeface.ITALIC);
            txtSundayReviewLink.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        }
        if(Commons.checkNotNull(String.valueOf(singleReviewv.getArticleChapterLink()))) {
            txtArticleChapterLink.setText(String.valueOf(singleReviewv.getArticleChapterLink()));
            Linkify.addLinks(txtArticleChapterLink, Linkify.WEB_URLS);
            txtArticleChapterLink.setLinkTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        } else {
            txtArticleChapterLink.setText(R.string.article_chapter_link_not_found);
            txtArticleChapterLink.setTypeface(txtArticleChapterLink.getTypeface(), Typeface.ITALIC);
            txtArticleChapterLink.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        }
    }

    private void resolveViewsFromXml(View v) {
        txtTitle = (TextView)v.findViewById(R.id.tv_history_book_title);
        txtAuthor = (TextView)v.findViewById(R.id.tv_history_author);
        txtDescription = (TextView)v.findViewById(R.id.tv_history_desc);
        txtCurrentRank = (TextView)v.findViewById(R.id.tv_history_current_rank);
        txtLastWeekRank = (TextView)v.findViewById(R.id.tv_history_last_week_rank);
        txtListName = (TextView)v.findViewById(R.id.tv_history_list_name);
        txtWeeksOnList = (TextView)v.findViewById(R.id.tv_history_weeks_on_list);
        txtBookReviewLink = (TextView)v.findViewById(R.id.tv_history_book_review_link);
        txtFirstChapterLink = (TextView)v.findViewById(R.id.tv_history_first_chapter_link);
        txtSundayReviewLink = (TextView)v.findViewById(R.id.tv_history_sunday_review_link);
        txtArticleChapterLink = (TextView)v.findViewById(R.id.tv_history_article_chapter_link);
    }
}
