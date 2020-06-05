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
import com.info121.iroster.models.JobDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AvailableRemarkAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    List<JobDetail> mListJob;
    JobDetail mJobDetail;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_REMARK = 2;


    public AvailableRemarkAdapter(Context mContext, List<JobDetail> mListJob, JobDetail mJobDetail) {
        this.mContext = mContext;
        this.mListJob = mListJob;
        this.mJobDetail = mJobDetail;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view;

        if (viewType == TYPE_HEADER) {
            view = inflater.inflate(R.layout.available_header, parent, false);
            return new ItemViewHolder(view);
        }

        if (viewType == TYPE_ITEM) {
            view = inflater.inflate(R.layout.cell_available_remark_item, parent, false);
            return new ItemViewHolder(view);
        }

        if (viewType == TYPE_REMARK) {
            view = inflater.inflate(R.layout.cell_remark, parent, false);
            return new RemarkViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        final int sdk = android.os.Build.VERSION.SDK_INT;


        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
            viewHolder.mCheckBox.setChecked(true);

            viewHolder.contractId.setText(mJobDetail.getId());
            viewHolder.siteName.setText(mJobDetail.getSiteName());
            viewHolder.shift.setText(mJobDetail.getShift());
            viewHolder.status.setText(mJobDetail.getStatus());
            viewHolder.officer.setText(mJobDetail.getOfficer());


            if(mJobDetail.getStatus().toString().equalsIgnoreCase("CONFIRMED")){
                viewHolder.status.setTextColor(ContextCompat.getColor(mContext, R.color.green));
            }else{
                viewHolder.status.setTextColor(ContextCompat.getColor(mContext, R.color.red));
            }
        }

        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.siteName.setText(mListJob.get(i).getSiteName());
        }

    }

    @Override
    public int getItemViewType(int position) {

        if(position==0)
            return TYPE_HEADER;
        else if(position == mListJob.size())
            return TYPE_REMARK;
        else
            return TYPE_ITEM;
    }


    @Override
    public int getItemCount() {
        return mListJob.size();
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.checkbox)
        CheckBox mCheckBox;

        @BindView(R.id.select_layout)
        LinearLayout mSelectLayout;

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

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.main_layout)
        LinearLayout mainLayout;

        @BindView(R.id.site_name)
        TextView siteName;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class RemarkViewHolder extends RecyclerView.ViewHolder {


        public RemarkViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
