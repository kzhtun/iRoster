package com.info121.iroster.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.info121.iroster.App;
import com.info121.iroster.R;
import com.info121.iroster.activities.JobDetailActivity;
import com.info121.iroster.activities.JobListActivity;
import com.info121.iroster.models.Action;
import com.info121.iroster.models.JobDetail;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShortageAdapter extends RecyclerView.Adapter<ShortageAdapter.ViewHolder> {
    private Context mContext;
    List<JobDetail> mListJob;

    public ShortageAdapter(Context mContext, List<JobDetail> mListJob) {
        this.mContext = mContext;
        this.mListJob = mListJob;
    }

    @NonNull
    @Override
    public ShortageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View searchView = inflater.inflate(R.layout.cell_job_item, parent, false);
        return new ViewHolder(searchView);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        final int sdk = android.os.Build.VERSION.SDK_INT;

        viewHolder.mCheckBox.setChecked(false);

        viewHolder.contractId.setText(mListJob.get(i).getId());
        viewHolder.siteName.setText(mListJob.get(i).getSiteName());
        viewHolder.shift.setText(mListJob.get(i).getShift());
      //  viewHolder.post.setText(mListJob.get(i).getPost());
        viewHolder.status.setText(mListJob.get(i).getStatus());
        viewHolder.officer.setText(mListJob.get(i).getOfficer());


        if(mListJob.get(i).getStatus().toString().equalsIgnoreCase("CONFIRMED")){
            viewHolder.status.setTextColor(ContextCompat.getColor(mContext, R.color.green));
        }else{
            viewHolder.status.setTextColor(ContextCompat.getColor(mContext, R.color.red));
        }

        final int index = i;
        viewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.isSiteDetail = true;
                App.currentSiteInfo = mListJob.get(index);
                Action action = new Action("JOBDETAIL", "SITE (" + App.currentShift + ")" , viewHolder.siteName.getText().toString(), mListJob.get(index));
                EventBus.getDefault().post(action);
            }
        });

        final JobDetail jobDetail = mListJob.get(i);

        viewHolder.mSelectLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.mCheckBox.setChecked(true);
                EventBus.getDefault().post(jobDetail);
            }
        });

        viewHolder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(jobDetail);
            }
        });

        if(i==1)
            viewHolder.mBottomLayout.setVisibility(View.VISIBLE);
        else
            viewHolder.mBottomLayout.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return mListJob.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.checkbox)
        CheckBox mCheckBox;

        @BindView(R.id.select_layout)
        LinearLayout mSelectLayout;

        @BindView(R.id.bottom_layout)
        LinearLayout mBottomLayout;

        @BindView(R.id.main_layout)
        LinearLayout mainLayout;

        @BindView(R.id.contract)
        TextView contractId;

        @BindView(R.id.site_name)
        TextView siteName;

        @BindView(R.id.shift)
        TextView shift;

        @BindView(R.id.position)
        TextView post;

        @BindView(R.id.status)
        TextView status;

        @BindView(R.id.officer)
        TextView officer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
