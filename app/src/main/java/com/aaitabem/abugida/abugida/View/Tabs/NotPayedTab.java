package com.aaitabem.abugida.abugida.View.Tabs;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aaitabem.abugida.abugida.Controller.PayedSearchedResult;
import com.aaitabem.abugida.abugida.R;
import com.aaitabem.abugida.abugida.View.NotPayedSelected;
import com.aaitabem.abugida.abugida.View.SearchSelected;


public class NotPayedTab extends Fragment {
    String[] title = {
            "Selam Bus",
            "Selam Bus",
            "Selam Bus",
            "Selam Bus"
    };
    String[] busTime = {
            "11:30 AM",
            "12:30 AM",
            "12:30 AM",
            "12:30 AM"};
    String[] busTicketPrice = {
            "Pending",
            "Pending",
            "Pending",
            "Pending"
    };

    String[] numberOfSeats = {
            "2",
            "2",
            "2",
            "1"
    };
    ListView lv;

    //Accessing the fragments
    NotPayedSelected notPayedTab;
    int[] images = {R.drawable.imgres,R.drawable.imgres,  R.drawable.imgres, R.drawable.imgres,};

    public NotPayedTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_not_payed_tab, container, false);

        notPayedTab = new NotPayedSelected();


        // Inflate the layout for this fragment
        lv = view.findViewById(R.id.notpayedListItmes);
        PayedSearchedResult adapter = new PayedSearchedResult(this.getActivity(), title, busTime, images, numberOfSeats, busTicketPrice);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(this, "The item selected"+ position ,Toast.LENGTH_LONG).show();
                if (position == 0) {
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.homeFrameLayout, notPayedTab);
                    fragmentTransaction.commit();
                }
            }
        });
        return view;
    }

}
