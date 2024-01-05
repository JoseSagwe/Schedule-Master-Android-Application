package com.example.sekuschedulemasterandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sekuschedulemasterandroidapplication.helpers.StringHelper;

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

    }
    //End of On Create Activity

    public void goToHome(View view){
        Intent intent = new Intent (SignInActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToSignUpAct(View view){
        Intent intent = new Intent (SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }


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

        //check if Password and Confirm fields are empty
        if (password_p.isEmpty()){
            et_password.setError("Password field cannot be Empty!");
            return false;
       }else {
            et_password.setError(null);
            return true;
        }
    }
    //End of validate Password and Confirm fields

}