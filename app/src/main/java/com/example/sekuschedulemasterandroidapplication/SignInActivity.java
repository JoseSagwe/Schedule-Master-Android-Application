package com.example.sekuschedulemasterandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sekuschedulemasterandroidapplication.helpers.StringHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {

    Button sign_in_btn;
    EditText et_email, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Hook Edit Text Fields
        et_email = findViewById(R.id.email);
        et_password = findViewById(R.id.password);

        //Hook Button
        sign_in_btn = findViewById(R.id.sign_in_btn);

        //Set Sign In Button On click Listener:
        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser();
            }
        });
    }
    //End of On Create Activity

    public void authenticateUser(){
//Check for Errors
        if ( !validateEmail() || !validatePassword()){
            return;

        } //End Of check For errors

        //Instantiate The request queue
        RequestQueue queue = Volley.newRequestQueue(SignInActivity.this);
        //The URL Posting TO:
        String url = "http://192.168.56.1:8080/Api/v1/user/login";


        //Set Parameters;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", et_email.getText().toString());
        params.put("password", et_password.getText().toString());


        //Set Request Object
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //Get Values From Response Object
                    String first_name = (String) response.get("first_name");
                    String last_name = (String) response.get("last_name");
                    String email = (String) response.get("email");


                    //Set Intent Actions:
                    Intent goToProfile = new Intent(SignInActivity.this, ProfileActivity.class);

                    //Pass Values to Profile Activity
                    goToProfile.putExtra("first_name", first_name);
                    goToProfile.putExtra("last_name", last_name);
                    goToProfile.putExtra("email", email);

                    //Start Activity
                    startActivity(goToProfile);
                    finish();

                }catch (JSONException e){
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                } //End of Try Block.

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println(error.getMessage());
                Toast.makeText(SignInActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
            }
        });  // End of Set Request Object
        // create Queue
        queue.add(jsonObjectRequest);

    }

    public void goToHome(View view){
        Intent intent = new Intent (SignInActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //End of Go to Home Intent Method


    public void goToSignUpAct(View view){
        Intent intent = new Intent (SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    //End of Go to Sign Up Intent Method

    public boolean validateEmail(){
        String email = et_email.getText().toString();

        //check if email is Empty
        if (email.isEmpty()){
            et_email.setError("Email cannot be Empty");
            return false;
        } else if (StringHelper.regexEmailValidationPattern(email)){
            et_email.setError("Please Enter Valid Email");
            return false;
        } else {
            et_email.setError(null);
            return true; //Check If Email Is Empty
        }
    }
    //End of validate Email field

    public boolean validatePassword(){
        String password_p = et_password.getText().toString();

        //check if Password  field is empty
        if (password_p.isEmpty()){
            et_password.setError("Password field cannot be Empty!");
            return false;
       }else {
            et_password.setError(null);
            return true;
        }
    }
    //End of validate Password  field

}
//End of Sign In Activity Class