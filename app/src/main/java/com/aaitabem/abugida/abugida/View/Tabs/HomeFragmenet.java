package com.aaitabem.abugida.abugida.View.Tabs;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aaitabem.abugida.abugida.R;
import com.aaitabem.abugida.abugida.View.SearchResult;

import es.dmoral.toasty.Toasty;

public class HomeFragmenet extends Fragment implements View.OnClickListener {


    //The vies in this fragment
    Button searchButtonView;

    //The two editTexts
    EditText editTextStartingPoint;
    EditText editTextDestinationPoint;

    //Accessing the fragment
    SearchResult searchResult;


    public HomeFragmenet() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_fragmenet, container, false);

        searchButtonView = view.findViewById(R.id.serachButton);

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
            fragmentChanger(1);
//            Toasty.success(this.getContext(), "Success!", Toast.LENGTH_SHORT, true).show();

        }
    }

    public void fragmentChanger(int id) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (id == 1) {
            fragmentTransaction.replace(R.id.homeFrameLayout, searchResult);
        }
        fragmentTransaction.commit();
    }


}
