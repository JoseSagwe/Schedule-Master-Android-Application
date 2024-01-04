package com.example.sekuschedulemasterandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sekuschedulemasterandroidapplication.helpers.StringHelper;

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
        Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
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
            last_name.setError(null);
            return true;
        }
    }
    //End of validate Email field

    public boolean validatePasswordAndConfirm(){
        String password_p = password.getText().toString();
        String confirm_p = confirm.getText().toString();

        //check if Password and Confirm fields are empty
        if (password_p.isEmpty() || confirm_p.isEmpty()){
            password.setError("Password field cannot be Empty");
            confirm.setError("Confirm field cannot be Empty");
            return false;
        } else {
            password.setError(null);
            confirm.setError(null);
            return true;
        }
    }
    //End of validate Password and Confirm fields

}
//End of Sign Up Activity class