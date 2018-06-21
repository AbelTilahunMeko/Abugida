package com.aaitabem.abugida.abugida.View.Tabs;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aaitabem.abugida.abugida.Controller.Controller;
import com.aaitabem.abugida.abugida.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    TextView textViewEmail, textViewPhone, TextviewName;
    static String firstNameDis, phoneNumberDis, emailDis;

    public ProfileTab() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        textViewEmail = view.findViewById(R.id.profile_email);
        textViewPhone = view.findViewById(R.id.profile_phone);
        TextviewName = view.findViewById(R.id.profile_name);

//        System.out.println("The first Name is "+ firstNameDis);
//        System.out.println("The phoneNumber is "+ phoneNumberDis);
//        System.out.println("The Email is "+ emailDis);
        textViewEmail.setText(emailDis);
        textViewPhone.setText(phoneNumberDis);
        TextviewName.setText(firstNameDis);

        String token = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("token", "");

        System.out.println("This token " + token);
        Controller controller = new Controller();
        controller.getProflie(token, this.getActivity());

        return view;
    }

    public void setTextEmail(String email) {
        emailDis = email;
    }

    public void setTextPhone(String phone) {
        phoneNumberDis = phone;
    }

    public void setTextName(String name) {
        firstNameDis = name;
    }

}
