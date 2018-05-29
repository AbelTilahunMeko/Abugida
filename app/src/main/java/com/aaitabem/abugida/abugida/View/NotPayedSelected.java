package com.aaitabem.abugida.abugida.View;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.aaitabem.abugida.abugida.R;

/**
 * Created by ${Abel_Tilahun} on ${4/9/2018}.
 */
public class NotPayedSelected extends Fragment implements
        AdapterView.OnItemSelectedListener,
        View.OnClickListener
{

    //TODO REMEMBER to add the arrow for the spinner
    //TODO when the confrim button is touched confirm booking. There is no set listener to that view. So u need to add an activity.
    //

    Spinner spinner;
    Button btnConfirm;

    //Book button
    Button submitTinBtn;

    private static final String[] paths = {"1", "2", "3"};

    public NotPayedSelected() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.notpayedselectedui, container, false);
        spinner = view.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, paths);

        submitTinBtn = view.findViewById(R.id.submitTinNumber);

        //TODO this is where to fix it
//        btnConfirm = view.findViewById(R.id.confimButtonAlertD);
//        btnConfirm.setOnClickListener(this);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        submitTinBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v){
        if(submitTinBtn == v){
            //Create an alert dialog fragment.
            showConfirmingButton();
        }
//        else if(btnConfirm == v){
//            Toast.makeText(getActivity(), "I hope this is write", Toast.LENGTH_SHORT).show();
//        }
    }

    private void showConfirmingButton() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //This is the custom alertDialog that will be showed for confrim ticket
        builder.setView(R.layout.confirmsubmitui);

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
