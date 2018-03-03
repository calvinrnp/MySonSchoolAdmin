package com.ubk.mysoncrud;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ubk.mysoncrud.classes.adapter.GroupMemberAdapter;
import com.ubk.mysoncrud.classes.list.GroupMemberList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * Created by USER on 21/02/2018.
 */

public class GroupMember extends AppCompatActivity {
    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;

    FrameLayout progressBarHolder;

    private Toolbar toolbar;
    private TextView toolbarTitle;
    private RecyclerView rcv;
    private RecyclerView.Adapter adapter;
    private List<GroupMemberList> groupMemberList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_group_member);

        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText(R.string.group_member);
        progressBarHolder = findViewById(R.id.progressBarHolder);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rcv = findViewById(R.id.rcv_group_member);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        loadData();

    }

    private void loadData() {
        showProgress();
        String url = "http://mysonschool.web.id/MySonSchool/MySonSchoolStaffPhp/GroupMember/SelectGroup.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(s);
                    JSONArray array = jsonObject.getJSONArray("Group");
                    for(int i = 0; i < array.length(); i ++ ){
                        JSONObject o = array.getJSONObject(i);
                        GroupMemberList group = new GroupMemberList(
                                o.getString("IdKelompok"),
                                o.getString("NamaKelompok"),
                                o.getString("IdGuru"),
                                o.getString("IdPlp")
                        );
                        groupMemberList.add(group);
                    }
                    adapter = new GroupMemberAdapter(groupMemberList, getApplicationContext());
                    rcv.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                hideProgress();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideProgress();
                Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showProgress(){
        rcv.setEnabled(false);
        inAnimation = new AlphaAnimation(0f, 1f);
        inAnimation.setDuration(200);
        progressBarHolder.setAnimation(inAnimation);
        progressBarHolder.setVisibility(View.VISIBLE);
    }

    private void hideProgress(){
        outAnimation = new AlphaAnimation(1f, 0f);
        outAnimation.setDuration(200);
        progressBarHolder.setAnimation(outAnimation);
        progressBarHolder.setVisibility(View.GONE);
        rcv.setEnabled(true);
    }
}
