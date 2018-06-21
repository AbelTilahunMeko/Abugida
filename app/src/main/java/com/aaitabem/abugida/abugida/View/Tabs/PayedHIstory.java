package com.aaitabem.abugida.abugida.View.Tabs;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aaitabem.abugida.abugida.Controller.Controller;
import com.aaitabem.abugida.abugida.Controller.MyAdapter;
import com.aaitabem.abugida.abugida.Controller.PayedSearchedResult;
import com.aaitabem.abugida.abugida.R;
import com.aaitabem.abugida.abugida.View.SearchSelected;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayedHIstory extends Fragment {
    //TODO when populating the vies dynamically first checek if there numbers doesn't match

    JSONObject payedObject = new JSONObject();
    int[] images = {R.drawable.imgres, R.drawable.imgres, R.drawable.imgres, R.drawable.imgres,R.drawable.imgres,R.drawable.imgres,R.drawable.imgres};
    String[] title,busTime,state,numberOfSeats;
    ListView lv;

    //Accessing the fragments
    SearchSelected searchSelected;

    Controller controller;
    public PayedHIstory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payed_history, container, false);
        //Initialize the fragment
        searchSelected = new SearchSelected();

        controller = new Controller();
        parser();
        // Inflate the layout for this fragment
        lv = view.findViewById(R.id.payedListItmes);
        PayedSearchedResult adapter = new PayedSearchedResult(this.getActivity(), title, busTime, images, numberOfSeats, state);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(this, "The item selected"+ position ,Toast.LENGTH_LONG).show();
                if (position <= title.length) {
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.homeFrameLayout, searchSelected);
                    fragmentTransaction.commit();
                }
            }
        });
        return view;
    }

    public void parser() {

        payedObject = controller.payed();
        JSONArray jsonArray = payedObject.optJSONArray("Result");
        String[] getTitles = new String[jsonArray.length()];
        String[] getBusTime = new String[jsonArray.length()];
        String[] getState = new String[jsonArray.length()];
        String[] seats = new String[jsonArray.length()];

        try {
            //Get the instance of JSONArray that contains JSONObjects

            //Iterate the jsonArray and print the info of JSONObjects
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.optString("Name").toString();
                String time = jsonObject.optString("Time").toString();
                String statee = jsonObject.optString("State").toString();
                String seat = jsonObject.optString("seat").toString();
                getTitles[i] = name.toString();
                getBusTime[i] = time;
                getState[i] = statee;
                seats[i] = seat;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.title = getTitles;
        this.busTime = getBusTime;
        this.state = getState;
        this.numberOfSeats = seats;
    }
}


