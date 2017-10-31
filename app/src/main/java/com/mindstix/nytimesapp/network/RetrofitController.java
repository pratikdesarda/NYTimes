package com.mindstix.nytimesapp.network;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindstix.nytimesapp.common.Commons;
import com.mindstix.nytimesapp.network.api.NYTimesApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by MindstixSoftware on 29/10/17.
 */

public class RetrofitController {

    private static RetrofitController retrofitInstance;
    private JacksonConverterFactory jacksonConverterFactory;
    private final OkHttpClient okHttpClient;

    private RetrofitController() {
        //Make http client
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(Commons.OKHTTP_CLIENT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Commons.OKHTTP_CLIENT_TIMEOUT, TimeUnit.SECONDS)
                .followRedirects(false)
                .addInterceptor(new HeaderInterceptor())
                .build();

        //Make JSON converter
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jacksonConverterFactory = JacksonConverterFactory.create(objectMapper);
    }

    public static RetrofitController getInstance() {
        if(retrofitInstance == null) {
            synchronized (RetrofitController.class) {
                if(retrofitInstance == null) {
                    retrofitInstance = new RetrofitController();
                }
            }
        }
        return retrofitInstance;
    }

    private class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request newRequest = request.newBuilder()
                    .build();
            return chain.proceed(newRequest);
        }
    }

    public NYTimesApi getNYTimesDetails() {
        Retrofit retrofit = getRetrofitBuilder(Commons.NY_TIMES_BASE_URL);
        return retrofit.create(NYTimesApi.class);
    }

    private Retrofit getRetrofitBuilder(String url) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(url)
                .addConverterFactory(jacksonConverterFactory)
                .build();
    }
}
