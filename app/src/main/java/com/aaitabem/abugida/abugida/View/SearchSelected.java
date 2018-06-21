package com.aaitabem.abugida.abugida.View;


import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aaitabem.abugida.abugida.Controller.Controller;
import com.aaitabem.abugida.abugida.MainActivity;
import com.aaitabem.abugida.abugida.R;
import com.aaitabem.abugida.abugida.View.Tabs.SettingsTab;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchSelected extends Fragment
        implements
        AdapterView.OnItemSelectedListener,
        View.OnClickListener
{

    //TODO REMEMBER to add the arrow for the spinner
    //TODO when the confrim button is touched confirm booking. There is no set listener to that view. So u need to add an activity.
    //

    Spinner spinner;
    Button btnConfirm;
    JSONObject searchSelectedObj;
    Controller controller = new Controller();

    SettingsTab settingsTab = new SettingsTab();
    //Book button
    Button bookBusTicketBtn;
    TextView busName,bookingStation,destinationStation,passengerName,conf_starting,conf_dest,conf_date;
    public String boarding,destination,date,selectedName, selectedTraveId, selectedBSPId;


    private static final String[] paths = {"1", "2", "3"};
    private static int numberOfSeat=0;

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
        busName = view.findViewById(R.id.busName1);
        bookingStation = view.findViewById(R.id.bookingStationName);
        destinationStation = view.findViewById(R.id.destinationStationName);




        parser();
        busName.setText(selectedName);
        bookingStation.setText(boarding);
        destinationStation.setText(destination);

        //TODO this is where to fix it
//        btnConfirm = view.findViewById(R.id.confimButtonAlertD);
//        btnConfirm.setOnClickListener(this);

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
            showConfirm();
            settingsTab.booked = true;
        }
//        else if(btnConfirm == v){
//            Toast.makeText(getActivity(), "I hope this is write", Toast.LENGTH_SHORT).show();
//        }
    }

    private void showConfirm(){
    parser();
    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
    // Setting Dialog Title
    alertDialog.setTitle("Conforming Booking");
    // Setting Dialog Message
    alertDialog.setMessage("From : " + boarding + "\n" + "To : " + destination + "\n" + "Date : " + date);

    System.out.println("this is showCONFIRM ");
    System.out.println(boarding);

    alertDialog.setIcon(R.drawable.error);
    // Setting Confirm Button
    alertDialog.setButton("Confirm", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            // Write your code here to execute after dialog closed
//            Toast.makeText(getActivity(), "You clicked on Confirm", Toast.LENGTH_SHORT).show();
//            System.out.println("$$$$$$$$$$$$$$$$$$ numberOfSeat" + numberOfSeat);
//            System.out.println("$$$$$$$$$$$$$$$$$$ SelectedTravelID" + selectedTraveId);
//            System.out.println("$$$$$$$$$$$$$$$$$$ selectedBSPId" + selectedBSPId);
            String token = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("token","");
            String numberOfSeats = String.valueOf(numberOfSeat);
            controller.bookTicketUser(token,selectedBSPId,selectedTraveId,numberOfSeats);
            Toast.makeText(getActivity(), "Successfully Book Ticket", Toast.LENGTH_SHORT).show();
        }
    });

    //post

    // Showing Alert Message
    alertDialog.show();
}


    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 1:
                System.out.println(position);
                numberOfSeat =2;
                break;
            case 2:
//                System.out.println("Two is selected");
                numberOfSeat =3;
                break;
            case 3:
                System.out.println("Three is selected");
                numberOfSeat = 4;
                break;
            default:
                System.out.print("One has been selected");
                numberOfSeat = 2;

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void parser(){
//        System.out.println("I am here");
        searchSelectedObj = controller.setSelected();

        boarding = searchSelectedObj.optString("Boarding");
        destination = searchSelectedObj.optString("Destination");
//        date = searchSelectedObj.optString("Date");
        selectedName = searchSelectedObj.optString("Selected");
        //If there is no result here make sure to change them n check
        selectedTraveId = searchSelectedObj.optString("SelectedId");
        selectedBSPId = searchSelectedObj.optString("selectedBSPId");
//        System.out.println("$$$$$$$$$$$$$$$$$$ SelectedTravelID" + selectedTraveId);
//        System.out.println("$$$$$$$$$$$$$$$$$$ selectedBSPId" + selectedBSPId);
//        System.out.println("$$$$$$$$$$$$$$$$$$ numberOfSeat" + numberOfSeat);

//        System.out.println(searchSelectedObj);

    }
}
