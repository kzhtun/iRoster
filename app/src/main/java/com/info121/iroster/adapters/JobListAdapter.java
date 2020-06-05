package com.info121.iroster.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.info121.iroster.R;
import com.info121.iroster.models.JobDetail;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.ViewHolder> {
    private Context mContext;
    List<JobDetail> mListJob;
    String mStatus;

    private int lastPosition = -1;

    public JobListAdapter(Context mContext, List<JobDetail> mListJob) {
        this.mContext = mContext;
        this.mListJob = mListJob;

    }

    @NonNull
    @Override
    public JobListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View searchView = inflater.inflate(R.layout.cell_job_item, parent, false);
        return new ViewHolder(searchView);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
      //  setAnimation(viewHolder.itemView, i);

        final int sdk = android.os.Build.VERSION.SDK_INT;

            viewHolder.siteName.setText(mListJob.get(i).getSiteName());
            viewHolder.address.setText(mListJob.get(i).getAddress());

            viewHolder.shift.setText(mListJob.get(i).getShift());
           // viewHolder.shiftDesc.setText(mListJob.get(i).getShiftDesc());

            viewHolder.position.setText(mListJob.get(i).getPost());
         //   viewHolder.positionDesc.setText(mListJob.get(i).getPostDesc());

            viewHolder.date.setText(mListJob.get(i).getDate());
            viewHolder.status.setText(mListJob.get(i).getStatus());

            if(mListJob.get(i).getStatus().toString().equalsIgnoreCase("CONFIRMED")){
                viewHolder.status.setTextColor(ContextCompat.getColor(mContext, R.color.green));
            }else{
                viewHolder.status.setTextColor(ContextCompat.getColor(mContext, R.color.red));
            }





    }

    @Override
    public int getItemCount() {
        return mListJob.size();
    }



    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.select_layout)
        LinearLayout mSelectLayout;

        @BindView(R.id.bottom_layout)
        LinearLayout mBottomLayout;

        @BindView(R.id.site_name)
        TextView siteName;

        @BindView(R.id.address)
        TextView address;

        @BindView(R.id.date)
        TextView date;

        @BindView(R.id.status)
        TextView status;

        @BindView(R.id.shift)
        TextView shift;

//        @BindView(R.id.shift_desc)
//        TextView shiftDesc;

        @BindView(R.id.position)
        TextView position;

//        @BindView(R.id.position_desc)
//        TextView positionDesc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
