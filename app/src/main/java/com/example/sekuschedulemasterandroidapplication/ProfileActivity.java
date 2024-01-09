package com.example.sekuschedulemasterandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView tv_first_name, tv_last_name, tv_email;
    Button sign_out_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Hook Text View
        tv_first_name = findViewById(R.id.first_name);
        tv_last_name = findViewById(R.id.last_name);
        tv_email = findViewById(R.id.email);

        //Get Intent Extra Values
        String first_name = getIntent().getStringExtra("first_name");
        String last_name = getIntent().getStringExtra("last_name");
        String email = getIntent().getStringExtra("email");

        //Hook Sign Out Button
        sign_out_btn = findViewById(R.id.sign_out_btn);

        //Set on click Listener
        sign_out_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUserOut();
            }
        });


    }


    public void signUserOut(){

    }

}