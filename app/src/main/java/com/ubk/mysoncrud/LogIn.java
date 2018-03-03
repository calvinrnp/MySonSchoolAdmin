package com.ubk.mysoncrud;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ubk.mysoncrud.classes.connection.LogInVariables;
import com.ubk.mysoncrud.classes.helper.SessionManager;

import java.util.HashMap;
import java.util.Map;

public class LogIn extends AppCompatActivity {

    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;

    FrameLayout progressBarHolder;

    private EditText txtUsername, txtPassword;
    private Button btnLogin;

    SessionManager session;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                System.exit(0);
            }
        }, 2000);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        session = new SessionManager(this);
        progressBarHolder = findViewById(R.id.progressBarHolder);
        txtUsername = findViewById(R.id.txt_username);
        txtPassword = findViewById(R.id.txt_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                if (username.isEmpty()||password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fill in your login information", Toast.LENGTH_SHORT).show();
                }else {
                    showProgress();
                    logIn();
                }
                //new progress().execute();
            }
        });
    }

    private void go(){
        Intent intent = new Intent(this, MainMenu.class);
        session.createLoginSession(txtUsername.getText().toString());
        startActivity(intent);
        finish();
    }


        private void logIn() {
            final String UserLogin = txtUsername.getText().toString().trim();
            final String PasswordLogin = txtPassword.getText().toString().trim();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, LogInVariables.LOGIN_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.contains(LogInVariables.LOGIN_SUCCESS)) {
                        hideProgress();
                        go();
                    }else {
                        hideProgress();
                        Toast.makeText(getApplicationContext(), "Invalid Username/Password!", Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //hideProgress();
                    Toast.makeText(getApplicationContext(), "Cannot connect to server, Please try again later", Toast.LENGTH_LONG).show();
                }
            }){
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put(LogInVariables.username, UserLogin);
                    params.put(LogInVariables.password, PasswordLogin);
                    return params;
                }
            };
            Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
        }

    private void showProgress(){
        progressBarHolder.bringToFront();
        btnLogin.setEnabled(false);
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
        btnLogin.setEnabled(true);
    }
}
