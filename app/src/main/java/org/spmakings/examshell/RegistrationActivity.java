package org.spmakings.examshell;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;
import org.spmakings.examshell.Application.ECellApplication;
import org.spmakings.examshell.Application.ECellConstant;
import org.spmakings.examshell.customview.AvenirLightEditText;
import org.spmakings.examshell.helper.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RegistrationActivity extends AppCompatActivity {


    private AvenirLightEditText userEmail, userPass, userConfPass, userPhone, university, userName;
    private ProgressBar pBar;
    private View signUp;
    private final String TAG = "SignUpActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        pBar = (ProgressBar) findViewById(R.id.sign_prog);

        userName = (AvenirLightEditText) findViewById(R.id.input_username);
        userEmail = (AvenirLightEditText) findViewById(R.id.input_useremail);
        userPass = (AvenirLightEditText) findViewById(R.id.input_userpass);
        userConfPass = (AvenirLightEditText) findViewById(R.id.input_user_cnf_pass);
        userPhone = (AvenirLightEditText) findViewById(R.id.input_user_phone);
        university = (AvenirLightEditText) findViewById(R.id.input_user_reg);

        signUp = findViewById(R.id.sign_up);

        signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail.getText().toString().trim()).matches()) {
                    if (userPass.getText().toString().trim().equals("")) {
                        userPass.setError("Enter Password");
                    } else {
                        if (userName.getText().toString().trim().equals("")) {
                            userConfPass.setError("Enter Your Name");
                        } else {
                            if (userConfPass.getText().toString().trim().equals("")) {
                                userConfPass.setError("Enter Confirm Password");
                            } else {
                                if (userConfPass.getText().toString().trim().equals(userPass.getText().toString().trim())) {
                                    if (userPhone.getText().toString().trim().equals("")) {
                                        userPhone.setError("Enter Phone Number");
                                    } else {
//                                        if (university.getText().toString().trim().equals("")) {
//                                            university.setError("Enter Registration Number");
//                                        } else {
//                                            //=======Process SignUp
//                                            makeMeSignUp();
//                                        }

                                        makeMeSignUpGet();

                                    }
                                } else {
                                    userConfPass.setError("Password is not matching with confirm password.");
                                }
                            }
                        }
                    }
                } else {
                    userEmail.setError("Invalid Email Id");
                }
            }
        });
    }



    public void displayMessage(String toastString) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegistrationActivity.this);
        alertDialogBuilder.setTitle("Error");
        alertDialogBuilder
                .setMessage(Html.fromHtml(toastString))
                .setCancelable(false)
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    public void makeMeSignUpGet() {
        String URL = "";
        pBar.setVisibility(View.VISIBLE);
        try {
            URL = ECellConstant.DOMAIN_URL_OTHER_NEW + "user_registration?name=" + URLEncoder.encode(userName.getText().toString().trim(), "UTF-8") +
                    "&email=" + URLEncoder.encode(userEmail.getText().toString().trim(), "UTF-8") +
                    "&password=" + URLEncoder.encode(userPass.getText().toString().trim(), "UTF-8") +
                    "&mobile_no=" + URLEncoder.encode(userPhone.getText().toString().trim(), "UTF-8") +
                    "&college_or_university=" + URLEncoder.encode(university.getText().toString().trim(), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Logger.showMessage(TAG, URL);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, URL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pBar.setVisibility(View.GONE);
                        Logger.showMessage(TAG, response.toString());
                        try {
                            if (response.getString("status").equalsIgnoreCase("SUCCESS")) {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegistrationActivity.this);
                                alertDialogBuilder.setTitle("Academic Association");
                                alertDialogBuilder
                                        .setMessage("Account Created Successfully, An activation mail has been send to you soon. Activate your account to login into app.")
                                        .setCancelable(false)
                                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                                onBackPressed();
                                            }
                                        });
                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();
                            } else {
                                displayMessage("Failed to create your account. Cause : " + response.getString("message"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.showMessage(TAG, "Error: " + error.getMessage());
                pBar.setVisibility(View.GONE);
                VolleyLog.d("Output : ", "Error: " + error.getMessage());
                String json = null;
                NetworkResponse response = error.networkResponse;
                if (response != null && response.data != null) {
                    switch (response.statusCode) {
                        case 400:
                            json = new String(response.data);
                            if (json != null) displayMessage(json);
                            break;
                    }
                }
            }
        });
        ECellApplication.getInstance().addToRequestQueue(jsonObjReq);
    }
    

}



