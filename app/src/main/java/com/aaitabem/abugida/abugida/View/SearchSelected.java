package com.aaitabem.abugida.abugida.View;


import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.aaitabem.abugida.abugida.MainActivity;
import com.aaitabem.abugida.abugida.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchSelected extends Fragment
        implements
        AdapterView.OnItemSelectedListener,
        View.OnClickListener
{

    //TODO REMEMBER to add the arrow for the spinner

    Spinner spinner;

    //Book button
    Button bookBusTicketBtn;

    private static final String[] paths = {"1", "2", "3"};

    public SearchSelected() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search_selected, container, false);
        spinner = view.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, paths);

        bookBusTicketBtn = view.findViewById(R.id.bookTicketButton);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        bookBusTicketBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v){
        if(bookBusTicketBtn == v){
            //Create an alert dialog fragment.
            showConfirmingButton();
        }
    }

    private void showConfirmingButton() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //This is the custom alertDialog that will be showed for confrim ticket
        builder.setView(R.layout.confimbookinglayout);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 1:
                System.out.println("One is selected");
                break;
            case 2:
                System.out.println("Two is selected");
                break;
            case 3:
                System.out.println("Three is selected");
                break;
            default:
                System.out.print("One has been selected");


        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
