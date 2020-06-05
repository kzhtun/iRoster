package com.info121.iroster.activities;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.info121.iroster.R;
import com.info121.iroster.adapters.NotificationAdapter;
import com.info121.iroster.models.Notification;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationActivity extends AppCompatActivity {
    List<Notification> mNotiList = new ArrayList<>();

    NotificationAdapter notiAdapter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.no_data)
    TextView mNoData;

    @BindView(R.id.rv_jobs)
    RecyclerView mRecyclerView;

    @BindView(R.id.pullToRefresh)
    SwipeRefreshLayout mSwipeLayout;

    Context mContext = NotificationActivity.this;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle Action bar item clicks here. The Action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ButterKnife.bind(this);
        // set toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TODO: dummy data
        mNotiList = new ArrayList<>();


        mNotiList.add(new Notification("THERE IS A SHORTFALL FOR THE WATERBAY, TWB", "18-May-2020, TWB, THE WATERBAY needs security officers 1.\nDAY SHIFT (SHIFT 1 08:00~20:00)", "W"));
        mNotiList.add(new Notification("THERE IS A SHORTFALL FOR THE NORTHSTAR, TNR", "18-May-2020, TNR, THE NORTHSTAR needs security officers 2.\nNIGHT SHIFT (SHIFT 1 18:00~06:00)", "S"));
        mNotiList.add(new Notification("THERE IS A SHORTFALL FOR WATERWAY POINT, WWP", "18-May-2020, WWP, WATERWAY POINT needs security officers 3.\nDAY SHIFT (SHIFT 1 08:00~20:00)", "W"));


        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        notiAdapter = new NotificationAdapter(mContext, mNotiList);
        mRecyclerView.setAdapter(notiAdapter);

        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeLayout.setRefreshing(false);
            }
        });

    }
}
