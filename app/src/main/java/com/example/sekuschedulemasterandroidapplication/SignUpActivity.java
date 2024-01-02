package com.example.sekuschedulemasterandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
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
}