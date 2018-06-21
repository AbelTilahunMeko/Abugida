package com.aaitabem.abugida.abugida.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aaitabem.abugida.abugida.Controller.Controller;
import com.aaitabem.abugida.abugida.Model.api.LoginModel;
import com.aaitabem.abugida.abugida.Model.api.UserLogin;
import com.aaitabem.abugida.abugida.Model.service.UserClient;
import com.aaitabem.abugida.abugida.R;
import com.google.gson.Gson;

import es.dmoral.toasty.Toasty;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.aaitabem.abugida.abugida.R.color.red;

public class Login extends AppCompatActivity implements View.OnClickListener {

    //TODO work validation on the login page before sending to the controller
    //
    // TODO When ever there is a network request we should go off from the main thread, Implement that
    //

    public static final String API_LOGIN_BASE_URL = "http://localhost:3000/api/login/bsu/";


    TextView signup, incorrectText;
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


    //Token
    private static String tokenToBeStored;
    private String token;
    private boolean okayStatus = false;
    Drawable errorIcon;


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

        incorrectText= findViewById(R.id.incorrect);

        //getting the inputs that are stored in saved instance state
        if (savedInstanceState != null) {
            CharSequence savedEmail = savedInstanceState.getCharSequence(usernameInputString);
            usernameInput.setText(savedEmail);

            CharSequence savedPassword = savedInstanceState.getCharSequence(passwordInputString);
            passwordInput.setText(savedPassword);
        }


        //Adding setOnclickListener

        //
        signin.setOnClickListener(this);
        signup.setOnClickListener(this);

        errorIcon = getResources().getDrawable(R.drawable.error, null);
        errorIcon.setBounds(new Rect(0, 0, errorIcon.getIntrinsicWidth(), errorIcon.getIntrinsicHeight()));
        usernameInput.setError(null, errorIcon);
        usernameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!editable.toString().startsWith("09")){
                    usernameInput.setError("Phone Number should start with 09", errorIcon);
                }
                else if (editable.toString().length() <= 9){
                    usernameInput.setError("Phone Number too short", errorIcon);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (signup == v) {
            //Setting up the sign up page
            Intent intentSignUp = new Intent(this, Signup.class);
            finish();
            startActivity(intentSignUp);
        } else if (signin == v) {
            //make sure this will be opened if the person is authenticated

//            Intent intentName = new Intent(this, Home.class);
            //This is the User Sign in click

            //Assigning the inputs to string
            usernameInputString = usernameInput.getText().toString();
            passwordInputString = passwordInput.getText().toString();


            Controller controller = new Controller();
            boolean success = controller.loginValidtor(usernameInputString, passwordInputString);

            if (success) {
                System.out.println("User Name " + usernameInputString);
                System.out.println("Password " + passwordInputString);
                // boolean x = controller.sendRequestLogin (this, usernameInputString, passwordInputString);

                sendRequestLogin(usernameInputString,passwordInputString);
                System.out.println("{}{}{}{}{}{}{}" + okayStatus);
//                if (okayStatus) {
//                    Intent intentHome = new Intent(this, Home.class);
//                    startActivity(intentHome);
//                    finish();
//                    Toasty.success(this, "Successful Login", Toast.LENGTH_LONG, true).show();
//                } else {
//                    Toasty.success(this, "Sorry ", Toast.LENGTH_LONG, true).show();
//                }
            } else {
                Toast.makeText(this, "Shit it's not working", Toast.LENGTH_LONG).show();

            }

        }
    }

    public void creatIntnet(boolean status) {
        this.okayStatus = status;

//        Intent intentHome = new Intent(this, Home.class);
//        startActivity(intentHome);
//        finish();
    }

    public void successfulLogin(Context context) {


//        System.out.println("The token to be stored buddy" + tokenToBeStored);
        //Before allowing access make sure to store the token
//        System.out.println("**********(((((((((((((( Token "+ token);
//        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("token", token).apply();


//        String tokenSaved = PreferenceManager.getDefaultSharedPreferences(this).getString("token","nothing");

//        System.out.println("Hehehehe the saved token in sharedPreference \n"+tokenSaved);
        //This will redirect if there is a sucessful login

        // startActivity(intentHome);

    }

    public void sendRequestLogin(String userName, String password) {
        LoginModel loginModel = new LoginModel(userName, password);
        System.out.println("Cont User Name " + userName);
        System.out.println("Cont Password " + password);
//        userClient.login(loginModel);
        //Creating the retrofit server
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_LOGIN_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

//        UserLogin userLogin = new UserLogin();
        UserClient userClient = retrofit.create(UserClient.class);
        Call<UserLogin> call = userClient.login(loginModel);
//        final boolean[] sucess = new boolean[1];

        //Asycn
        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(@NonNull Call<UserLogin> call, @NonNull Response<UserLogin> response) {
                String token = "";
                if (response.isSuccessful()) {
//                    Intent intentHome = new Intent(getApplicationContext(), Home.class);
//                    startActivity(intentHome);

//                    Toast.makeText(activity, "This Works", Toast.LENGTH_SHORT).show();
                    assert response.body() != null;
                    try {
                        System.out.println("***********************\nBody" + new Gson().toJson(response.body()));
                        token = response.body().getToken().toString();
                        String message = response.body().getMessage().toString();

                        //tokenToBeStored = token;


                        response.body().setToken(token);
                        response.body().setMessage(message);
                        response.body().setSuccess(true);

//                        sucessfulEnter = response.body().getSuccess();
//                        System.out.println("The Boolean :- " + sucess);
//                        System.out.println("The Token :- " + token);
//                        System.out.println("The Message:- " + message);
//                        sucessfulEnter = true;

                        intentCreation();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Ok");
//                    System.out.println("The Body of the Request Token\n " + new Gson().toJson(response.body().getToken()));
//                    sucessfulLogin(context, token, true);

                } else {
//                    Toast.makeText(this, "Authentication Error", Toast.LENGTH_SHORT).show();
                    incorrectText.setText("Username or Password Incorrect");
                    incorrectText.setTextColor(getResources().getColor(R.color.red));
                    incorrectText.setTextSize(20);
                    System.out.println("Authentication Error");
                    System.out.println(response.message());

                }

            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                System.out.println("Network Error");
                incorrectText.setText("Wef Yelem, Not Connected");
                incorrectText.setTextColor(getResources().getColor(R.color.red));
                incorrectText.setTextSize(20);
            }
        });
    }

    public void intentCreation() {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
            finish();
    }


    @Override
    public void onBackPressed() {
//        Intent mainActivity = new Intent(this, MainActivity.class);
        finish();
//        startActivity(mainActivity);
    }

    @Override
    public void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);

        outstate.putCharSequence(usernameInputString, usernameInput.getText().toString());
        outstate.putCharSequence(passwordInputString, passwordInput.getText().toString());

    }


}