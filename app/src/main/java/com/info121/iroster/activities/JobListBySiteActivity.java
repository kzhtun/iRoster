package com.info121.iroster.activities;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.info121.iroster.AbstractActivity;
import com.info121.iroster.R;
import com.info121.iroster.adapters.JobListAdapter;
import com.info121.iroster.adapters.JobListBySiteAdapter;
import com.info121.iroster.adapters.JobListPageAdapter;
import com.info121.iroster.models.JobDetail;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JobListBySiteActivity extends AbstractActivity {
    Context mContext = JobListBySiteActivity.this;

    List<JobDetail> mJobListBySite = new ArrayList<>();
    List<JobDetail> mJobList = new ArrayList<>();

    JobListBySiteAdapter jobListBySiteAdapter;
    JobListAdapter jobListAdapter;

    @BindView(R.id.tablayout)
    TabLayout mTabLayout;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;


    @BindView(R.id.sub_title)
    TextView mSubtile;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.view_switch)
    ImageView mViewSwitch;

    Boolean isGroup = true;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.back)
    public void backOnClick() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list_by_site);

        ButterKnife.bind(this);
        // set toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        final String sector = getIntent().getExtras().getString("DATE");

        //     mSubtile.setText(sector);


        JobListPageAdapter pageAdapter = new JobListPageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isGroup) {
                    isGroup = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mViewSwitch.setImageDrawable(getResources().getDrawable(R.mipmap.ic_group, getApplicationContext().getTheme()));
                    } else {
                        mViewSwitch.setImageDrawable(getResources().getDrawable(R.mipmap.ic_group));
                    }

                    EventBus.getDefault().post("LIST");

                } else {
                    isGroup = true;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mViewSwitch.setImageDrawable(getResources().getDrawable(R.mipmap.ic_list, getApplicationContext().getTheme()));
                    } else {
                        mViewSwitch.setImageDrawable(getResources().getDrawable(R.mipmap.ic_list));
                    }

                    EventBus.getDefault().post("GROUP");
                }
            }
        });


    }



}
