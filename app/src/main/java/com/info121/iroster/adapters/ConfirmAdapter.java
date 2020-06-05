package com.info121.iroster.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.info121.iroster.R;
import com.info121.iroster.models.Action;
import com.info121.iroster.models.ConfirmJobDetail;
import com.info121.iroster.models.JobDetail;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmAdapter extends RecyclerView.Adapter<ConfirmAdapter.ViewHolder> {
    private Context mContext;
    List<ConfirmJobDetail> mConfirmJobList;

    public ConfirmAdapter(Context mContext, List<ConfirmJobDetail> mConfirmJobList) {
        this.mContext = mContext;
        this.mConfirmJobList = mConfirmJobList;
    }

    @NonNull
    @Override
    public ConfirmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View searchView = inflater.inflate(R.layout.cell_job_confirm, parent, false);
        return new ViewHolder(searchView);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        final int sdk = android.os.Build.VERSION.SDK_INT;

        viewHolder.mCheckBox.setChecked(true);

        viewHolder.contractId.setText(mConfirmJobList.get(i).getId());
        viewHolder.siteName.setText(mConfirmJobList.get(i).getSiteName());
        viewHolder.shift.setText(mConfirmJobList.get(i).getShift());
      //  viewHolder.post.setText(mListJob.get(i).getPost());
        viewHolder.status.setText(mConfirmJobList.get(i).getStatus());
        viewHolder.officer.setText(mConfirmJobList.get(i).getOfficer());


        viewHolder.date.setText(mConfirmJobList.get(i).getDate());
        viewHolder.time.setText(mConfirmJobList.get(i).getTime());
        viewHolder.guard.setText(mConfirmJobList.get(i).getGuard());
        viewHolder.guardId.setText(mConfirmJobList.get(i).getGuardId());


        if(mConfirmJobList.get(i).getStatus().toString().equalsIgnoreCase("CONFIRMED")){
            viewHolder.status.setTextColor(ContextCompat.getColor(mContext, R.color.green));
        }else{
            viewHolder.status.setTextColor(ContextCompat.getColor(mContext, R.color.red));
        }

        final int index = i;

//        viewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Action action = new Action("JOBDETAIL", "JOB DETAIL", viewHolder.siteName.getText().toString(), mConfirmJobList.get(index));
//                EventBus.getDefault().post(action);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mConfirmJobList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.checkbox)
        CheckBox mCheckBox;

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

        @BindView(R.id.guard)
        TextView guard;

        @BindView(R.id.guard_id)
        TextView guardId;

        @BindView(R.id.date)
        TextView date;

        @BindView(R.id.time)
        TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
