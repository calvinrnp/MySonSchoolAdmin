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

public class TeacherInsert extends AppCompatActivity{
    AlphaAnimation inAnimation,outAnimation;
    FrameLayout progressBarHolder;
    private EditText name,email,mobile;
    private Button btnInsert;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_teacher_form);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText(R.string.insert_teacher_data);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        progressBarHolder = findViewById(R.id.progressBarHolder);
        name = findViewById(R.id.txt_teacher_name);
        email = findViewById(R.id.txt_email);
        mobile = findViewById(R.id.txt_mobile);
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
        //String id = intent.getStringExtra("teacherid");
        String nameteacher = name.getText().toString().trim();
        String emailteacher = email.getText().toString().trim();
        String mobileteacher = mobile.getText().toString().trim();
        String url = "http://mysonschool.web.id/MySonSchool/MySonSchoolStaffPhp/Teacher/InsertTeacher.php?name="+nameteacher+"&email="+emailteacher+"&mobile="+mobileteacher;
        //System.out.println(url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                if (s.contains("Success")){
                    hideProgress();
                    Intent intentt = new Intent(TeacherInsert.this,Teacher.class);
                    startActivity(intentt);
                    finish();
                }else {
                    hideProgress();
                    Toast.makeText(TeacherInsert.this, "An Error has Occured, Please try again later", Toast.LENGTH_LONG).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(TeacherInsert.this);
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
