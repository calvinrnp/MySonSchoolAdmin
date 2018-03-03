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
import com.ubk.mysoncrud.classes.list.TeacherList;

import java.util.List;

/**
 * Created by USER on 22/02/2018.
 */

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.ViewHolder> {
    private List<TeacherList> teacherList;
    private Context context;
    private int lastPosition = -1;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName,tvEmail,tvMobile;
        private CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv_teacher);
            tvName = itemView.findViewById(R.id.tv_name);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvMobile = itemView.findViewById(R.id.tv_mobile);
        }
    }

    public TeacherAdapter(List<TeacherList> teacherList, Context context) {
        this.teacherList = teacherList;
        this.context = context;
    }

    @Override
    public TeacherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_teacher_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TeacherAdapter.ViewHolder holder, int position) {
        final TeacherList teacher = teacherList.get(position);
        holder.tvName.setText(teacher.getTeachername());
        holder.tvEmail.setText(teacher.getTeacheremail());
        holder.tvMobile.setText(teacher.getTeachermobile());


        if (position>lastPosition){
            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.slide_right_to_left);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    public void removeItem(int position) {
        teacherList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(TeacherList item, int position) {
        teacherList.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }


}
