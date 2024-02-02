package com.example.sekuschedulemasterandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button sign_in, sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //End of On Create Method

    public void goToSignUp(View view){
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();

    }
    //End of Sign Up Activity

    public void goToSignIn(View view){
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }
    //End of Sign In Activity
}