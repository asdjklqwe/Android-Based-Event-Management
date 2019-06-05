package com.example.arnold.hypercebuproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvRegisterHere;
    private EditText etUsername, etPassword;
    private Button btnLogin;
    private CheckBox cbRememberMe;
    private String username, password;
    private SharedPreferences.Editor loginPrefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvRegisterHere = (TextView) findViewById(R.id.tvRegisterHere);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        cbRememberMe = (CheckBox)findViewById(R.id.cbRememberMe);

        SharedPreferences loginPreferences = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        boolean rememberMe = loginPreferences.getBoolean("rememberMe", false);
        if (rememberMe == true) {
            etUsername.setText(loginPreferences.getString("username", ""));
            etPassword.setText(loginPreferences.getString("password", ""));
            cbRememberMe.setChecked(true);
        }

        etUsername.requestFocus();

        tvRegisterHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        btnLogin.setOnClickListener(this);
    }

    private void validateLogin(){
        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if (username.length() == 0 && password.length() == 0) {
            etUsername.requestFocus();
            etUsername.setError("FIELD CANNOT BE EMPTY");
            etPassword.requestFocus();
            etPassword.setError("FIELD CANNOT BE EMPTY");
            etUsername.requestFocus();
        } else if (username.length() == 0) {
            etUsername.requestFocus();
            etUsername.setError("FIELD CANNOT BE EMPTY");
        } else if (password.length() == 0) {
            etPassword.requestFocus();
            etPassword.setError("FIELD CANNOT BE EMPTY");
        } else {
            login();
        }
    }

    private void login() {
        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            //Toast.makeText(LoginActivity.this,response,Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("result");
                            for(int i = 0; i<array.length(); i++){
                                JSONObject o = array.getJSONObject(i);
                                openEvent(o.getString("card_no"),o.getString("Card_Amount"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                        progressDialog.dismiss();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(Config.KEY_USERNAME,username);
                map.put(Config.KEY_PASSWORD,password);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v == btnLogin){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(etUsername.getWindowToken(), 0);

            if (cbRememberMe.isChecked()) {
                loginPrefsEditor.putBoolean("rememberMe", true);
                loginPrefsEditor.putString("username", etUsername.getText().toString());
                loginPrefsEditor.putString("password", etPassword.getText().toString());
                loginPrefsEditor.commit();
            } else {
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }
            validateLogin();
        }
    }

    public void openEvent(String c_no, String c_amount){
        Intent intent = new Intent(LoginActivity.this, EventActivity.class);
        intent.putExtra("card_no", c_no);
        intent.putExtra("Card_Amount", c_amount);
        startActivity(intent);
  }
}