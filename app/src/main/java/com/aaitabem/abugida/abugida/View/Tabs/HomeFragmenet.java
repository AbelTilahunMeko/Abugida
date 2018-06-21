package com.aaitabem.abugida.abugida.View.Tabs;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aaitabem.abugida.abugida.Controller.Controller;
import com.aaitabem.abugida.abugida.Model.api.UserLogin;
import com.aaitabem.abugida.abugida.Model.service.UserClient;
import com.aaitabem.abugida.abugida.R;
import com.aaitabem.abugida.abugida.View.Login;
import com.aaitabem.abugida.abugida.View.SearchResult;

import es.dmoral.toasty.Toasty;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO Set clickListener to data
//TODO add validation to the serahc inputs by the user
//TODO add on backPressed to handle the appropirate fragmenet to be displayed
public class HomeFragmenet extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    Controller controller;
    //Creating the retrofit server

    //The vies in this fragment
    Button searchButtonView;

    //The two editTexts
    EditText editTextStartingPoint;
    EditText editTextDestinationPoint;

    TextView pic_date;
    //Accessing the fragment
    SearchResult searchResult;

    private static String token;

    public HomeFragmenet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_fragmenet, container, false);

        controller = new Controller();
        searchButtonView = view.findViewById(R.id.serachButton);

        pic_date = view.findViewById(R.id.pic_date);
        pic_date.setOnClickListener(this);

        //Getting the starting and finishing point
        editTextDestinationPoint = view.findViewById(R.id.destinationPoint);
        editTextStartingPoint = view.findViewById(R.id.staringPoint);

        //Initializing the searchResult
        searchResult = new SearchResult();

        //setting on click listiner
        searchButtonView.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == searchButtonView) {
            token = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("token", "");
//            System.out.println("The Token is "+ token);
            String startingPoint = editTextStartingPoint.getText().toString();
            String destinationPoint = editTextDestinationPoint.getText().toString();
            controller.sendRequestSearch(token, startingPoint, destinationPoint);
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fragmentChanger(1);
//            Toasty.success(this.getContext(), "Success!", Toast.LENGTH_SHORT, true).show();

        } else if (v == pic_date) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this.getActivity(), this, 2000, 2, 2);
            datePickerDialog.show();
        }
    }

    public void fragmentChanger(int id) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (id == 1) {
            fragmentTransaction.replace(R.id.homeFrameLayout, searchResult);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}