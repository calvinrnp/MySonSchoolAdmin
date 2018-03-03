package com.ubk.mysoncrud.classes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ubk.mysoncrud.R;
import com.ubk.mysoncrud.classes.list.GroupMemberShowList;

import java.util.List;

/**
 * Created by USER on 22/02/2018.
 */

public class GroupMemberShowAdapter extends RecyclerView.Adapter<GroupMemberShowAdapter.ViewHolder>{
    private List<GroupMemberShowList> groupMemberShowList;
    private Context context;
    private int lastPosition = -1;

    public GroupMemberShowAdapter(List<GroupMemberShowList> groupMemberShowList, Context context) {
        this.groupMemberShowList = groupMemberShowList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivStudent;
        private TextView tvName,tvPlp;

        public ViewHolder(View itemView) {
            super(itemView);
            ivStudent = itemView.findViewById(R.id.iv_student);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPlp = itemView.findViewById(R.id.tv_plp);
        }
    }

    @Override
    public GroupMemberShowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_group_member_show_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GroupMemberShowAdapter.ViewHolder holder, int position) {
        final GroupMemberShowList member = groupMemberShowList.get(position);
        if (!member.getUrl().isEmpty()) {
            Glide.with(context).load(member.getUrl()).into(holder.ivStudent);
        }

        holder.tvName.setText(member.getSc());
        holder.tvPlp.setText(member.getPlp());
    }

    @Override
    public int getItemCount() {
        return groupMemberShowList.size();
    }

}
