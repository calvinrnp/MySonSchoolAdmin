package com.ubk.mysoncrud.classes.list;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by USER on 21/02/2018.
 */

public class MainMenuList {
    private String title;
    private int image;

    public MainMenuList(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
