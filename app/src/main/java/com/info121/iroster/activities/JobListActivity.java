package com.info121.iroster.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.info121.iroster.AbstractActivity;
import com.info121.iroster.R;
import com.info121.iroster.adapters.JobListPageAdapter;
import com.info121.iroster.models.Action;
import com.info121.iroster.models.JobDetail;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JobListActivity extends AbstractActivity {
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.sub_title)
    TextView mSubtile;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

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
        setContentView(R.layout.activity_job_list);

        ButterKnife.bind(this);
        // set toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String sector = getIntent().getExtras().getString("SECTOR");

        mSubtile.setText(sector);

        // set view pager
        JobListPageAdapter pageAdapter = new JobListPageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                //App.test = i + "";

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


}
