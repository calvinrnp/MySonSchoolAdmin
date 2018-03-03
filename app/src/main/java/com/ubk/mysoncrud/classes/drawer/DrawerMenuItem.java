package com.ubk.mysoncrud.classes.drawer;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.ubk.mysoncrud.R;
import com.ubk.mysoncrud.classes.helper.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * Created by Calvin on 9/16/2017.
 */

@Layout(R.layout.layout_drawer_item)
public class  DrawerMenuItem {

    private int mMenuPosition;
    private Context mContext;
    private DrawerCallBack mCallBack;


    @View(R.id.itemNameTxt)
    private TextView itemNameTxt;

    public DrawerMenuItem(Context context, int menuPosition) {
        mContext = context;
        mMenuPosition = menuPosition;
    }


    public void setDrawerCallBack(DrawerCallBack callBack) {
        mCallBack = callBack;
    }

    public interface DrawerCallBack {
        void onLogoutMenuSelected();
    }
}