package com.aaitabem.abugida.abugida.View;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aaitabem.abugida.abugida.R;

/**
 * Created by ${Abel_Tilahun} on ${4/9/2018}.
 */

@SuppressLint("ValidFragment")
public class FinalInformation extends Fragment implements View.OnClickListener{
    String firstName;
    String lastName;
    String phoneNumber;
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
        this.phoneNumber = phoneNumber;
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
        phoneNumber_viewr.setText(phoneNumber);
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
                phoneNumber
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
            Toast.makeText(getActivity(), "Finish of the view", Toast.LENGTH_SHORT).show();
        }
    }
}
