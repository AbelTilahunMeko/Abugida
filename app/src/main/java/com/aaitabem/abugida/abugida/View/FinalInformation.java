package com.aaitabem.abugida.abugida.View;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aaitabem.abugida.abugida.MainActivity;
import com.aaitabem.abugida.abugida.Model.api.User;
import com.aaitabem.abugida.abugida.Model.service.UserClient;
import com.aaitabem.abugida.abugida.R;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ${Abel_Tilahun} on ${4/9/2018}.
 */

@SuppressLint("ValidFragment")
public class FinalInformation extends Fragment implements View.OnClickListener{
   // public static final String API_BASE_URI = "http://10.5.50.58:3000/userAccount/";
//   public static final String API_BASE_URI_SIGN_UP = "http://10.5.50.21:3000/api/bsu/";
   public static final String API_BASE_URI_SIGN_UP = "http://192.168.43.78:3000/api/bsu/";

    String firstName;
    String lastName;
    String phone;
    String dateOfBirth;
    String email;
    String gender;
    String password;
    String conPassword;

    //Finding the output textView
    TextView firstName_viewr;
    TextView lastName_viewr;
    TextView phoneNumber_viewr;
    TextView dateOfBirth_viewr;
    TextView email_viewr;
    TextView gender_viewr;

    //Getitng the buttons
    Button signUp;
    Button prev;

    //Fragments that are called by this class
    UserInfo userInfo;

    @SuppressLint("ValidFragment")
    public FinalInformation(
            String firstName,
            String lastName,
            String dateOfBirth,
            String gender,
            String phoneNumber,
            String email,
            String password,
            String conPassword


    ){
        this.firstName =firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.gender = gender;
        this.phone = phoneNumber;
        this.conPassword = conPassword;
        this.password = password;
    };
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstacneState) {
        View view = layoutInflater.inflate(R.layout.final_information_view, container, false);

        firstName_viewr = view.findViewById(R.id.firstName_final);
        lastName_viewr = view.findViewById(R.id.lastName_final);
        dateOfBirth_viewr = view.findViewById(R.id.dateBirth_final);
        phoneNumber_viewr = view.findViewById(R.id.phoneNumber_final);
        email_viewr = view.findViewById(R.id.email_final);
        gender_viewr = view.findViewById(R.id.gender_final);


        //setting the text that are set by the constructor
        firstName_viewr.setText(firstName);
        lastName_viewr.setText(lastName);
        dateOfBirth_viewr.setText(dateOfBirth);
        phoneNumber_viewr.setText(phone);
        email_viewr.setText(email);
        gender_viewr.setText(gender);

        //Getting the buttons
        signUp = view.findViewById(R.id.confirmation_sign_up);
        prev= view.findViewById(R.id.prev_account_info);

        userInfo = new UserInfo(
                firstName,
                lastName,
                dateOfBirth,
                gender,
                email,
                password,
                conPassword,
                phone
        );

        prev.setOnClickListener(this);
        signUp.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view){
        if(view == prev){
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//            System.out.println("The Gender is " +genderSelected);
            fragmentTransaction.setCustomAnimations(R.animator.exit_right_to_left,R.animator.exit_left_toright  ,0,0);


            fragmentTransaction.replace(R.id.signupFrameLayout, userInfo);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }else if( signUp == view){
//            Toast.makeText(getActivity(), "Finish of the view", Toast.LENGTH_SHORT).show();
            User user = new User(
                    firstName,
                    lastName,
                    dateOfBirth,
                    gender,
                    phone,
                    email,
                    password
            );
            sendNetwrokReques(user);


        }

    }


    //This section of code will be moved to the controller class after completion

    public void sendNetwrokReques(User user){
        //Use an instance of the retrofit class
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URI_SIGN_UP)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        UserClient userClient = retrofit.create(UserClient.class);

        Call<User> call = userClient.createAccount(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(getActivity(), "Account Created", Toast.LENGTH_LONG ).show();

                if(response.isSuccessful()){
                    //Make sure to add the below code here when ever u are creating a new user
                    System.out.println("SIgn Up Response " + new Gson().toJson(response.body()));
                    Intent singinIntent = new Intent(getActivity(), Login.class);
                    startActivity(singinIntent);
                }else{
                    System.out.println("SIGN Up ERROR " + new Gson().toJson(response.body()));

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(), "Network Failure", Toast.LENGTH_LONG).show();
                System.out.println("What is tit##########################   ");
                System.out.println(t);
            }
        });
    }

}

