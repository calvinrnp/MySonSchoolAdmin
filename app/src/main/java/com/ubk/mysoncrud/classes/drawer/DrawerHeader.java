package com.ubk.mysoncrud.classes.drawer;


import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
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

@NonReusable
@Layout(R.layout.layout_drawer_header)
public class DrawerHeader {

    private SessionManager session;
    private Context mContext;

    public DrawerHeader(Context mContext) {
        this.mContext = mContext;
    }

    @View(R.id.profile_image)
    private CircularImageView profileImage;

    @View(R.id.txt_name)
    private TextView txtName;

    @View(R.id.txt_status)
    private TextView txtStatus;


}