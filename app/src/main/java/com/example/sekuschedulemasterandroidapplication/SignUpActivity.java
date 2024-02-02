package com.example.sekuschedulemasterandroidapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sekuschedulemasterandroidapplication.helpers.StringHelper;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    EditText first_name, last_name, email, password, confirm;

    Button sign_up_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hook Edit Text Fields:
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm);

        //Hook Sign Up Button:
        sign_up_btn = findViewById(R.id.sign_up_btn);

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processFormFields();
            }
        });

    }

    //End of On Create Activity

    public void goToHome(View view){
        Intent intent = new Intent (SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    public void goToSignInAct(View view){
        Intent intent = new Intent (SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    public void processFormFields(){
        //Check for Errors
        if (!validateFirstName() || !validateLastName() || !validateEmail() || !validatePasswordAndConfirm()){
            return;
        }
        //End of Check for Errors

        //Instantiate The request queue
        RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
        //The URL Posting TO:
        String url = "http://192.168.56.1:8080/Api/v1/user/register";

        //String Request Object
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("SignUpActivity", "Response: " + response);
                if (response.equalsIgnoreCase("success")){
                    first_name.setText(null);
                    last_name.setText(null);
                    email.setText(null);               // Setting the text field empty when user clicks button to stop posting again
                    password.setText(null);
                    confirm.setText(null);
                    Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                }
                //End of Response If block

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("SignUpActivity", "Error during network request", error);
                System.out.println(error.getMessage());
                Toast.makeText(SignUpActivity.this, "Registration Un-successful", Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("first_name", first_name.getText().toString());
                params.put("last_name", last_name.getText().toString());
                params.put("email", email.getText().toString());
                params.put("password", password.getText().toString());
                return params;
            }

        };  // End of String Request Object.

        queue.add(stringRequest);
    }
    //End Of Process Form Fields Method


    public boolean validateFirstName(){
        String firstName = first_name.getText().toString();

        //check if First name is Empty
        if (firstName.isEmpty()){
            first_name.setError("First name cannot be Empty");
            return false;
        } else {
            first_name.setError(null);
            return true;
        }
    }
    //End of validate First name field

    public boolean validateLastName(){
        String lastName = last_name.getText().toString();

        //check if Last name is Empty
        if (lastName.isEmpty()){
            last_name.setError("Last name cannot be Empty");
            return false;
        } else {
            last_name.setError(null);
            return true;
        }
    }
    //End of validate Last name field

    public boolean validateEmail(){
        String email_e = email.getText().toString();

        //check if email is Empty
        if (email_e.isEmpty()){
            email.setError("Email cannot be Empty");
            return false;
        } else if (StringHelper.regexEmailValidationPattern(email_e)){
            email.setError("Please Enter Valid Email");
            return false;
        } else {
            email.setError(null);
            return true; //Check if Email Is empty
        }
    }
    //End of validate Email field

    public boolean validatePasswordAndConfirm(){
        String password_p = password.getText().toString();
        String confirm_p = confirm.getText().toString();

        //check if Password and Confirm fields are empty
        if (password_p.isEmpty()){
            password.setError("Password field cannot be Empty!");
            return false;
        } else if (!password_p.equals(confirm_p)){
            password.setError("Password do not match!");
            return false;
        }else if (confirm_p.isEmpty()) {
            confirm.setError("Confirm field cannot be Empty!");
            return false;
        }else {
            password.setError(null);
            confirm.setError(null);
            return true;
        }
    }
    //End of validate Password and Confirm fields
}
//End of Sign Up Activity class