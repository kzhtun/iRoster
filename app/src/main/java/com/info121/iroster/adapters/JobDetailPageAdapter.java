package com.info121.iroster.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.info121.iroster.fragments.AvailableFragment;
import com.info121.iroster.fragments.AvailableRemarkFragment;
import com.info121.iroster.fragments.ConfirmFragment;
import com.info121.iroster.fragments.ShortageFragment;
import com.info121.iroster.fragments.SiteInfoFragment;


public class JobDetailPageAdapter extends FragmentStatePagerAdapter {
    SiteInfoFragment siteInfoFragment = SiteInfoFragment.newInstance("SITE INFO");
    AvailableRemarkFragment availableRemarkFragment = AvailableRemarkFragment.newInstance("AVAILABLE");
    ConfirmFragment confirmFragment = ConfirmFragment.newInstance("CONFIRM");

    public JobDetailPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0: return SiteInfoFragment.newInstance("SITE INFO");
            case 1: return ShortageFragment.newInstance("SHORTAGE");
            case 2: return AvailableFragment.newInstance("AVAILABLE");
            case 3: return ConfirmFragment.newInstance("CONFIRM");
        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Log.e("PageTitle", position + "");

        switch (position){
            case 0: return "SITE INFO";
            case 1: return "SHORTAGE";
            case 2: return "AVAILABLE";
            case 3: return "CONFIRM";
            default: return null;
        }


    }
}
