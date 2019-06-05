package com.example.arnold.hypercebuproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvLoginHere;
    private EditText etFirstname, etLastname, etUsername, etPassword, etConfirmPassword;
    private Button btnRegister;

    private String firstname, lastname, username, password, cpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFirstname = (EditText) findViewById(R.id.etFirstname);
        etLastname = (EditText) findViewById(R.id.etLastname);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword) ;
        btnRegister = (Button) findViewById(R.id.btnRegister);
        tvLoginHere = (TextView) findViewById(R.id.tvLoginHere);

        etFirstname.requestFocus();

        tvLoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(this);
    }

    private void validateRegister() {
        firstname = etFirstname.getText().toString().trim();
        lastname = etLastname.getText().toString().trim();
        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        cpassword = etConfirmPassword.getText().toString().trim();

        if (firstname.length() == 0 && lastname.length() == 0 && username.length() == 0 && password.length() == 0 && cpassword.length() == 0) {
            etFirstname.requestFocus();
            etFirstname.setError("FIELD CAN'T BE EMPTY");
            etLastname.requestFocus();
            etLastname.setError("FIELD CAN'T BE EMPTY");
            etUsername.requestFocus();
            etUsername.setError("FIELD CAN'T BE EMPTY");
            etPassword.requestFocus();
            etPassword.setError("FIELD CAN'T BE EMPTY");
            etConfirmPassword.requestFocus();
            etConfirmPassword.setError("FIELD CANNOT BE EMPTY");
            etFirstname.requestFocus();
        } else if (!firstname.matches("[a-zA-Z]+")) {
            etFirstname.requestFocus();
            etFirstname.setError("NAME DOES'NT CONTAIN NUMERIC CHARACTER");
        } else if (!lastname.matches("[a-zA-Z]+")) {
            etLastname.requestFocus();
            etLastname.setError("NAME DOES'NT CONTAIN NUMERIC CHARACTER");
        } else if (!(username.length() > 5)) {
            etUsername.requestFocus();
            etUsername.setError("USERNAME MUST HAVE MORE THAN 5 CHARACTERS");
        } else if (!(password.length() > 5)) {
            etPassword.requestFocus();
            etPassword.setError("PASSWORD MUST HAVE MORE THAN 5 CHARACTERS");
        } else if (!cpassword.equals(password)) {
            etConfirmPassword.requestFocus();
            etConfirmPassword.setError("PASSWORD DON'T MATCH");
        } else {
            register();
        }
        //insert the details on database
    }

    private void register(){
        firstname = etFirstname.getText().toString().trim();
        lastname = etLastname.getText().toString().trim();
        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        cpassword = etConfirmPassword.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing up...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("Successfully Signup")){
                            Toast.makeText(RegisterActivity.this,response,Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(RegisterActivity.this,response,Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Config.KEY_FIRSTNAME, firstname);
                params.put(Config.KEY_LASTNAME, lastname);
                params.put(Config.KEY_USERNAME, username);
                params.put(Config.KEY_PASSWORD, password);
                params.put(Config.KEY_CPASSWORD, cpassword);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    @Override
    public void onClick(View v) {
        validateRegister();
    }

}


