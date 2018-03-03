package com.ubk.mysoncrud.classes.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.ubk.mysoncrud.GroupMember;
import com.ubk.mysoncrud.GroupMemberShow;
import com.ubk.mysoncrud.R;
import com.ubk.mysoncrud.classes.list.GroupMemberList;

import java.util.List;

/**
 * Created by USER on 21/02/2018.
 */

public class GroupMemberAdapter extends RecyclerView.Adapter<GroupMemberAdapter.ViewHolder> {
    private List<GroupMemberList> groupMemberList;
    private Context context;
    private int lastPosition = -1;

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView group;
        private CardView cardView;
        private ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv_group_member);
            group = itemView.findViewById(R.id.tv_group);
        }
    }

    public GroupMemberAdapter(List<GroupMemberList> groupMemberList, Context context) {
        this.groupMemberList = groupMemberList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_group_member_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GroupMemberList groupmember = groupMemberList.get(position);
        holder.group.setText(groupmember.getGroupname());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GroupMemberShow.class);
                intent.putExtra("IdKelompok", groupmember.getGroupid());
                intent.putExtra("Teacher", groupmember.getGroupname());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        if (position>lastPosition){
            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.slide_right_to_left);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return groupMemberList.size();
    }
}
