package com.aaitabem.abugida.abugida.Controller;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aaitabem.abugida.abugida.R;

/**
 * Created by littELGOsht on 4/6/2018.
 */

public class MyAdapter extends ArrayAdapter {

    private int[] imageArray;
    private String[] titleArray;
    private String[] descArray;
    private Context upperContext;
    private String[] busTime;
    private String[] numberOfSeats;
    private String[] bustTicketPrice;

    public MyAdapter(Context context, String[] titles1, String[] busTime, int[] img1, String[] numberOfSeats, String[] bustTicketPrice) {

        super(context, R.layout.listitems, R.id.idTitle, titles1);
        this.imageArray = img1;
        this.titleArray = titles1;
        this.busTime = busTime;
        this.numberOfSeats = numberOfSeats;
        this.bustTicketPrice =bustTicketPrice;
        this.upperContext = context;
    }


    //    Start Here extending the ArrayAdapter
    @Nullable
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.listitems, parent, false);

        //Get the reference to the view object
        ImageView myImage = row.findViewById(R.id.idPic);
        TextView titleText = row.findViewById(R.id.idTitle);
        TextView busTimeDisplayer = row.findViewById(R.id.id_description);
        TextView busPriceDisplayer = row.findViewById(R.id.pirceOfticket);
        TextView numberOfSeatsLeft = row.findViewById(R.id.seatsLeft);

        //Providing the element of an array by
//        myImage.setBackgroundColor(background[position]);
        myImage.setImageResource(imageArray[position]);
        titleText.setText(titleArray[position]);
        busTimeDisplayer.setText(busTime[position]);
        busPriceDisplayer.setText(bustTicketPrice[position]);
        numberOfSeatsLeft.setText(numberOfSeats[position]);




//        row.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(upperContext, "Hey", Toast.LENGTH_LONG).show();
//                    }
//                }
//        );

        return row;
    }


}
