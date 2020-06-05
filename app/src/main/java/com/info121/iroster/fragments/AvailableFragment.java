package com.info121.iroster.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.info121.iroster.AbstractFragment;
import com.info121.iroster.App;
import com.info121.iroster.R;
import com.info121.iroster.adapters.AvailableAdapter;
import com.info121.iroster.adapters.AvailableRemarkAdapter;
import com.info121.iroster.models.JobDetail;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AvailableFragment extends AbstractFragment {
    List<JobDetail> mJobList = new ArrayList<>();

    AvailableAdapter availableAdapter;

    Context mContext = getActivity();
    String mCurrentTab = "";

    @BindView(R.id.no_data)
    TextView mNoData;

    @BindView(R.id.rv_jobs)
    RecyclerView mRecyclerView;

    @BindView(R.id.pullToRefresh)
    SwipeRefreshLayout mSwipeLayout;

    public AvailableFragment() {
        // Required empty public constructor
    }

    public static AvailableFragment newInstance(String param1) {
        AvailableFragment fragment = new AvailableFragment();
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

        mJobList.add(new JobDetail("CONTRACT #43322", "HEADER", "SHIFT 1 (08:00~20:00)", "SSO", "PENDING", "OFFICER: MOHD RAFER BIN JAMAT"));

        mJobList.add(new JobDetail("CONTRACT #02311", "THE NORTH STAR, TNR", "SHIFT 1 (08:00~20:00)", "SSO", "PENDING", "OFFICER: MOHD RAFER BIN JAMAT"));
        mJobList.add(new JobDetail("CONTRACT #19292", "WATERWAY POINT, WWP", "SHIFT 1 (08:00~20:00)", "SSO", "PENDING", "OFFICER: MOHD RAFER BIN JAMAT"));
        mJobList.add(new JobDetail("CONTRACT #43322", "PUNGGOL VIEW, PGV", "SHIFT 1 (08:00~20:00)", "SSO", "CONFIRM", "OFFICER: MOHD RAFER BIN JAMAT"));
        mJobList.add(new JobDetail("CONTRACT #37272", "REGENT GROVE, RGG", "SHIFT 1 (08:00~20:00)", "SSO", "CONFIRM", "OFFICER: MOHD RAFER BIN JAMAT"));

        mJobList.add(new JobDetail("CONTRACT #43322", "REMARK", "SHIFT 1 (08:00~20:00)", "SSO", "PENDING", "OFFICER: MOHD RAFER BIN JAMAT"));


        if(App.isSiteDetail)
        {
            List<JobDetail> mSelectedJobList = new ArrayList<>();

            for (JobDetail jobDetail : mJobList){
                if(jobDetail.getSiteName().equalsIgnoreCase("HEADER"))
                    mSelectedJobList.add(jobDetail);

                if(jobDetail.getSiteName() == App.currentSiteInfo.getSiteName())
                    mSelectedJobList.add(jobDetail);

                if(jobDetail.getSiteName().equalsIgnoreCase("REMARK"))
                    mSelectedJobList.add(jobDetail);
            }

            mJobList = mSelectedJobList;
        }



        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        availableAdapter = new AvailableAdapter(mContext, mJobList, App.currentJobDetail);
        mRecyclerView.setAdapter(availableAdapter);

        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               mSwipeLayout.setRefreshing(false);
            }
        });

        return view;
    }

    @Subscribe(sticky = false)
    public void onEvent(String action) {
        if(action.equalsIgnoreCase("REFRESH_AVAILABLE_FRAGMENT")) {
            availableAdapter = new AvailableAdapter(mContext, mJobList, App.currentJobDetail);
            mRecyclerView.setAdapter(availableAdapter);
            availableAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.e("AvailableRemark", " On Resume");
    }

//    public void setAvailableData() {
//        availableAdapter = new AvailableAdapter(mContext, mJobList, App.currentJobDetail);
//        mRecyclerView.setAdapter(availableAdapter);
//        availableAdapter.notifyDataSetChanged();
//    }
}
