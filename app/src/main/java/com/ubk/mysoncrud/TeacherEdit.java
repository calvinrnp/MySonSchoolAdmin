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
import com.ubk.mysoncrud.classes.list.TeacherEditList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TeacherEdit extends AppCompatActivity {
    AlphaAnimation inAnimation,outAnimation;
    FrameLayout progressBarHolder;
    private EditText name,email,mobile;
    private Button btnEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_teacher_form);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText(R.string.edit_teacher_data);

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
        btnEdit = findViewById(R.id.btn_submit);
        btnEdit.setText(R.string.edit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();
            }
        });

        load();
    }

    private void edit() {
        showProgress();
        Intent intent = getIntent();
        String id = intent.getStringExtra("teacherid");
        String nameteacher = name.getText().toString().trim();
        String emailteacher = email.getText().toString().trim();
        String mobileteacher = mobile.getText().toString().trim();
        String url = "http://mysonschool.web.id/MySonSchool/MySonSchoolStaffPhp/Teacher/EditTeacher.php?id="+id+"&name="+nameteacher+"&email="+emailteacher+"&mobile="+mobileteacher;
        //System.out.println(url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                if (s.contains("Success")){
                    hideProgress();
                    Intent intentt = new Intent(TeacherEdit.this,Teacher.class);
                    startActivity(intentt);
                    finish();
                }else {
                    hideProgress();
                    Toast.makeText(TeacherEdit.this, "An Error has Occured, Please try again later", Toast.LENGTH_LONG).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(TeacherEdit.this);
        requestQueue.add(stringRequest);
    }

    private void load() {
        showProgress();
        Intent intent = getIntent();
        String id = intent.getStringExtra("teacherid");
        String url = "http://mysonschool.web.id/MySonSchool/MySonSchoolStaffPhp/Teacher/SelectTeacherEdit.php?id="+id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(s);
                    JSONArray array = jsonObject.getJSONArray("Teacher");
                    for(int i = 0; i < array.length(); i ++ ){
                        JSONObject o = array.getJSONObject(i);
                        TeacherEditList teacher = new TeacherEditList(
                                o.getString("IdGuru"),
                                o.getString("NamaGuru"),
                                o.getString("EmailGuru"),
                                o.getString("MobileGuru")
                        );
                        name.setText(teacher.getName());
                        email.setText(teacher.getEmail());
                        mobile.setText(teacher.getMobile());
                    }
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
        btnEdit.setEnabled(false);
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
        btnEdit.setEnabled(true);
    }
}
