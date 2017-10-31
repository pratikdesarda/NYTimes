package com.mindstix.nytimesapp.books;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mindstix.nytimesapp.MainActivity;
import com.mindstix.nytimesapp.R;
import com.mindstix.nytimesapp.common.Commons;
import com.mindstix.nytimesapp.network.RetrofitController;
import com.mindstix.nytimesapp.network.model.bestSellerOverviewDetails.BestSellerListOverview;
import com.mindstix.nytimesapp.network.model.bestSellerOverviewDetails.Book;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BestSellerListFragment extends Fragment implements View.OnClickListener{
    private static MainActivity mainActivity;
    private Context context;
    private String CLASS_NAME = getClass().getName() + " ";
    private RecyclerView recyclerViewBestSeller;
    private RecyclerView.LayoutManager layoutManager;
    private BestSellerListOverview bestSellers;
    private List<com.mindstix.nytimesapp.network.model.bestSellerOverviewDetails.List> bestSellerList;
    private BestSellerAdapter bestSellerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Commons.LOG_TAG, CLASS_NAME + "In onCreate");
        mainActivity = (MainActivity) getActivity();
        this.context = mainActivity.getApplicationContext();
        getBestSellerListOverview();
    }

    private void getBestSellerListOverview() {
        mainActivity.showProgressDialog(true, getResources().getString(R.string.loading));
        Log.d(Commons.LOG_TAG, CLASS_NAME + "In getBestSellerListOverview");
        Call<BestSellerListOverview> call = RetrofitController.getInstance().getNYTimesDetails().getBestSellerListOverview(Commons.NY_TIMES_API_KEY_VAL);
        Log.d(Commons.LOG_TAG, CLASS_NAME + "Best Sellers Url: " + call.request().url());
        call.enqueue(new Callback<BestSellerListOverview>() {
            @Override
            public void onResponse(Call<BestSellerListOverview> call, Response<BestSellerListOverview> response) {
                mainActivity.showProgressDialog(false, "");
                if(response.isSuccessful()) {
                    Log.d(Commons.LOG_TAG, CLASS_NAME + "In getBestSellerListOverview: onResponse");
                    bestSellers = response.body();
                    setBestSellerListFragmentData();
                } else {
                    Toast.makeText(mainActivity, "Error occured while getting best sellers list, please try again...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BestSellerListOverview> call, Throwable t) {
                mainActivity.showProgressDialog(false, "");
                Toast.makeText(mainActivity, "Failure while getting best sellers list, please try again...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setBestSellerListFragmentData() {
        bestSellerAdapter = new BestSellerAdapter(mainActivity, BestSellerListFragment.this);
        bestSellerList = bestSellers.getResults().getLists();
        bestSellerAdapter.setData(bestSellerList);
        recyclerViewBestSeller.setAdapter(bestSellerAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View bestSellerListView = inflater.inflate(R.layout.fragment_best_seller_list, container, false);
        resolveViewsFromXml(bestSellerListView);
        if(null != bestSellers) {
            setBestSellerListFragmentData();
        }
        return bestSellerListView;
    }

    private void resolveViewsFromXml(View view) {
        recyclerViewBestSeller = (RecyclerView) view.findViewById(R.id.recycler_view_best_seller_list);
        layoutManager = new GridLayoutManager(context, 2);
        recyclerViewBestSeller.setLayoutManager(layoutManager);
        recyclerViewBestSeller.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerViewBestSeller.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_view_best_seller:
                List<Book> booklist = (List<Book>) v.getTag(R.string.tag_best_seller_type);
                startOverviewFragment(booklist);
                break;
        }
    }

    private void startOverviewFragment(List<Book> booklist) {
        OverviewFragment overviewFragment = new OverviewFragment();
        Bundle overViewDetails = new Bundle();
        overViewDetails.putSerializable(Commons.BOOK_LIST_KEY, (Serializable) booklist);
        overviewFragment.setArguments(overViewDetails);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, overviewFragment);
        fragmentTransaction.addToBackStack(OverviewFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }
}
