package com.aaitabem.abugida.abugida.View;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aaitabem.abugida.abugida.MainActivity;
import com.aaitabem.abugida.abugida.R;

import es.dmoral.toasty.Toasty;

public class Login extends AppCompatActivity implements View.OnClickListener {

    //TODO work validation on the login page before sending to the controller


    TextView signup;
    Button signin;

    //Lets find the inputs of the Login activity
    EditText usernameInput;
    EditText passwordInput;

    //Assign the edit text values to a String
    String usernameInputString;
    String passwordInputString;

    //The corrected user name and password values
    String correctPassword;
    String correctPhoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Getting the buttons that are used for redirecting
        signin = findViewById(R.id.loginButton);
        signup = findViewById(R.id.sign_upRedirect);

        //Getting the fields from the inputs
        usernameInput = findViewById(R.id.username_login);
        passwordInput = findViewById(R.id.password_login);



        //Assigning the inputs to string
        usernameInputString = usernameInput.getText().toString().trim();
        passwordInputString = passwordInput.getText().toString().trim();



        //getting the inputs that are stored in saved instance state
        if(savedInstanceState != null){
            CharSequence savedEmail = savedInstanceState.getCharSequence(usernameInputString);
            usernameInput.setText(savedEmail);

            CharSequence savedPassword = savedInstanceState.getCharSequence(passwordInputString);
            passwordInput.setText(savedPassword);
        }

        //Adding setOnclickListener

        //
        signin.setOnClickListener(this);
        signup.setOnClickListener(this);


    }

    @Override
    public void onClick(View v){
        if(signup == v){
            //Setting up the sign up page
            Intent intentSignUp = new Intent(this, Signup.class);
            finish();
            startActivity(intentSignUp);
        }else if(signin == v){
            //make sure this will be opened if the person is authenticated
            Intent intentHome = new Intent(this, Home.class);
            finish();
            Toasty.success(this, "Successful Login", Toast.LENGTH_LONG, true).show();
            startActivity(intentHome);
        }
    }
    @Override
    public void onBackPressed(){
//        Intent mainActivity = new Intent(this, MainActivity.class);
        finish();
//        startActivity(mainActivity);
    }

    @Override
    public void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);

        outstate.putCharSequence(usernameInputString, usernameInput.getText().toString());
        outstate.putCharSequence(passwordInputString, passwordInput.getText().toString());

    }

}
