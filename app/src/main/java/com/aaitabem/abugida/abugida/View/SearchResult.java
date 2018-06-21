package com.aaitabem.abugida.abugida.View;


import android.annotation.SuppressLint;
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

import com.aaitabem.abugida.abugida.Controller.Controller;
import  com.aaitabem.abugida.abugida.Controller.MyAdapter;

import com.aaitabem.abugida.abugida.Model.api.LoginModel;
import com.aaitabem.abugida.abugida.Model.api.Search;
import com.aaitabem.abugida.abugida.Model.api.UserLogin;
import com.aaitabem.abugida.abugida.Model.service.UserClient;
import com.aaitabem.abugida.abugida.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResult extends Fragment  {

    public static final String API_LOGIN_BASE_URL = "http://10.5.50.58:3000/userAccount/";

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(API_LOGIN_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();
    Controller controller = new Controller();

    static JSONObject searchResult = new JSONObject();
    UserClient userClient = retrofit.create(UserClient.class);
    String[] title ;
    String[] busTime;
    String[] busTicketPrice;
    String[] numberOfSeats;

    String[] travelID;
    String[] bspId;
    ListView lv;


    //Accessing the fragments
    SearchSelected searchSelected;
    int[] images = {R.drawable.imgres,
            R.drawable.imgres};


    public SearchResult() {
        // Required empty public constructor
    }

    public void setJson(JSONObject json){
        searchResult = json;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);

        //Initialize the fragment
        searchSelected = new SearchSelected();
//        System.out.println("This is the Result view "+searchResult);

        parser();
//        // Inflate the layout for this fragment
        lv = view.findViewById(R.id.listItems);
        MyAdapter adapter = new MyAdapter(this.getActivity(), title, busTime, images,numberOfSeats, busTicketPrice);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(this, "The item selected"+ position ,Toast.LENGTH_LONG).show();
                if(position <= title.length){
                    controller.getSelected(title[position], travelID[position], bspId[position] );
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.homeFrameLayout, searchSelected);
                    fragmentTransaction.commit();

                }
            }
        });
        return view;
    }


    public void parser(){
        //searchResult = controller.getsearchResult();
        System.out.println("This is the Result view "+searchResult);

        JSONArray jsonArray = searchResult.optJSONArray("Result");
        String[] getTitles = new String[jsonArray.length()];
        String[] getBusTime = new String[jsonArray.length()];
        String[] getSeats = new String[jsonArray.length()];
        String[] getPrice = new String[jsonArray.length()];
        String[] getId = new String[jsonArray.length()];
        String[] getBSPId = new String[jsonArray.length()];

        try {
            //Get the instance of JSONArray that contains JSONObjects

            //Iterate the jsonArray and print the info of JSONObjects
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.optString("id".toString());
                String bsp = jsonObject.optString("bspId".toString());
                String name = jsonObject.optString("bspName").toString();
                String time = jsonObject.optString("departureTime").toString();
                String price = jsonObject.optString("Price").toString();
                String seat = jsonObject.optString("seatLeft").toString();
                getTitles[i] = name.toString();
                getBusTime[i] = time;
                getSeats[i] = seat;
                getPrice[i] = price;
                getId[i] = id;
                getBSPId[i] =bsp;
            }

        } catch (JSONException e) {e.printStackTrace();}
        this.title = getTitles;
        this.busTime = getBusTime;
        this.busTicketPrice = getPrice;
        this.numberOfSeats = getSeats;
        this.travelID = getBSPId;
        this.bspId = getId;
//        System.out.println("This is The JSONArrat    "+jsonArray);


    }


}
