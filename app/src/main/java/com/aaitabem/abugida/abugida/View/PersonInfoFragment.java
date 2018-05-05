package com.aaitabem.abugida.abugida.View;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aaitabem.abugida.abugida.R;

import org.w3c.dom.Text;

/**
 * Created by ${Abel_Tilahun} on ${4/9/2018}.
 */

//TODO remember to handel back press in order to go to the appropriate spot

@SuppressLint("ValidFragment")
public class PersonInfoFragment extends Fragment implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener,
        RadioGroup.OnCheckedChangeListener, TextWatcher {
    Button send;
    Signup signup;


    //Getting the views from the xml
    EditText firstNameInput;
    EditText lastNameInput;
    RadioGroup radioGroup;
    TextView dateofBirth_textView;

    //Assigning values of the inputs
    String firstNameInputString;
    String lastNameInputString;
    String genderSelected;
    String dateOfBirthString;

    //creating a global variable that for getting the state of the inputs
    String firstName;
    String lastName;
    String gender;
    String dateOfBirth;
    String email;
    String password;
    String confPassword;
    String phoneNumber;

    public PersonInfoFragment(){

    }

    @SuppressLint("ValidFragment")
    public PersonInfoFragment(
            String firtName,
            String lastName,
            String gender,
            String dateOfBirth,
            String email,
            String phoneNumber,
            String password,
            String confPassword

    ) {

        this.firstName = firtName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;

        this.password = password;
        this.email = email;
        this.confPassword = confPassword;
        this.phoneNumber = phoneNumber;

    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstacneState) {
        View view = layoutInflater.inflate(R.layout.personinfofragment, container, false);


        dateofBirth_textView = view.findViewById(R.id.dateBirth);
        firstNameInput = view.findViewById(R.id.firstName_signup);
        lastNameInput = view.findViewById(R.id.lastName_signup);
        radioGroup = view.findViewById(R.id.genderSelection);

        //If there has been a value that have been set before we can set it here

        dateofBirth_textView.setText(dateOfBirth);
        firstNameInput.setText(firstName);
        lastNameInput.setText(lastName);


        dateofBirth_textView.addTextChangedListener(this);
        send = view.findViewById(R.id.heyllo);
        send.setEnabled(false);
        send.setBackground(getResources().getDrawable(R.drawable.buttonblack, null));




        firstNameInput.addTextChangedListener(this);
        lastNameInput.addTextChangedListener(this);

        //Handling Input's of first name and last name
        firstNameInput.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        // Handle the inputs here please don't forget
                        //Todo This is where you handle the firstName also remember to handle the lastName
                    }
                }
        );


        signup = new Signup();
//
        dateofBirth_textView.setOnClickListener(this);
        send.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        return view;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        System.out.println("The selected year:- " + view.getYear() + "/" + view.getMonth() + "/" + view.getDayOfMonth());
        String dateChoosen = view.getDayOfMonth() + "/" + view.getMonth() + "/" + view.getYear();
        dateofBirth_textView.setText(dateChoosen);
        dateofBirth_textView.setTextColor(getResources().getColor(R.color.black, null));
        this.dateOfBirthString = dateChoosen;
//        send.setEnabled(true);
//        send.setBackgroundColor(getResources().getColor(R.color.buttonColor,null));

    }

    @Override
    public void onClick(View v) {
//        if(!(TextUtils.isEmpty(firstNameInputString)|| TextUtils.isEmpty(lastNameInputString))){
//            send.setEnabled(true);
//            send.setBackgroundColor(getResources().getColor(R.color.buttonColor, null));
//        }
        if (v == dateofBirth_textView) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), this, 2000, 2, 2);
            datePickerDialog.show();

        } else if (send == v) {
            //Lets find the inputs of the fields
            firstNameInputString = firstNameInput.getText().toString().trim();
            lastNameInputString = lastNameInput.getText().toString().trim();

            UserInfo userInfo = new UserInfo(
                    firstNameInputString,
                    lastNameInputString,
                    dateOfBirthString,
                    genderSelected,
                    email,
                    password,
                    confPassword,
                    phoneNumber
            );
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//            System.out.println("The Gender is " +genderSelected);
            fragmentTransaction.setCustomAnimations(R.animator.openright_to_left, R.animator.openleft_to_right, 0, 0);


            fragmentTransaction.replace(R.id.signupFrameLayout, userInfo);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outstate) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {


        switch (checkedId) {
            case R.id.genderFemale:
//                Toast.makeText(this.getContext(), this.dateOfBirthString, Toast.LENGTH_SHORT).show();
                genderSelected = "female";
                if (TextUtils.isEmpty(dateOfBirthString)
                        || TextUtils.isEmpty(firstNameInput.getText().toString())
                        || TextUtils.isEmpty(lastNameInput.getText().toString())
                        ) {
                    send.setEnabled(false);
                    send.setBackground(getResources().getDrawable(R.drawable.buttonblack, null));
                } else {
                    send.setEnabled(true);
                    send.setBackground(getResources().getDrawable(R.drawable.button, null));
                }
                break;
            case R.id.genderMale:
//                Toast.makeText(this.getContext(), this.genderSelected, Toast.LENGTH_SHORT).show();
                genderSelected = "male";
                if (TextUtils.isEmpty(dateOfBirthString)
                        || TextUtils.isEmpty(firstNameInput.getText().toString())
                        || TextUtils.isEmpty(lastNameInput.getText().toString())
                        )  {
                    send.setEnabled(false);
                    send.setBackground(getResources().getDrawable(R.drawable.buttonblack, null));
                } else {
                    send.setEnabled(true);
                    send.setBackground(getResources().getDrawable(R.drawable.button, null));
                }
                break;
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (TextUtils.isEmpty(genderSelected) || TextUtils.isEmpty(dateOfBirthString)) {
            Toast.makeText(this.getContext(), this.genderSelected, Toast.LENGTH_SHORT).show();
            send.setEnabled(false);
            send.setBackground(getResources().getDrawable(R.drawable.buttonblack, null));
        } else {
            send.setEnabled(true);
            send.setBackground(getResources().getDrawable(R.drawable.button, null));
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (firstNameInput.getText().toString().trim().length() == 0
                || lastNameInput.getText().toString().trim().length() == 0) {
            send.setEnabled(false);
            send.setBackground(getResources().getDrawable(R.drawable.buttonblack, null));
        } else {
            if (TextUtils.isEmpty(genderSelected) || TextUtils.isEmpty(dateOfBirthString)) {
                Toast.makeText(this.getContext(), this.genderSelected, Toast.LENGTH_SHORT).show();
                send.setEnabled(false);
                send.setBackground(getResources().getDrawable(R.drawable.buttonblack, null));
            } else {
                send.setEnabled(true);
                send.setBackground(getResources().getDrawable(R.drawable.button, null));
            }

        }
    }
}
