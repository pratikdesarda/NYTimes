package com.mindstix.nytimesapp.home;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.mindstix.nytimesapp.MainActivity;
import com.mindstix.nytimesapp.R;
import com.mindstix.nytimesapp.common.Commons;
import com.mindstix.nytimesapp.network.RetrofitController;
import com.mindstix.nytimesapp.network.model.articleSearchDetails.ArticleSearch;
import com.mindstix.nytimesapp.network.model.articleSearchDetails.Doc;
import com.mindstix.nytimesapp.network.model.topStoriesDetails.Result;
import com.mindstix.nytimesapp.network.model.topStoriesDetails.TopStories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener, SearchView.OnQueryTextListener, SearchView.OnCloseListener, SearchView.OnFocusChangeListener{

    private static MainActivity mainActivity;
    private Context context;
    private String CLASS_NAME = getClass().getName() + " ";
    private TopStories topStories;
    private RecyclerView recyclerViewTopStories;
    private RecyclerView.LayoutManager layoutManager;
    private TopStoryAdapter topStoryAdapter;
    private List<Result> topStoryList;
    private SearchView searchView;
    private ImageButton imageButton;
    private LinearLayout layoutFilter;
    private ArrayList<String> filterArray;
    private CheckBox cbEducation, cbFood, cbForeign, cbSports, cbScience, cbTechnology;
    private ArticleSearch articleSearch;
    private Timer searchTimer;
    private SearchResultAdapter searchResultAdapter;
    private List<Doc> searchDocList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Commons.LOG_TAG, CLASS_NAME + "In onCreate");
        mainActivity = (MainActivity) getActivity();
        this.context = mainActivity.getApplicationContext();
        filterArray = new ArrayList<>();
        getAllTopStories();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(Commons.LOG_TAG, CLASS_NAME + "In onCreateView");
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);
        resolveViewsFromXml(homeView);
        if(null != topStories) {
            setHomeFragmentData(getString(R.string.top_stories));
        }
        return homeView;
    }

    private void resolveViewsFromXml(View view) {
        recyclerViewTopStories = (RecyclerView) view.findViewById(R.id.recycler_view_top_stories);
        layoutManager = new LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false);
        recyclerViewTopStories.setLayoutManager(layoutManager);
        imageButton = (ImageButton)view.findViewById(R.id.imgbtn_filter);
        imageButton.setOnClickListener(this);
        searchView = (SearchView)view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(this);
        layoutFilter = (LinearLayout)view.findViewById(R.id.filterLayout);
        cbEducation = (CheckBox)view.findViewById(R.id.cbEducation);
        cbFood = (CheckBox)view.findViewById(R.id.cbFood);
        cbForeign = (CheckBox)view.findViewById(R.id.cbForeign);
        cbScience = (CheckBox)view.findViewById(R.id.cbScience);
        cbSports = (CheckBox)view.findViewById(R.id.cbSports);
        cbTechnology = (CheckBox)view.findViewById(R.id.cbTechnology);
    }

    private void getAllTopStories() {
        mainActivity.showProgressDialog(true, getString(R.string.loading));
        Log.d(Commons.LOG_TAG, CLASS_NAME + "In getAllTopStories");
        Call<TopStories> call = RetrofitController.getInstance().getNYTimesDetails().getAllTopStoriesDetails(Commons.NY_TIMES_API_KEY_VAL);
        Log.d(Commons.LOG_TAG, CLASS_NAME + "Top Stories Url: " + call.request().url());
        call.enqueue(new Callback<TopStories>() {
            @Override
            public void onResponse(Call<TopStories> call, Response<TopStories> response) {
                mainActivity.showProgressDialog(false, "");
                if(response.isSuccessful()) {
                    Log.d(Commons.LOG_TAG, CLASS_NAME + "In getAllTopStories: onResponse");
                    topStories = response.body();
                    setHomeFragmentData(getString(R.string.top_stories));
                } else {
                        Toast.makeText(mainActivity, "Error occured while getting all top stories, please try again...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TopStories> call, Throwable t) {
                mainActivity.showProgressDialog(false, "");
                Toast.makeText(mainActivity, "Failure while getting all top stories, please try again...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setHomeFragmentData(String str) {
        if(str.equalsIgnoreCase(getString(R.string.top_stories))) {
            topStoryAdapter = new TopStoryAdapter(mainActivity);
            topStoryList = topStories.getResults();
            topStoryAdapter.setData(topStoryList);
            recyclerViewTopStories.setAdapter(topStoryAdapter);
        } else if(str.equalsIgnoreCase(getString(R.string.search_results))) {
            searchResultAdapter = new SearchResultAdapter(mainActivity);
            searchDocList = articleSearch.getResponse().getDocs();
            searchResultAdapter.setData(searchDocList);
            recyclerViewTopStories.setAdapter(searchResultAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgbtn_filter:
                if(layoutFilter.getVisibility() == View.VISIBLE) {
                    imageButton.setImageDrawable(getResources().getDrawable(R.drawable.filter));
                    layoutFilter.setVisibility(View.GONE);
                    goForSearch(String.valueOf(searchView.getQuery()));
                } else {
                    imageButton.setImageDrawable(getResources().getDrawable(R.drawable.tick));
                    layoutFilter.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    private String getFilteredCategories () {
        filterArray.clear();
        addItemsToFilterArray(cbEducation);
        addItemsToFilterArray(cbFood);
        addItemsToFilterArray(cbForeign);
        addItemsToFilterArray(cbScience);
        addItemsToFilterArray(cbSports);
        addItemsToFilterArray(cbTechnology);
        return prepareSearchCategoryValue(filterArray);
    }

    private String prepareSearchCategoryValue(ArrayList<String> filterArray) {
        StringBuilder builder = new StringBuilder();
        String retString = "";
        if(!filterArray.isEmpty()) {
            for (String str : filterArray) {
                builder.append(str).append(" ");
            }
            retString = (builder.toString()).trim();
        }
        Log.d(Commons.LOG_TAG, CLASS_NAME + "retString = " + retString);
        return retString;
    }

    private void addItemsToFilterArray(CheckBox checkBox) {
        if(checkBox.isChecked()) {
            if(!filterArray.contains(checkBox.getText())) {
                filterArray.add((String) checkBox.getText());
            }
        } else {
            if(filterArray.contains(checkBox.getText())) {
                filterArray.remove(checkBox.getText());
            }
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(final String newText) {
        goForSearch(newText);
        return true;
    }

    private void goForSearch(final String newText) {
        if(null != searchTimer)
            searchTimer.cancel();
        searchTimer = new Timer();
        try {
            searchTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if(null != getActivity()) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String filteredCategories = getFilteredCategories();
                                if(Commons.checkNotNull(newText)) {
                                    if(null != filteredCategories) {
                                        if("".equalsIgnoreCase(filteredCategories)) {
                                            getArticleSearchByKeyword(newText);
                                        } else {
                                            getArticleSearchByKeywordAndCategories(newText, filteredCategories);
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            }, 500);
        } catch (Throwable t) {
            Log.d(Commons.LOG_TAG, CLASS_NAME + "Unable to search");
        }
    }

    private void getArticleSearchByKeyword(String newText) {
        searchView.clearFocus();
        mainActivity.showProgressDialog(true, getString(R.string.loading));
        Log.d(Commons.LOG_TAG, CLASS_NAME + "In getArticleSearchByKeyword");
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put(Commons.NY_TIMES_API_KEY, Commons.NY_TIMES_API_KEY_VAL);
        queryMap.put(Commons.SEARCH_QUERY_KEY, newText);
        Call<ArticleSearch> call = RetrofitController.getInstance().getNYTimesDetails().getArticleSearchByKeyword(queryMap);
        Log.d(Commons.LOG_TAG, CLASS_NAME + "Article Search By Keyword Url: " + call.request().url());
        call.enqueue(new Callback<ArticleSearch>() {
            @Override
            public void onResponse(Call<ArticleSearch> call, Response<ArticleSearch> response) {
                mainActivity.showProgressDialog(false, "");
                if(response.isSuccessful()) {
                    Log.d(Commons.LOG_TAG, CLASS_NAME + "In getArticleSearchByKeyword: onResponse");
                    articleSearch = response.body();
                    setHomeFragmentData(getString(R.string.search_results));

                } else {
                    Toast.makeText(mainActivity, "Error occured while getting article search by keyword, please try again...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArticleSearch> call, Throwable t) {
                mainActivity.showProgressDialog(false, "");
                Toast.makeText(mainActivity, "Failure while getting article search by keyword, please try again...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getArticleSearchByKeywordAndCategories(String newText, String filteredCategories) {
        searchView.clearFocus();
        mainActivity.showProgressDialog(true, getString(R.string.loading));
        Log.d(Commons.LOG_TAG, CLASS_NAME + "In getArticleSearchByKeywordAndCategories");
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put(Commons.NY_TIMES_API_KEY, Commons.NY_TIMES_API_KEY_VAL);
        queryMap.put(Commons.SEARCH_QUERY_KEY, newText);
        queryMap.put(Commons.SEARCH_CATEGORY_KEY, "news_desk:("+filteredCategories+")");
        Call<ArticleSearch> call = RetrofitController.getInstance().getNYTimesDetails().getArticleSearchByKeywordAndCategories(queryMap);
        Log.d(Commons.LOG_TAG, CLASS_NAME + "Article Search By Keyword and Categories Url: " + call.request().url());
        call.enqueue(new Callback<ArticleSearch>() {
            @Override
            public void onResponse(Call<ArticleSearch> call, Response<ArticleSearch> response) {
                mainActivity.showProgressDialog(false, "");
                if(response.isSuccessful()) {
                    Log.d(Commons.LOG_TAG, CLASS_NAME + "In getArticleSearchByKeywordAndCategories: onResponse");
                    articleSearch = response.body();
                    setHomeFragmentData(getString(R.string.search_results));
                } else {
                    Toast.makeText(mainActivity, "Error occured while getting article search by keyword and categories, please try again...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArticleSearch> call, Throwable t) {
                mainActivity.showProgressDialog(false, "");
                Toast.makeText(mainActivity, "Failure while getting article search by keyword and categories, please try again...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onClose() {
        if(null != topStories) {
            setHomeFragmentData(getString(R.string.top_stories));
        } else {
            getAllTopStories();
        }
        return true;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus) {
            InputMethodManager imm =  (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
