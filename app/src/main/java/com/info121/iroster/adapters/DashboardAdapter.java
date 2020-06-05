package com.info121.iroster.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.info121.iroster.App;
import com.info121.iroster.R;
import com.info121.iroster.activities.JobListActivity;
import com.info121.iroster.models.Action;
import com.info121.iroster.models.JobSummary;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {
    private Context mContext;
    List<JobSummary> mListJob;

    public DashboardAdapter(Context mContext, List<JobSummary> mListJob) {
        this.mContext = mContext;
        this.mListJob = mListJob;
    }

    @NonNull
    @Override
    public DashboardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View searchView = inflater.inflate(R.layout.cell_summary, parent, false);
        return new ViewHolder(searchView);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        final int sdk = android.os.Build.VERSION.SDK_INT;

        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {

            switch (mListJob.get(i).getSector()) {
                case "NORTH":
                    viewHolder.mainLayout.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.rounded_layout_north));
                    break;
                case "WEST":
                    viewHolder.mainLayout.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.rounded_layout_west));
                    break;
                case "EAST":
                    viewHolder.mainLayout.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.rounded_layout_east));
                    break;
                case "CENTRAL":
                    viewHolder.mainLayout.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.rounded_layout_central));
                    break;

            }


        } else {

            switch (mListJob.get(i).getSector()) {
                case "NORTH":
                    viewHolder.mainLayout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.rounded_layout_north));
                    break;
                case "WEST":
                    viewHolder.mainLayout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.rounded_layout_west));
                    break;
                case "EAST":
                    viewHolder.mainLayout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.rounded_layout_east));
                    break;
                case "CENTRAL":
                    viewHolder.mainLayout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.rounded_layout_central));
                    break;

            }
        }

        viewHolder.sector.setText(mListJob.get(i).getSector());
        viewHolder.count.setText(mListJob.get(i).getCount());

        final int index = i;
        viewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Action action = new Action("JOBLIST", "REGIONS (" + mListJob.get(index).getShift() + ")", viewHolder.sector.getText().toString());
                App.currentShift = mListJob.get(index).getShift();
                App.currentShortage = mListJob.get(index).getCount();

                if (mListJob.get(index).getShift().equalsIgnoreCase("DAY"))
                    App.currentShortage = "-4";
                else
                    App.currentShortage = "-3";

                EventBus.getDefault().post(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListJob.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.main_layout)
        LinearLayout mainLayout;

        @BindView(R.id.sector)
        TextView sector;

        @BindView(R.id.job_count)
        TextView count;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
