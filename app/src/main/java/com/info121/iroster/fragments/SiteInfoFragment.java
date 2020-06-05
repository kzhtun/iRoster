package com.info121.iroster.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.info121.iroster.AbstractFragment;
import com.info121.iroster.App;
import com.info121.iroster.R;
import com.info121.iroster.models.Action;
import com.info121.iroster.models.JobDetail;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SiteInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SiteInfoFragment extends AbstractFragment {

    Context mContext = getActivity();
    String mCurrentTab = "";


    @BindView(R.id.site_name)
    TextView mSiteName;

    @BindView(R.id.contract)
    TextView mContract;

    @BindView(R.id.cluster)
    TextView mClaster;

    @BindView(R.id.requirement)
    TextView mRequirement;

    @BindView(R.id.day)
    TextView mDay;

    @BindView(R.id.others)
    TextView mOthers;

    @BindView(R.id.day_desc)
    TextView mDayDesc;

    @BindView(R.id.others_shortage)
    TextView mOthersShortage;

    @BindView(R.id.day_shortage)
    TextView mDayShortage;


    @BindView(R.id.others_desc)
    TextView mOthersDesc;

    View view;

    static SiteInfoFragment fragment;

    public SiteInfoFragment() {
    }

    public static SiteInfoFragment newInstance(String param1) {
       if(fragment == null){
           fragment =  new SiteInfoFragment();
       }

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
    view  = inflater.inflate(R.layout.fragment_site_info, container, false);

        ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//
//            if(App.currentSiteInfo != null)
//                displayJobDetail(App.currentSiteInfo);
//        }

    }


    @Subscribe(sticky = false)
    public void onEvent(String action) {

        if(action.equalsIgnoreCase("REFRESH_SITE_INFO")){
            displayJobDetail(App.currentSiteInfo);
        }

    }

    private void displayJobDetail(JobDetail jobDetail) {
        mSiteName.setText(jobDetail.getSiteName());
        mContract.setText(jobDetail.getId());
        mClaster.setText(App.currentCluster);
        mRequirement.setText(jobDetail.getPost());
        mDay.setText(jobDetail.getShift());
        mDayDesc.setText(jobDetail.getShiftDesc());
        mOthers.setText(jobDetail.getOthers());
        mOthersDesc.setText(jobDetail.getOthersDesc());
        mDayShortage.setText(jobDetail.getDayShortage());
        mOthersShortage.setText(jobDetail.getOthersShortage());
    }

}
