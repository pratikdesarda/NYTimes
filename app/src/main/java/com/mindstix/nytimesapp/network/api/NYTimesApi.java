package com.mindstix.nytimesapp.network.api;

import com.mindstix.nytimesapp.common.Commons;
import com.mindstix.nytimesapp.network.model.articleSearchDetails.ArticleSearch;
import com.mindstix.nytimesapp.network.model.bestSellerOverviewDetails.BestSellerListOverview;
import com.mindstix.nytimesapp.network.model.historyDetails.History;
import com.mindstix.nytimesapp.network.model.topStoriesDetails.TopStories;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface NYTimesApi {

    @GET(Commons.GET_ALL_TOP_STORIES_URL)
    Call<TopStories> getAllTopStoriesDetails(@Query(Commons.NY_TIMES_API_KEY) String apiKeyVal);

    @GET(Commons.GET_BEST_SELLER_LIST_OVERVIEW_URL)
    Call<BestSellerListOverview> getBestSellerListOverview(@Query(Commons.NY_TIMES_API_KEY) String apiKeyVal);

    @GET(Commons.GET_HISTORY_URL)
    Call<History> getHistory(@QueryMap HashMap<String, String> queryMap);

    @GET(Commons.GET_ARTICLE_SEARCH_URL)
    Call<ArticleSearch> getArticleSearchByKeyword(@QueryMap HashMap<String, String> queryMap);

    @GET(Commons.GET_ARTICLE_SEARCH_URL)
    Call<ArticleSearch> getArticleSearchByKeywordAndCategories(@QueryMap HashMap<String, String> queryMap);
}
