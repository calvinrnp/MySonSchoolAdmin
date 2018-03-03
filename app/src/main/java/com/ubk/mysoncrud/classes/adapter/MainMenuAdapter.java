package com.ubk.mysoncrud.classes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.ubk.mysoncrud.R;
import com.ubk.mysoncrud.classes.list.MainMenuList;

import java.util.List;

/**
 * Created by USER on 21/02/2018.
 */

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.ViewHolder> {
    private List<MainMenuList> mainMenuList;
    private Context context;
    private int lastPosition = -1;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            image = itemView.findViewById(R.id.iv_menu);
        }
    }

    public MainMenuAdapter(List<MainMenuList> mainMenuList, Context context){
        this.mainMenuList = mainMenuList;
        this.context = context;
    }

    @Override
    public MainMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_main_menu_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainMenuAdapter.ViewHolder holder, int position) {
        MainMenuList mainMenu = mainMenuList.get(position);
        holder.title.setText(mainMenu.getTitle());
        holder.image.setImageResource(mainMenu.getImage());

        if (position>lastPosition){
            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.slide_right_to_left);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return mainMenuList.size();
    }
}
