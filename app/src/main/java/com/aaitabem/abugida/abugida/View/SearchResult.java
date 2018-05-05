package com.aaitabem.abugida.abugida.View;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import  com.aaitabem.abugida.abugida.Controller.MyAdapter;

import com.aaitabem.abugida.abugida.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResult extends Fragment  {
    String[] title = {
            "Selam Bus",
            "Selam Bus",
            "Selam Bus",
            "Selam Bus",
            "Selam Bus",
            "Selam Bus",
            "Selam Bus",
            "Selam Bus",
            "Selam Bus"

    };
    String[] busTime = {"12:30 AM",
            "11:30 AM" ,
            "12:30 AM",
            "12:30 AM",
            "12:30 AM",
            "12:30 AM",
            "10:00 AM",
            "10:30 AM",
            "11:30 AM",
            "12:30 AM"};
    String[] busTicketPrice = {
            "250 ETB",
            "320 ETB",
            "250 ETB",
            "250 ETB",
            "250 ETB",
            "250 ETB",
            "250 ETB",
            "250 ETB",
            "250 ETB"

    };

    String[] numberOfSeats ={
            "32",
            "32",
            "21",
            "19",
            "21",
            "29",
            "21",
            "19",
            "21",
    };

    ListView lv;
    int[] images = {R.drawable.imgres,
            R.drawable.imgres, R.drawable.imgres,
            R.drawable.imgres,R.drawable.imgres,
            R.drawable.imgres,R.drawable.imgres,
            R.drawable.imgres,R.drawable.imgres,R.drawable.imgres,};


    public SearchResult() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        // Inflate the layout for this fragment
        lv = view.findViewById(R.id.listItems);
        MyAdapter adapter = new MyAdapter(this.getContext(), title, busTime, images,numberOfSeats, busTicketPrice);
        lv.setAdapter(adapter);

        return view;
    }

}
