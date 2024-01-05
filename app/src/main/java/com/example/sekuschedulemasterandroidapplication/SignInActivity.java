package com.example.sekuschedulemasterandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

}