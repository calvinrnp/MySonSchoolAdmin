package com.ubk.mysoncrud.classes.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.ubk.mysoncrud.R;
import com.ubk.mysoncrud.classes.list.ViolationList;

import java.util.List;

public class ViolationAdapter extends RecyclerView.Adapter<ViolationAdapter.ViewHolder> {
    private List<ViolationList> violationList;
    private Context context;
    private int lastPosition = -1;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvId,tvName,tvPoint;
        private CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv_violation);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPoint = itemView.findViewById(R.id.tv_point);
        }
    }

    public ViolationAdapter(List<ViolationList> violationList, Context context) {
        this.violationList = violationList;
        this.context = context;
    }

    @Override
    public ViolationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_violation_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViolationAdapter.ViewHolder holder, int position) {
        final ViolationList violation = violationList.get(position);
        holder.tvId.setText(violation.getViolationid());
        holder.tvName.setText(violation.getViolationname());
        holder.tvPoint.setText(violation.getViolationpoint());


        if (position>lastPosition){
            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.slide_right_to_left);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return violationList.size();
    }

    public void removeItem(int position) {
        violationList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(ViolationList item, int position) {
        violationList.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }


}