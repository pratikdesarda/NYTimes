package com.mindstix.nytimesapp.books;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mindstix.nytimesapp.MainActivity;
import com.mindstix.nytimesapp.R;
import com.mindstix.nytimesapp.common.Commons;
import com.mindstix.nytimesapp.network.model.bestSellerOverviewDetails.Book;

import java.util.List;

public class OverviewFragment extends Fragment implements View.OnClickListener {
    private static MainActivity mainActivity;
    private String CLASS_NAME = getClass().getName() + " ";
    private List<Book> booklist;
    private RecyclerView recyclerViewOverview;
    private RecyclerView.LayoutManager layoutManager;
    private OverviewAdapter overviewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Commons.LOG_TAG, CLASS_NAME + "In onCreate");
        mainActivity = (MainActivity) getActivity();
        Bundle overViewBundle = getArguments();
        if (overViewBundle != null) {
            if (overViewBundle.containsKey(Commons.BOOK_LIST_KEY)) {
                booklist = (List<Book>) overViewBundle.getSerializable(Commons.BOOK_LIST_KEY);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View overView = inflater.inflate(R.layout.fragment_overview, container, false);
        resolveViewsFromXml(overView);
        if (null != booklist || booklist.isEmpty() || booklist.size() <= 1) {
            setOverviewData();
        }
        return overView;
    }

    private void setOverviewData() {
        overviewAdapter = new OverviewAdapter(mainActivity, OverviewFragment.this);
        overviewAdapter.setData(booklist);
        recyclerViewOverview.setAdapter(overviewAdapter);
    }

    private void resolveViewsFromXml(View view) {
        recyclerViewOverview = (RecyclerView) view.findViewById(R.id.recycler_view_overview);
        layoutManager = new LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false);
        recyclerViewOverview.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_history:
                String primaryIsbn10 = (String) v.getTag(R.string.tag_overview_type);
                startHistoryFragment(primaryIsbn10);
                break;
        }
    }

    private void startHistoryFragment(String primaryIsbn10) {
        HistoryFragment historyFragment = new HistoryFragment();
        Bundle historyDetails = new Bundle();
        historyDetails.putString(Commons.PRIMARY_ISBN_NUMBER_10_KEY, primaryIsbn10);
        historyFragment.setArguments(historyDetails);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, historyFragment);
        fragmentTransaction.addToBackStack(HistoryFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }
}
