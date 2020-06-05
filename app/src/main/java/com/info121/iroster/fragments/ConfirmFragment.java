package com.info121.iroster.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.info121.iroster.App;
import com.info121.iroster.R;
import com.info121.iroster.adapters.ConfirmAdapter;
import com.info121.iroster.adapters.ShortageAdapter;
import com.info121.iroster.models.ConfirmJobDetail;
import com.info121.iroster.models.JobDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmFragment extends Fragment {
    List<ConfirmJobDetail> mConfirmJobList = new ArrayList<>();

    ConfirmAdapter confirmAdapter;

    Context mContext = getActivity();
    String mCurrentTab = "";

    @BindView(R.id.no_data)
    TextView mNoData;


    @BindView(R.id.rv_jobs)
    RecyclerView mRecyclerView;

    @BindView(R.id.pullToRefresh)
    SwipeRefreshLayout mSwipeLayout;

    public ConfirmFragment() {
    }

    public static ConfirmFragment newInstance(String param1) {
        ConfirmFragment fragment = new ConfirmFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);

        fragment.mCurrentTab = param1;

        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm, container, false);

        ButterKnife.bind(this, view);

        //TODO: dummy data
        mConfirmJobList = new ArrayList<>();

        mConfirmJobList.add(new ConfirmJobDetail("", "CONTRACT #02311", "THE NORTH STAR, TNR", "", "SHIFT 1 (08:00~20:00)", "SSO", "26-May-2020", "9:30 AM", "CONFIRMED", "PETER, Exective", "Jon Jordan", "#2120"));
        mConfirmJobList.add(new ConfirmJobDetail("", "CONTRACT #19292", "WATERWAY POINT, WWP", "", "SHIFT 1 (08:00~20:00)", "SSO", "26-May-2020", "9:35 AM", "CONFIRMED", "PETER, Exective", "Aswan", "#2230"));
        mConfirmJobList.add(new ConfirmJobDetail("", "CONTRACT #43322", "PUNGGOL VIEW, PGV", "", "SHIFT 1 (08:00~20:00)", "SSO", "26-May-2020", "10:00 AM", "CONFIRMED", "PETER, Exective", "Ethan", "#3210"));


        if(App.isSiteDetail)
        {
            List<ConfirmJobDetail> mSelectedJobList = new ArrayList<>();

            for (ConfirmJobDetail jobDetail : mConfirmJobList){
                if(jobDetail.getSiteName() == App.currentSiteInfo.getSiteName())
                    mSelectedJobList.add(jobDetail);
            }

            mConfirmJobList = mSelectedJobList;
        }


        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        confirmAdapter = new ConfirmAdapter(mContext, mConfirmJobList);
        mRecyclerView.setAdapter(confirmAdapter);

        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeLayout.setRefreshing(false);
            }
        });

        return view;
    }
}
