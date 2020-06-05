package com.info121.iroster.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.info121.iroster.R;
import com.info121.iroster.adapters.DashboardAdapter;
import com.info121.iroster.models.JobSummary;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ShiftFragment extends Fragment {
    List<JobSummary> mDayList = new ArrayList<>();
    List<JobSummary> mNightList = new ArrayList<>();

    DashboardAdapter dashboardAdapter;

    Context mContext = getActivity();
    String mCurrentTab = "";

    @BindView(R.id.no_data)
    TextView mNoData;

    @BindView(R.id.rv_jobs)
    RecyclerView mRecyclerView;

    @BindView(R.id.pullToRefresh)
    SwipeRefreshLayout mSwipeLayout;

    @BindView(R.id.message)
    TextView mMessage;

    public ShiftFragment() {
        // Required empty public constructor
    }

    public static ShiftFragment newInstance(String param1) {
        ShiftFragment fragment = new ShiftFragment();
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
        View view = inflater.inflate(R.layout.fragment_shift, container, false);


        ButterKnife.bind(this, view);

        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeLayout.setRefreshing(false);
            }
        });

        //TODO: dummy data
        mDayList = new ArrayList<>();
        mNightList = new ArrayList<>();

        mDayList.add(new JobSummary("DAY", "NORTH", "-2"));
        mDayList.add(new JobSummary("DAY","WEST", "+3"));
        mDayList.add(new JobSummary("DAY","EAST", "0"));
        mDayList.add(new JobSummary("DAY","CENTRAL", "-2"));


        mNightList.add(new JobSummary("NIGHT", "NORTH", "-1"));
        mNightList.add(new JobSummary("NIGHT","WEST", "+1"));
        mNightList.add(new JobSummary("NIGHT","EAST", "0"));
        mNightList.add(new JobSummary("NIGHT","CENTRAL", "-3"));

        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));



        if(mCurrentTab == "0"){
            dashboardAdapter = new DashboardAdapter(mContext, mDayList);
            mMessage.setText("There are 1 shortages for this day.");
        }else{
            dashboardAdapter = new DashboardAdapter(mContext, mNightList);
            mMessage.setText("There are 3 shortages for this day.");
        }

        mRecyclerView.setAdapter(dashboardAdapter);
        mRecyclerView.getAdapter().notifyDataSetChanged();

//        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                getRelatedTabData();
//            }
//        });

        return view;
    }
}
