package com.aaitabem.abugida.abugida.View;

import android.app.AlertDialog;
import android.app.Fragment;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.aaitabem.abugida.abugida.Controller.Controller;
import com.aaitabem.abugida.abugida.R;

/**
 * Created by ${Abel_Tilahun} on ${4/9/2018}.
 */
public class NotPayedSelected extends Fragment implements
        AdapterView.OnItemSelectedListener,
        View.OnClickListener, TextWatcher {

    //TODO REMEMBER to add the arrow for the spinner
    //TODO when the confrim button is touched confirm booking. There is no set listener to that view. So u need to add an activity.
    //
    Controller controller = new Controller();
    Spinner spinner;
    Button btnConfirm;

    //Book button
    Button submitTinBtn;
    EditText tinNum;
    Drawable errorIcon;

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
        tinNum = view.findViewById(R.id.tin_num);
        errorIcon = getResources().getDrawable(R.drawable.error, null);
        errorIcon.setBounds(new Rect(0, 0, errorIcon.getIntrinsicWidth(), errorIcon.getIntrinsicHeight()));

        //TODO this is where to fix it
//        btnConfirm = view.findViewById(R.id.confimButtonAlertD);
//        btnConfirm.setOnClickListener(this);
        submitTinBtn.setEnabled(false);
        submitTinBtn.setBackground(getResources().getDrawable(R.drawable.buttonblack, null));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        submitTinBtn.setOnClickListener(this);
        tinNum.addTextChangedListener( this);


        return view;
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
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (tinNum.getText().toString().trim().isEmpty()) {
            tinNum.setError("Tin can't be empty", errorIcon);
            System.out.println("this show error");
            submitTinBtn.setEnabled(false);
            submitTinBtn.setBackground(getResources().getDrawable(R.drawable.buttonblack, null));
        }
        else {
            submitTinBtn.setEnabled(true);
            submitTinBtn.setBackground(getResources().getDrawable(R.drawable.button, null));
        }

    }
    @Override
    public void onClick(View v) {
        if (submitTinBtn == v) {
            //Create an alert dialog fragment.

            int tinnum;
            String tin = tinNum.getText().toString();
            if (tin.isEmpty()) {
                tinnum = 0;
            } else {
                tinnum = Integer.parseInt(tin);
            }

            controller.peeding(tinnum);
            System.out.println("========= this from get =======");
            System.out.println(tinnum);

            showConfirmingButton();

        }

    }
    private void showConfirmingButton() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //This is the custom alertDialog that will be showed for confrim ticket
        builder.setView(R.layout.confirmsubmitui);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}