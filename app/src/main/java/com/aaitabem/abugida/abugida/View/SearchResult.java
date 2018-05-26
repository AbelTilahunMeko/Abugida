package com.aaitabem.abugida.abugida.View;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

    //Accessing the fragments
    SearchSelected searchSelected;
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

        //Initialize the fragment
        searchSelected = new SearchSelected();


        // Inflate the layout for this fragment
        lv = view.findViewById(R.id.listItems);
        MyAdapter adapter = new MyAdapter(this.getActivity(), title, busTime, images,numberOfSeats, busTicketPrice);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(this, "The item selected"+ position ,Toast.LENGTH_LONG).show();
                if(position == 0){
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.homeFrameLayout, searchSelected);
                    fragmentTransaction.commit();
                }
            }
        });
        return view;
    }

}
