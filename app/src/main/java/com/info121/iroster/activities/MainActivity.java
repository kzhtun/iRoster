package com.info121.iroster.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.info121.iroster.AbstractActivity;
import com.info121.iroster.App;
import com.info121.iroster.R;
import com.info121.iroster.adapters.DashboardPageAdapter;
import com.info121.iroster.adapters.JobDetailPageAdapter;
import com.info121.iroster.adapters.JobListPageAdapter;
import com.info121.iroster.fragments.AvailableFragment;
import com.info121.iroster.fragments.AvailableRemarkFragment;
import com.info121.iroster.models.Action;
import com.info121.iroster.models.JobDetail;
import com.info121.iroster.models.JobSummary;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.Utils;

public class MainActivity extends AbstractActivity {

    Context mContext = MainActivity.this;

    @BindView(R.id.tablayout)
    TabLayout mTabLayout;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    @BindView(R.id.drawer)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.sub_title)
    TextView mSubTitle;

    @BindView(R.id.date)
    TextView mDateTitle;

    @BindView(R.id.date_layout)
    LinearLayout mDateLayout;

    Calendar myCalendar;

    static TextView mLastLogin, mUserName;
    MenuItem mProfile, mNotification, mCalendar, mDeskboard, mLogout;
    MenuItem mNorth, mEast, mWest, mCentral;

    JobListPageAdapter jobListPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(MainActivity.this, LoginActivity.class));

        ButterKnife.bind(this);

        myCalendar = Calendar.getInstance();

        String dateString = Utils.convertDateToString(Calendar.getInstance().getTime(), "EEE dd MMM yyyy");
        String timeString = Utils.convertDateToString(Calendar.getInstance().getTime(), "hh:mm a");

        mDateTitle.setText(dateString);
        mDateLayout.setVisibility(View.GONE);

        final ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        mDrawerToggle.syncState();


        //mDrawerToggle.setDrawerIndicatorEnabled(false);
        //mDrawerToggle.setHomeAsUpIndicator(R.mipmap.ic_drawer);

        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });


        mNavigationView.setItemIconTintList(null);
        View headerView = mNavigationView.getHeaderView(0);
        mLastLogin = (TextView) headerView.findViewById(R.id.last_login);
        mUserName = (TextView) headerView.findViewById(R.id.user_name);


        mProfile = mNavigationView.getMenu().findItem(R.id.profile);
        mProfile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                return false;
            }
        });

        mNotification = mNavigationView.getMenu().findItem(R.id.notificaiton);
        mNotification.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent);
                return false;
            }
        });

        mLogout = mNavigationView.getMenu().findItem(R.id.logout);
        mLogout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                return false;
            }
        });


//        List<JobSummary> mDayList = new ArrayList<>();
//        mDayList = new ArrayList<>();
//
//        mDayList.add(new JobSummary("NORTH", "-2"));
//        mDayList.add(new JobSummary("WEST", "+3"));
//        mDayList.add(new JobSummary("EAST", "0"));
//        mDayList.add(new JobSummary("CENTRAL", "-2"));

        mDeskboard = mNavigationView.getMenu().findItem(R.id.dashboard);
        mDeskboard.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                mDrawerLayout.closeDrawer(GravityCompat.START);

                Action action = new Action("DASHBOARD", "DASHBOARD", "");
                EventBus.getDefault().post(action);
                return false;
            }
        });

        mNorth = mNavigationView.getMenu().findItem(R.id.north);
        mNorth.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                Action action = new Action("JOBLIST", "REGIONS (DAY)", "NORTH");
                EventBus.getDefault().post(action);
                return false;
            }
        });

        mWest = mNavigationView.getMenu().findItem(R.id.west);
        mWest.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                Action action = new Action("JOBLIST", "REGIONS (DAY)", "WEST");
                EventBus.getDefault().post(action);
                return false;
            }
        });

        mEast = mNavigationView.getMenu().findItem(R.id.east);
        mEast.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                Action action = new Action("JOBLIST", "REGIONS (DAY)", "EAST");
                EventBus.getDefault().post(action);
                return false;
            }
        });

        mCentral = mNavigationView.getMenu().findItem(R.id.central);
        mCentral.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                Action action = new Action("JOBLIST", "REGIONS (DAY)", "CENTRAL");
                EventBus.getDefault().post(action);
                return false;
            }
        });

        Action action = new Action("DASHBOARD", "DASHBOARD", "25 May ~ 31 May");
        onEvent(action);

    }

    private void initializeDashboardTabs() {
        TabLayout.Tab tabitem;
        View v;
        TextView header, badge;

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        try {
            tabitem = mTabLayout.getTabAt(0);
            v = View.inflate(mContext, R.layout.tab_header, null);
            header = v.findViewById(R.id.title);
            badge = v.findViewById(R.id.job_count);
            header.setText("DAY");
            badge.setText("-4");
            tabitem.setCustomView(v);

            tabitem = mTabLayout.getTabAt(1);
            v = View.inflate(mContext, R.layout.tab_header, null);
            header = v.findViewById(R.id.title);
            badge = v.findViewById(R.id.job_count);
            header.setText("NIGHT");
            badge.setText("-3");
            tabitem.setCustomView(v);

        } catch (Exception e) {
            return;
        }

    }

    private void initializeJobListTabs() {
        TabLayout.Tab tabitem;
        View v;
        TextView header, badge;

        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        try {
            tabitem = mTabLayout.getTabAt(0);
            v = View.inflate(mContext, R.layout.tab_header, null);
            header = v.findViewById(R.id.title);
            badge = v.findViewById(R.id.job_count);
            header.setText("SHORTAGE");
            badge.setText(App.currentShortage);
            tabitem.setCustomView(v);

            tabitem = mTabLayout.getTabAt(1);
            v = View.inflate(mContext, R.layout.tab_header, null);
            header = v.findViewById(R.id.title);
            badge = v.findViewById(R.id.job_count);
            header.setText("AVAILABLE");
            badge.setText("10");
            tabitem.setCustomView(v);


            tabitem = mTabLayout.getTabAt(2);
            v = View.inflate(mContext, R.layout.tab_header, null);
            header = v.findViewById(R.id.title);
            badge = v.findViewById(R.id.job_count);
            header.setText("CONFIRM");
            badge.setText(App.currentShortage.replace("-", ""));
            tabitem.setCustomView(v);

        } catch (Exception e) {
            return;
        }

    }

    private void initializeJobDetailTabs() {
        TabLayout.Tab tabitem;
        View v;
        TextView header, badge;

        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        try {

            tabitem = mTabLayout.getTabAt(0);
            v = View.inflate(mContext, R.layout.tab_header, null);
            header = v.findViewById(R.id.title);
            badge = v.findViewById(R.id.job_count);
            header.setText("SITE INFO");
            badge.setText("3");
            tabitem.setCustomView(v);

            tabitem = mTabLayout.getTabAt(1);
            v = View.inflate(mContext, R.layout.tab_header, null);
            header = v.findViewById(R.id.title);
            badge = v.findViewById(R.id.job_count);
            header.setText("SHORTAGE");
            badge.setText("-2");
            tabitem.setCustomView(v);

            tabitem = mTabLayout.getTabAt(2);
            v = View.inflate(mContext, R.layout.tab_header, null);
            header = v.findViewById(R.id.title);
            badge = v.findViewById(R.id.job_count);
            header.setText("AVAILABLE");
            badge.setText("3");
            tabitem.setCustomView(v);

            tabitem = mTabLayout.getTabAt(3);
            v = View.inflate(mContext, R.layout.tab_header, null);
            header = v.findViewById(R.id.title);
            badge = v.findViewById(R.id.job_count);
            header.setText("CONFIRM");
            badge.setText("1");
            tabitem.setCustomView(v);

        } catch (Exception e) {
            return;
        }

    }

    @OnClick(R.id.calendar)
    public void CalendarOnClick() {
        showDateDialog();
    }

    private void showDateDialog() {
        DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd MMM yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                // target.setText(sdf.format(myCalendar.getTime()));
            }
        };


        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, dateListener,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();

        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

//            if(target.getTag().toString().equalsIgnoreCase("TO_DATE")){
//                datePickerDialog.getDatePicker().setMinDate(myCalendar.getTimeInMillis());
//            }


    }


    public static void setLoginInfo() {
        mUserName.setText(App.userName);
        mLastLogin.setText("Last Login : " + App.lastLogin);

    }

    @Subscribe(sticky = false)
    public void onEvent(Action action) {
        if (action.getType() == "DASHBOARD") {
            // set view pager
            DashboardPageAdapter pageAdapter = new DashboardPageAdapter(getSupportFragmentManager());
            mViewPager.setAdapter(pageAdapter);
            mTabLayout.setupWithViewPager(mViewPager);


            App.isSiteDetail = false;

            mTitle.setText(action.getTitle());
            mSubTitle.setText(action.getSubtitle());
            mDateTitle.setText(App.currentDate);
            mDateLayout.setVisibility(View.GONE);

            initializeDashboardTabs();
        }


        if (action.getType() == "JOBLIST") {
            jobListPageAdapter = new JobListPageAdapter(getSupportFragmentManager());
            mViewPager.setAdapter(jobListPageAdapter);
            mTabLayout.setupWithViewPager(mViewPager);

            mTitle.setText(action.getTitle());
            mSubTitle.setText(action.getSubtitle());
            mDateTitle.setText(App.currentDate);
            mDateLayout.setVisibility(View.VISIBLE);

            App.currentCluster = action.getSubtitle();
            App.currentJobDetail = action.getJobDetail();

            App.isSiteDetail = false;

            initializeJobListTabs();
        }

        if (action.getType() == "JOBDETAIL") {

            JobDetailPageAdapter pageAdapter = new JobDetailPageAdapter(getSupportFragmentManager());
            mViewPager.setAdapter(pageAdapter);
            mTabLayout.setupWithViewPager(mViewPager);

            mTabLayout.setTabIndicatorFullWidth(false);

            mTitle.setText(action.getTitle());
            mSubTitle.setText(action.getSubtitle());
            mDateTitle.setText(App.currentDate);
            mDateLayout.setVisibility(View.VISIBLE);

            initializeJobDetailTabs();

            App.isSiteDetail = true;

            // EventBus.getDefault().post(action.getJobDetail());

            EventBus.getDefault().post("REFRESH_SITE_INFO");

        }

    }


    @Subscribe(sticky = false)
    public void onEvent(JobDetail jobDetail) {
        App.currentJobDetail = jobDetail;

        EventBus.getDefault().post("REFRESH_AVAILABLE_FRAGMENT");

        if (App.isSiteDetail)
            mTabLayout.getTabAt(2).select();
        else
            mTabLayout.getTabAt(1).select();


//        Fragment fragment = (Fragment) jobListPageAdapter.instantiateItem(mViewPager, 1);
//
//
//        mViewPager.getAdapter().notifyDataSetChanged();
//
//        if (fragment != null && fragment instanceof AvailableFragment) {
//            AvailableFragment availableFragment =  ((AvailableFragment) fragment);
//
//             availableFragment.setAvailableData();
//
//        }


    }
}
