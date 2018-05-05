package com.aaitabem.abugida.abugida.View;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aaitabem.abugida.abugida.R;

@SuppressLint("ValidFragment")
public class UserInfo extends Fragment implements View.OnClickListener, TextWatcher {

    //Todo All the textWatcher Handle text changes that are afterChange Text
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    //This are the fragments that are called by this class
    PersonInfoFragment personInfoFragment;
    FinalInformation finalInformation;


    Button prev;
    Button nextBtn;

    //Getting the fields of the inputs
    EditText emailInput;
    EditText passwordInput;
    EditText confirmPasswordInput;
    EditText phoneNumberInput;

    //String Assigning of the field
    String emailInputString;
    String passwordInputString;
    String confirmPasswordInputString;
    String phoneNumberInputString;

    //Variables in the personal Info_class
    String firstName_info;
    String lastName_info;
    String dateOfBirth_info;
    String gender_info;

    //Making a golbal variable when the back button is pressed store them here
    String email;
    String phoneNumber;
    String password;
    String conPassword;
    //Error icon to show when there a user makes an error when filling the fields
    Drawable errorIcon;
    //Success icon to show when the user enters the same password with confirm password
    Drawable successIcon;

    public UserInfo() {

    }

    @SuppressLint("ValidFragment")
    public UserInfo(
            String firstName_info,
            String lastName_info,
            String dateOfBirthInfo,
            String gender,
            String email,
            String password,
            String conPassword,
            String phoneNumber
    ) {
        this.firstName_info = firstName_info;
        this.lastName_info = lastName_info;
        this.dateOfBirth_info = dateOfBirthInfo;
        this.gender_info = gender;
        this.email = email;
        this.password = password;
        this.conPassword = conPassword;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);

        //Fragment class initialization
        personInfoFragment = new PersonInfoFragment(
                firstName_info,
                lastName_info,
                gender_info,
                dateOfBirth_info,
                email,
                phoneNumber,
                password,
                conPassword

        );

        //Getting the EditText from the XML
        emailInput = view.findViewById(R.id.email);
        phoneNumberInput = view.findViewById(R.id.phoneNumber);
        passwordInput = view.findViewById(R.id.password_signup);
        confirmPasswordInput = view.findViewById(R.id.password_signup_confirm);
        prev = view.findViewById(R.id.prev_person_info);
        nextBtn = view.findViewById(R.id.SignUp);


        //If the fields have been previously filed lets set the values here

        emailInput.setText(email);
        phoneNumberInput.setText(phoneNumber);
        confirmPasswordInput.setText(conPassword);
        passwordInput.setText(password);


        nextBtn.setEnabled(false);
        nextBtn.setBackground(getResources().getDrawable(R.drawable.buttonblack, null));

        errorIcon = getResources().getDrawable(R.drawable.error, null);
        successIcon = getResources().getDrawable(R.drawable.check, null);

        successIcon.setBounds(new Rect(0, 0, successIcon.getIntrinsicWidth(), successIcon.getIntrinsicHeight()));
        errorIcon.setBounds(new Rect(0, 0, errorIcon.getIntrinsicWidth(), errorIcon.getIntrinsicHeight()));

        phoneNumberInput.setError(null, errorIcon);
        phoneNumberInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() <= 9) {
                    phoneNumberInput.setError("Phone Number too short", errorIcon);
//                    nextBtn.setEnabled(false);
//                    nextBtn.setBackground(getResources().getDrawable(R.drawable.buttonblack,null));
                }
                if( !(s.toString().trim().startsWith("09"))){
                    phoneNumberInput.setError("Phone Number should start with 09", errorIcon);
                    nextBtn.setEnabled(false);
                    nextBtn.setBackground(getResources().getDrawable(R.drawable.buttonblack,null));
                }

            }
        });


        //Controlling if the user have entered all the necessary fields
        // if they aren't filled the button will be disabled
        emailInput.addTextChangedListener(this);
        phoneNumberInput.addTextChangedListener(this);
        passwordInput.addTextChangedListener(this);
        confirmPasswordInput.addTextChangedListener(this);

        passwordInput.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (passwordInput.getText().toString().trim().isEmpty()) {
                            passwordInput.setError("Password can't be empty", errorIcon);
                        } else if (confirmPasswordInput.toString().trim().isEmpty()) {
                            confirmPasswordInput.setError("Password can't be empty", errorIcon);
                        } else {
                            //Code block executed if the length of the password if the password ins't empty.
                            if (passwordInput.getText().toString().trim().length() >= 6) {
                                if (passwordInput.getText().toString().trim().equals(confirmPasswordInput.getText().toString().trim())) {
                                    confirmPasswordInput.setError("Password Match", successIcon);
                                } else {
                                    confirmPasswordInput.setError("Password doesn't March", errorIcon);
                                    nextBtn.setEnabled(false);
                                    nextBtn.setBackground(getResources().getDrawable(R.drawable.buttonblack, null));
                                }
                            } else {
                                //This will be executed if the password length is less than 6 digits.
                                passwordInput.setError("Password too short", errorIcon);
                            }
                        }
                    }
                }
        );

        //Validating an email address
        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!(Patterns.EMAIL_ADDRESS.matcher(emailInput.getText().toString().trim()).matches())) {
                    emailInput.setError("Incorrect Email", errorIcon);
                    nextBtn.setEnabled(false);
                    nextBtn.setBackground(getResources().getDrawable(R.drawable.buttonblack, null));
                }
            }
        });

        //Adding listener to password input. If the user inputs confirmPassword first and then enter
        //Password the button will change even if they don't match


        //Adding listener to confirmPassword...
        confirmPasswordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (passwordInput.getText().toString().trim().isEmpty()
                        || confirmPasswordInput.toString().trim().isEmpty()) {
                    confirmPasswordInput.setError("Password can't be empty", errorIcon);
                } else {
                    if (passwordInput.getText().toString().trim().equals(confirmPasswordInput.getText().toString().trim())) {
                        confirmPasswordInput.setError("Password Match", successIcon);
                    } else {
                        confirmPasswordInput.setError("Password doesn't March", errorIcon);
                        nextBtn.setEnabled(false);
                        nextBtn.setBackground(getResources().getDrawable(R.drawable.buttonblack, null));
                    }
                }

            }
        });


        if (savedInstanceState != null) {
            CharSequence savedTextEmail = savedInstanceState.getCharSequence(emailInputString);
            CharSequence savedTextPhoneNumber = savedInstanceState.getCharSequence(phoneNumberInputString);
            CharSequence savedTextPassword = savedInstanceState.getCharSequence(phoneNumberInputString);
            CharSequence savedTextConfirmPassword = savedInstanceState.getCharSequence(confirmPasswordInputString);
            emailInput.setText(savedTextEmail);
            phoneNumberInput.setText(savedTextPhoneNumber);
            passwordInput.setText(savedTextPassword);
            confirmPasswordInput.setText(savedTextConfirmPassword);
        }

        prev.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == prev) {
            fragmentChanger(0);
//            Toast.makeText(this.getContext(), "Hey", Toast.LENGTH_SHORT).show();
        } else if (view == nextBtn) {
//            Toast.makeText(this.getContext(), "Next", Toast.LENGTH_SHORT).show();
            fragmentChanger(1);
        }
    }

    public void fragmentChanger(int id) {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.setCustomAnimations(R.animator.exit_right_to_left,R.animator.exit_left_toright,0,0);

        fragmentTransaction.setCustomAnimations(R.animator.exit_right_to_left, R.animator.exit_left_toright, 0, 0);

        if (id == 0) {
            fragmentTransaction.replace(R.id.signupFrameLayout, personInfoFragment);
        } else if (id == 1) {
            emailInputString = emailInput.getText().toString().trim();
            passwordInputString = passwordInput.getText().toString().trim();
            confirmPasswordInputString = confirmPasswordInput.getText().toString().trim();
            phoneNumberInputString = phoneNumberInput.getText().toString().trim();


            finalInformation = new FinalInformation(
                    firstName_info,
                    lastName_info,
                    dateOfBirth_info,
                    gender_info,
                    phoneNumberInputString,
                    emailInputString,
                    passwordInputString,
                    confirmPasswordInputString
            );
            fragmentTransaction.setCustomAnimations(R.animator.openright_to_left, R.animator.openleft_to_right, 0, 0);
            fragmentTransaction.replace(R.id.signupFrameLayout, finalInformation);
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            outState.putCharSequence(emailInputString, emailInput.getText().toString());
            outState.putCharSequence(passwordInputString, passwordInput.getText().toString());
            outState.putCharSequence(confirmPasswordInputString, passwordInput.getText().toString());
            outState.putCharSequence(phoneNumberInputString, phoneNumberInput.getText().toString());
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {

        if (phoneNumberInput.getText().toString().trim().length() <= 9
                || !(phoneNumberInput.getText().toString().trim().startsWith("09"))
                || emailInput.getText().toString().trim().length() == 0
                || passwordInput.getText().toString().trim().length() == 0
                || confirmPasswordInput.getText().toString().trim().length() == 0) {
            nextBtn.setEnabled(false);
            nextBtn.setBackground(getResources().getDrawable(R.drawable.buttonblack, null));
        } else {
            if (passwordInput.getText().toString().trim().length() == 0
                    || confirmPasswordInput.getText().toString().trim().length() == 0) {
                nextBtn.setEnabled(false);
                nextBtn.setBackground(getResources().getDrawable(R.drawable.buttonblack, null));
            } else {
                nextBtn.setEnabled(true);
                nextBtn.setBackground(getResources().getDrawable(R.drawable.button, null));
            }

        }

    }
}
