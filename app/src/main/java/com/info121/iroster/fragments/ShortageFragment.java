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

import com.info121.iroster.App;
import com.info121.iroster.R;
import com.info121.iroster.activities.MainActivity;
import com.info121.iroster.adapters.ShortageAdapter;
import com.info121.iroster.models.JobDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ShortageFragment extends Fragment {
    List<JobDetail> mJobList = new ArrayList<>();

    ShortageAdapter shortageAdapter;

    Context mContext = getActivity();
    String mCurrentTab = "";

    @BindView(R.id.no_data)
    TextView mNoData;

    @BindView(R.id.rv_jobs)
    RecyclerView mRecyclerView;

    @BindView(R.id.pullToRefresh)
    SwipeRefreshLayout mSwipeLayout;

    public ShortageFragment() {
        // Required empty public constructor
    }

    public static ShortageFragment newInstance(String param1) {
        ShortageFragment fragment = new ShortageFragment();
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
        View view = inflater.inflate(R.layout.fragment_shortage, container, false);

        ButterKnife.bind(this, view);

        //TODO: dummy data
        mJobList = new ArrayList<>();

//        mJobList.add(new JobDetail("CONTRACT #02311", "THE NORTH STAR, TNR", "SHIFT 1 (08:00~20:00)", "SSO", "PENDING", "MOHD RAFER BIN JAMAT"));
//        mJobList.add(new JobDetail("CONTRACT #19292", "WATERWAY POINT, WWP", "SHIFT 1 (08:00~20:00)", "SSO", "CONFIRMED", "HASAN  ABDULLA ALSERARI"));
//        mJobList.add(new JobDetail("CONTRACT #43322", "THE WATER BAY, TWB", "SHIFT 1 (08:00~20:00)", "SSO", "PENDING", "THOMASON KEGBY"));
//

        mJobList.add(new JobDetail("", "CONTRACT #02311", "THE NORTH STAR, TNR", "", "SHIFT 1 (08:00~20:00)", "1SS, 4SSO", "DAY SHIFT", "RTT", "SSO", "", "", "PENDING", "MOHD RAFER BIN JAMAT", "-1", "-2"));
        mJobList.add(new JobDetail("", "CONTRACT #19292", "WATERWAY POINT, WWP", "", "SHIFT 1 (08:00~20:00)", "2SS, 1SSO", "DAY SHIFT", "RTT", "SSO", "", "", "CONFIRMED", "HASAN  ABDULLA ALSERARI", "-2", "-1"));
        mJobList.add(new JobDetail("", "CONTRACT #43322", "PUNGGOL VIEW, PGV", "", "SHIFT 1 (08:00~20:00)", "3SS, 1SO", "DAY SHIFT", "RTT", "SSO", "", "", "PENDING", "THOMASON KEGBY", "-2", "-3"));
        mJobList.add(new JobDetail("", "CONTRACT #37272", "REGENT GROVE, RGG", "", "SHIFT 1 (08:00~20:00)", "3SS, 1SO", "DAY SHIFT", "RTT", "SSO", "", "", "PENDING", "ERWIN TAN", "-1", "-2"));


        if(App.isSiteDetail)
        {
           List<JobDetail> mSelectedJobList = new ArrayList<>();

            for (JobDetail jobDetail : mJobList){
                if(jobDetail.getSiteName() == App.currentSiteInfo.getSiteName())
                    mSelectedJobList.add(jobDetail);
            }

            mJobList = mSelectedJobList;
        }

        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        shortageAdapter = new ShortageAdapter(mContext, mJobList);
        mRecyclerView.setAdapter(shortageAdapter);

        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeLayout.setRefreshing(false);
            }
        });

        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (App.currentJobDetail != null) {
                try {
                    mRecyclerView.setHasFixedSize(false);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                    shortageAdapter = new ShortageAdapter(mContext, mJobList);
                    mRecyclerView.setAdapter(shortageAdapter);
                }catch (Exception e){}
            }
        }

    }
}
