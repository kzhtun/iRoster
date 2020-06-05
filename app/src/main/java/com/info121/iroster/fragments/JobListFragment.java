package com.info121.iroster.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.info121.iroster.AbstractFragment;
import com.info121.iroster.R;
import com.info121.iroster.adapters.JobListAdapter;
import com.info121.iroster.adapters.JobListBySiteAdapter;
import com.info121.iroster.models.JobDetail;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class JobListFragment extends AbstractFragment {
    JobListBySiteAdapter jobListBySiteAdapter;
    JobListAdapter jobListAdapter;


    List<JobDetail> mJobListBySite = new ArrayList<>();
    List<JobDetail> mConfirmJobList = new ArrayList<>();
    List<JobDetail> mPendingJobList = new ArrayList<>();

    Context mContext = getActivity();
    String mCurrentTab = "";

    @BindView(R.id.no_data)
    TextView mNoData;

    @BindView(R.id.rv_jobs)
    RecyclerView mRecyclerView;

    @BindView(R.id.pullToRefresh)
    SwipeRefreshLayout mSwipeLayout;

    public JobListFragment() {
        // Required empty public constructor
    }

    public static JobListFragment newInstance(String param1) {
        JobListFragment fragment = new JobListFragment();
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
        View view = inflater.inflate(R.layout.fragment_job_list, container, false);

        ButterKnife.bind(this, view);

        mContext = getContext();

        Log.e("Current Tab : ", mCurrentTab);

        //TODO: dummy data
        mJobListBySite = new ArrayList<>();
        mJobListBySite.add(new JobDetail("CONTRACT #02190", "THE NORTH STAR, TNR", "6 Battery Rd, Singapore 049909", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "", "PND", "MOHD RAFER BIN"));
        mJobListBySite.add(new JobDetail("CONTRACT #04100", "WATERWAY POINT, WWP", "83 Punggol Central, Singapore 828761", "SHIFT 1", "(08:00~20:00)", "SO", "Serinor Office", "", "PND", "MOHD RAFER BIN"));
        mJobListBySite.add(new JobDetail("CONTRACT #30933", "THE WATERBAY, TWB", "45 Edgefield Plains, Singapore 828710", "SHIFT 1", "(08:00~20:00)", "SO", "Serinor Office", "", "PND", "MOHD RAFER BIN"));


        mPendingJobList = new ArrayList<>();
        mConfirmJobList = new ArrayList<>();

        mPendingJobList.add(new JobDetail("CONTRACT #02190", "THE NORTH STAR, TNR", "6 Battery Rd, Singapore 049909", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "25-May-2020, Mon", "PENDING", "MOHD RAFER BIN"));
        mConfirmJobList.add(new JobDetail("CONTRACT #02190", "THE NORTH STAR, TNR", "6 Battery Rd, Singapore 049909", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "26-May-2020, Tue", "CONFIRMED", "MOHD RAFER BIN"));
        mPendingJobList.add(new JobDetail("CONTRACT #02190", "THE NORTH STAR, TNR", "6 Battery Rd, Singapore 049909", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "27-May-2020, Wed", "PENDING", "MOHD RAFER BIN"));
        mConfirmJobList.add(new JobDetail("CONTRACT #02190", "THE NORTH STAR, TNR", "6 Battery Rd, Singapore 049909", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "28-May-2020, Thu", "CONFIRMED", "MOHD RAFER BIN"));
        mPendingJobList.add(new JobDetail("CONTRACT #02190", "THE NORTH STAR, TNR", "6 Battery Rd, Singapore 049909", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "29-May-2020, Fri", "PENDING", "MOHD RAFER BIN"));
        mPendingJobList.add(new JobDetail("CONTRACT #02190", "THE NORTH STAR, TNR", "6 Battery Rd, Singapore 049909", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "30-May-2020, Sat", "PENDING", "MOHD RAFER BIN"));
        mPendingJobList.add(new JobDetail("CONTRACT #02190", "THE NORTH STAR, TNR", "6 Battery Rd, Singapore 049909", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "31-May-2020, Sun", "PENDING", "MOHD RAFER BIN"));

        mPendingJobList.add(new JobDetail("CONTRACT #04100", "WATERWAY POINT, WWP", "83 Punggol Central, Singapore 828761", "SHIFT 1", "(08:00~20:00)", "S0", "Security Office", "25-May-2020, Mon", "PENDING", "MOHD RAFER BIN"));
        mConfirmJobList.add(new JobDetail("CONTRACT #04100", "WATERWAY POINT, WWP", "83 Punggol Central, Singapore 828761", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "26-May-2020, Tue", "CONFIRMED", "MOHD RAFER BIN"));
        mPendingJobList.add(new JobDetail("CONTRACT #04100", "WATERWAY POINT, WWP", "83 Punggol Central, Singapore 828761", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "27-May-2020, Wed", "PENDING", "MOHD RAFER BIN"));
        mConfirmJobList.add(new JobDetail("CONTRACT #04100", "WATERWAY POINT, WWP", "83 Punggol Central, Singapore 828761", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "28-May-2020, Thu", "CONFIRMED", "MOHD RAFER BIN"));
        mPendingJobList.add(new JobDetail("CONTRACT #04100", "WATERWAY POINT, WWP", "83 Punggol Central, Singapore 828761", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "29-May-2020, Fri", "PENDING", "MOHD RAFER BIN"));
        mPendingJobList.add(new JobDetail("CONTRACT #04100", "WATERWAY POINT, WWP", "83 Punggol Central, Singapore 828761", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "30-May-2020, Sat", "PENDING", "MOHD RAFER BIN"));
        mPendingJobList.add(new JobDetail("CONTRACT #04100", "WATERWAY POINT, WWP", "83 Punggol Central, Singapore 828761", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "31-May-2020, Sun", "PENDING", "MOHD RAFER BIN"));

        mPendingJobList.add(new JobDetail("CONTRACT #30933", "THE WATERBAY, TWB", "45 Edgefield Plains, Singapore 828710", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "25-May-2020, Mon", "PENDING", "MOHD RAFER BIN"));
        mConfirmJobList.add(new JobDetail("CONTRACT #30933", "THE WATERBAY, TWB", "45 Edgefield Plains, Singapore 828710", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "26-May-2020, Tue", "CONFIRMED", "MOHD RAFER BIN"));
        mPendingJobList.add(new JobDetail("CONTRACT #30933", "THE WATERBAY, TWB", "45 Edgefield Plains, Singapore 828710", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "27-May-2020, Wed", "PENDING", "MOHD RAFER BIN"));
        mConfirmJobList.add(new JobDetail("CONTRACT #30933", "THE WATERBAY, TWB", "45 Edgefield Plains, Singapore 828710", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "28-May-2020, Thu", "CONFIRMED", "MOHD RAFER BIN"));
        mPendingJobList.add(new JobDetail("CONTRACT #30933", "THE WATERBAY, TWB", "45 Edgefield Plains, Singapore 828710", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "29-May-2020, Fri", "PENDING", "MOHD RAFER BIN"));
        mPendingJobList.add(new JobDetail("CONTRACT #30933", "THE WATERBAY, TWB", "45 Edgefield Plains, Singapore 828710", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "30-May-2020, Sat", "PENDING", "MOHD RAFER BIN"));
        mPendingJobList.add(new JobDetail("CONTRACT #30933", "THE WATERBAY, TWB", "45 Edgefield Plains, Singapore 828710", "SHIFT 1", "(08:00~20:00)", "SO", "Security Office", "31-May-2020, Sun", "PENDING", "MOHD RAFER BIN"));

        String status = "";

        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        if(mCurrentTab.equalsIgnoreCase("CONFIRMED JOB (6)"))
            jobListBySiteAdapter = new JobListBySiteAdapter(mContext, mConfirmJobList, "CFM");
        else
            jobListBySiteAdapter = new JobListBySiteAdapter(mContext, mPendingJobList, "PND");

        mRecyclerView.setAdapter(jobListBySiteAdapter);
        mRecyclerView.getAdapter().notifyDataSetChanged();

        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeLayout.setRefreshing(false);
            }
        });


        return view;
    }


    @Subscribe(sticky = true)
    public void onEvent(String action) {

        if(action.equalsIgnoreCase("LIST")){
            if(mCurrentTab.equalsIgnoreCase("CONFIRMED JOB (6)"))
                jobListAdapter = new JobListAdapter(mContext, mConfirmJobList);
            else
                jobListAdapter = new JobListAdapter(mContext, mPendingJobList);

            mRecyclerView.setAdapter(jobListAdapter);
        }else{
            if(mCurrentTab.equalsIgnoreCase("CONFIRMED JOB (6)"))
                jobListBySiteAdapter = new JobListBySiteAdapter(mContext, mConfirmJobList, "CFM");
            else
                jobListBySiteAdapter = new JobListBySiteAdapter(mContext, mPendingJobList, "PND");

            mRecyclerView.setAdapter(jobListBySiteAdapter);
        }


        mRecyclerView.getAdapter().notifyDataSetChanged();
    }
}
