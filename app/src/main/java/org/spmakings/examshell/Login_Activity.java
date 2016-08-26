package org.spmakings.examshell;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;
import org.spmakings.examshell.Application.ECellApplication;
import org.spmakings.examshell.Application.ECellConstant;
import org.spmakings.examshell.customview.AvenirLightEditText;
import org.spmakings.examshell.helper.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Login_Activity extends AppCompatActivity {


    private View signIN = null;
    private AvenirLightEditText userEmail, userPassword;
    private final String TAG = "Login_Activity";
    private ProgressBar pBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        //===============================


        signIN = findViewById(R.id.login);

        userEmail = (AvenirLightEditText) findViewById(R.id.input_username);
        userPassword = (AvenirLightEditText) findViewById(R.id.input_password);
        pBar = (ProgressBar) findViewById(R.id.progressBar);


        signIN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Patterns.EMAIL_ADDRESS.matcher(userEmail.getText().toString().trim()).matches()) {
                    if (userPassword.getText().toString().trim().equalsIgnoreCase("")) {
                        userPassword.setError("Enter Password");
                    } else {
                        makeMeSignIn(userEmail.getText().toString().trim(), userPassword.getText().toString().trim());
                    }
                } else {
                    userEmail.setError("Invalid Email ID");
                }
            }
        });


        findViewById(R.id.creat_new).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login_Activity.this, RegistrationActivity.class);
                startActivity(i);
            }
        });


    }


    public void makeMeSignIn(final String email, final String password) {
        pBar.setVisibility(View.VISIBLE);
        signIN.setEnabled(false);
        String URL = "";

        try {
            URL = ECellConstant.DOMAIN_URL_OTHER_NEW + "login?email=" + URLEncoder.encode(email, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Logger.showMessage(TAG, URL);
        StringRequest sr = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response2) {
                        Log.i(TAG, response2);
                        try {
                            JSONObject jObject = new JSONObject(response2);
                            if (jObject.getString("status").equalsIgnoreCase("FAIL")) {
                                if (jObject.getString("message").equalsIgnoreCase("Login failed. Inactive account.")) {
                                    pBar.setVisibility(View.GONE);
                                    signIN.setEnabled(true);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login_Activity.this);
                                    alertDialogBuilder.setTitle("Academic Association");
                                    alertDialogBuilder
                                            .setMessage("An activation mail is already mailed to your account. Activate your account to login")
                                            .setCancelable(false)
                                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });
                                    AlertDialog alertDialog = alertDialogBuilder.create();
                                    alertDialog.show();
                                } else {
                                    displayMessage(jObject.getString("message"));
                                }

                            } else {
                                ECellApplication.getInstance().setUserData(jObject.getString("name").trim(), jObject.getString("email"), "" + jObject.getInt("user_id"));
                                Intent i = new Intent(Login_Activity.this, LandingActivity.class);
                                startActivity(i);
                                finish();
                            }
                        } catch (Exception e) {
                            pBar.setVisibility(View.GONE);
                            signIN.setEnabled(true);
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Sign in Failed...", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Output : ", "Error: " + error.getMessage());
                String json = null;
                NetworkResponse response = error.networkResponse;
                if (response != null && response.data != null) {
                    switch (response.statusCode) {
                        case 400:
                            json = new String(response.data);
//                            json = trimMessage(json, "message");
                            if (json != null) displayMessage(json);
                            break;
                    }
                }
            }
        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("email", "" + email);
//                params.put("password", "" + password);
//                return params;
//            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        ECellApplication.getInstance().addToRequestQueue(sr);
    }


    public void displayMessage(String toastString) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login_Activity.this);
        alertDialogBuilder.setTitle("Error");
        alertDialogBuilder
                .setMessage(Html.fromHtml(toastString))
                .setCancelable(false)
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
//                .setNegativeButton("Discard", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                    }
//                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}
