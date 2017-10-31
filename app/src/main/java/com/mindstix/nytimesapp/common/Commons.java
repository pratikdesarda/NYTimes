package com.mindstix.nytimesapp.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by MindstixSoftware on 29/10/17.
 */

public class Commons {

    public static final String LOG_TAG = "NYTimesApp";

    // Okhttp Client Timeout
    public final static int OKHTTP_CLIENT_TIMEOUT = 60;

    //Base URL
    public static final String NY_TIMES_BASE_URL = "http://api.nytimes.com/svc/";

    //NY Times API Key
    public static final String NY_TIMES_API_KEY = "api-key";
    public static final String NY_TIMES_API_KEY_VAL = "2f0961ff2b214e6680a54bee0b9a3172";

    //API End Points

    public final static String GET_ALL_TOP_STORIES_URL = "topstories/v2/home.json";
    public final static String GET_BEST_SELLER_LIST_OVERVIEW_URL = "books/v3/lists/overview.json";
    public final static String GET_HISTORY_URL = "books/v3/lists/best-sellers/history.json";
    public final static String GET_ARTICLE_SEARCH_URL = "search/v2/articlesearch.json";


    public final static String TOP_STORIES_ORIGINAL_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    public final static String TOP_STORIES_REQUIRED_DATE_FORMAT = "dd/MM/yyyy hh:mm:ss";

    public final static String BOOK_LIST_KEY = "BookList";
    public final static String PRIMARY_ISBN_NUMBER_10_KEY = "isbn";
    public final static String SEARCH_QUERY_KEY = "q";
    public final static String SEARCH_CATEGORY_KEY = "fq";

    public static String getFormattedDateOrTime(String strOriginalDate, String currentFormat, String requiredFormat) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat(currentFormat, Locale.US);
        Date newDate = format.parse(strOriginalDate);

        format = new SimpleDateFormat(requiredFormat, Locale.US);

        return format.format(newDate);
    }

    public static boolean checkNotNull(String string) {
        return null != string && !"".equalsIgnoreCase(string.trim()) && !"null".equalsIgnoreCase(string.trim());
    }
}
