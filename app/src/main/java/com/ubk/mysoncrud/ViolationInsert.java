package com.ubk.mysoncrud;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by USER on 27/02/2018.
 */

public class ViolationInsert extends AppCompatActivity {
    AlphaAnimation inAnimation,outAnimation;
    FrameLayout progressBarHolder;
    private EditText name,point;
    private Button btnInsert;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_violation_form);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText(R.string.insert_violation_data);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        progressBarHolder = findViewById(R.id.progressBarHolder);
        name = findViewById(R.id.txt_violation_name);
        point = findViewById(R.id.txt_point);
        btnInsert = findViewById(R.id.btn_submit);
        btnInsert.setText(R.string.insert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }

    private void insert() {
        showProgress();
        //Intent intent = getIntent();
        //String id = intent.getStringExtra("violationid");
        String violationname = name.getText().toString().trim();
        String violationpoint = point.getText().toString().trim();
        String url = "http://mysonschool.web.id/MySonSchool/MySonSchoolStaffPhp/Violation/InsertViolation.php?name="+violationname+"&point="+violationpoint;
        //System.out.println(url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                if (s.contains("Success")){
                    hideProgress();
                    Intent intentt = new Intent(ViolationInsert.this,Violation.class);
                    startActivity(intentt);
                    finish();
                }else {
                    hideProgress();
                    Toast.makeText(ViolationInsert.this, "An Error has Occured, Please try again later", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideProgress();
                Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(ViolationInsert.this);
        requestQueue.add(stringRequest);
    }

    private void showProgress(){
        btnInsert.setEnabled(false);
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
        btnInsert.setEnabled(true);
    }
}

