package com.aaitabem.abugida.abugida.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.aaitabem.abugida.abugida.Model.api.BookTicket;
import com.aaitabem.abugida.abugida.Model.api.LoginModel;
import com.aaitabem.abugida.abugida.Model.api.PayedModel;
import com.aaitabem.abugida.abugida.Model.api.ProfileModel;
import com.aaitabem.abugida.abugida.Model.api.Search;
import com.aaitabem.abugida.abugida.Model.api.SearchQuery;
import com.aaitabem.abugida.abugida.Model.api.UserLogin;
import com.aaitabem.abugida.abugida.Model.service.UserClient;
import com.aaitabem.abugida.abugida.View.Home;
import com.aaitabem.abugida.abugida.View.Login;
import com.aaitabem.abugida.abugida.View.SearchResult;
import com.aaitabem.abugida.abugida.View.Tabs.ProfileTab;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.dmoral.toasty.Toasty;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ${Abel_Tilahun} on ${4/9/2018}.
 */
public class Controller  extends  AppCompatActivity{
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String phoneNumber;
    private String email;
    private String password;
    private String confirmPass;

    private ArrayList<Search> arrayList;
    public static String boarding;
    public static String destiantion;
    public static String dates;
    public static int tinNum;

    public static int id;
    public static String SelectedName;
    public static String selectedBSP;
    public static String selectedId;

    public static final String API_SEARHC_BASE_URL_PRIVATE = "http://192.168.43.78:3000/api/bsu/travel/search/";
    public static final String API_PFROFILE_BASE_URL_PRIVATE = "http://192.168.43.78:3000/api/bsu/profile/";

    public static final String API_LOGIN_BASE_URL = "http://192.168.43.78:3000/api/login/bsu/";
    public static final String API_BOOK_BASE_URL = "http://192.168.43.78:3000/api/bsu/reservation/";

    public boolean sucessfulEnter = false;
    public JSONObject jsonObject = new JSONObject();

    public Controller() {

    }

    public Controller(
            String firstName,
            String lastName,
            String dateOfBirth,
            String gender,
            String phoneNumber,
            String email,
            String password,
            String confirmPass
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.confirmPass = confirmPass;
    }

    public boolean validator() {
        if (firstName.isEmpty() || lastName.isEmpty()
                || dateOfBirth.isEmpty() || gender.isEmpty()
                || phoneNumber.isEmpty() || email.isEmpty()
                || password.isEmpty() || confirmPass.isEmpty()) {

            return false;
        } else if (!(password.equals(confirmPass))) {
            return false;
        }
        //Bekki work on the validator here...


        return true;
    }

    public boolean loginValidtor(String username, String password) {
        if (username.length() < 10 || password.length() < 6) {
            System.out.println("The User name " + username);
            System.out.println("The Password " + password);

            return false;
        }

        return true;
    }

    public void getSelected(String name, String bsp, String id){
        SelectedName = name;
        selectedBSP = bsp;
        selectedId = id;
//        System.out.println("___________________________ Id " + id);
//        System.out.println("___________________________ selectedBSP " + bsp);

    }

    public JSONObject setSelected(){
        JSONObject result = new JSONObject();
        try{
            result.put("Boarding",boarding);
            result.put("Destination",destiantion);
            result.put("Selected",SelectedName);
            result.put("SelectedId" , selectedId);
            result.put("selectedBSPId", selectedBSP);

        }
        catch (JSONException e){e.printStackTrace();}

        return result;
    }
    public void sendRequestSearch(String token, String starting, String destination) {
        boarding = starting;
        destiantion = destination;
        System.out.println("#####################\nDestination " + destination);
        System.out.println("#####################\nStaring " + starting);

        final SearchQuery searchQuery = new SearchQuery(starting, destination);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_SEARHC_BASE_URL_PRIVATE)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        UserClient userClient = retrofit.create(UserClient.class);
        token = "Bearer " + token;
        Call<List<Search>> call = userClient.searchRoute(token, searchQuery);
        call.enqueue(new Callback<List<Search>>() {
            @Override
            public void onResponse(Call<List<Search>> call, Response<List<Search>> response) {
                if(response.isSuccessful()){
                    System.out.println("JSON Body" + new Gson().toJson(response.body()));
                    List<Search> ui= response.body();
                    System.out.println("The first item "+ui.get(0).get_id());
                    JSONArray jsonArray = new JSONArray();
                    try {
                        for (int i = 0; i < ui.size(); i++) {
                            JSONObject rs = new JSONObject();
                            rs.put("id", ui.get(i).get_id());
                            rs.put("bspId", ui.get(i).getBspID());
                            rs.put("routeId",ui.get(i).getRouteID());
                            rs.put("bspName",   ui.get(i).getBspName());
                            rs.put("departureTime",  ui.get(i).getDepartureTime());
                            rs.put("Price",  ui.get(i).getPrice());
                            rs.put("seatLeft",   ui.get(i).getSeatLeft());
                            rs.put("travelDate",   ui.get(i).getTravelDate());
                            jsonArray.put(rs);
                        }
                    }
                    catch (JSONException e){e.printStackTrace();}
                    try {
                        jsonObject.put("Result", jsonArray);
                    }
                    catch (JSONException e){e.printStackTrace();}
//                    System.out.println("JSON Body Print" +jsonObject);
                    SearchResult searchResult = new SearchResult();
                    searchResult.setJson(jsonObject);
                    System.out.println("The Whole Deal" + jsonObject);
                }else{
                    System.out.println("JSON Body" +response.message());
                }

//
            }

            @Override
            public void onFailure(Call<List<Search>> call, Throwable t) {

            }
        });




//        Call<ArrayList<Search>> call = userClient.searchRoute(token, searchQuery);
//        call.enqueue(new Callback<ArrayList<Search>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Search>> call, Response<ArrayList<Search>> response) {
//                if (response.isSuccessful()) {
//                    arrayList = response.body();
//
//                    JSONArray jsonArray = new JSONArray();
//                    try {
//                        for (int i = 0; i < arrayList.size(); i++) {
//                            JSONObject rs = new JSONObject();
//                            rs.put("id", response.body().get(i).toString());
//                            rs.put("bspName",  response.body().get(i).toString());
//                            rs.put("departureTime", response.body().get(i).toString());
//                            rs.put("Price", response.body().get(i).toString());
//                            rs.put("seatLeft",  response.body().get(i).toString());
//                            rs.put("travelDate",  response.body().get(i).toString());
//                            jsonArray.put(rs);
//                        }
//                    }
//                    catch (JSONException e){e.printStackTrace();}
//                    try {
//                        jsonObject.put("Result", jsonArray);
//                    }
//                    catch (JSONException e){e.printStackTrace();}
//
//                    //getsearchResult();
//                    Toast.makeText(activity, "Successfull", Toast.LENGTH_SHORT).show();
//                    System.out.println("JSON Body" + new Gson().toJson(response.body()));
//                    System.out.println("JSON Body" + jsonObject);
//
//                } else {
//                    Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show();
//                    System.out.println("The Error Message " + response.message());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Search>> call, Throwable t) {
//                Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show();
//
//            }
//        });


    }
    public JSONObject getsearchResult(){
        JSONObject restult = this.jsonObject;
        System.out.println("Please Print the:- " + restult);
        return restult;
    }

    public void getSearchResult(String bspID, String bspName, String routeId, String travelDate,
                                String departureTime, String seatleft, String busId, String travelId, String price
    ) {

        System.out.println("@@@@@@@@@@@@@@@@@@@\nBSP id " + bspID);
        System.out.println("bspName " + bspName);
        System.out.println("routeId " + routeId);
        System.out.println("travelDate " + travelDate);
        System.out.println("departureTime " + departureTime);
        System.out.println("seatleft " + seatleft);
        System.out.println("busId " + busId);
        System.out.println("travelId " + travelId);
        System.out.println("price " + price);

    }

    public void getProflie(String token, final Activity activity) {
        System.out.println("Successfully Made a connections with controller");
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_PFROFILE_BASE_URL_PRIVATE)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        UserClient userClient = retrofit.create(UserClient.class);

        token = "Bearer " + token;
        Call<ProfileModel> call = userClient.getProflie(token);
        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                if (response.isSuccessful()) {
                    String firstName = response.body().getFirstName();
                    String lastName = response.body().getLastName();
                    String dateOfBirth = response.body().getDateOfBirth();
                    String gender = response.body().getGender();
                    String phone = response.body().getPhone();
                    String email = response.body().getEmail();
//
                    String fullName = firstName + lastName;
                    System.out.println("full name "+ fullName);
                    ProfileTab np = new ProfileTab();
                    np.setTextEmail(email);
                    np.setTextName(firstName);
                    np.setTextPhone(phone);

                    System.out.println("################\nSuccess Message Body" + new Gson().toJson(response.body()));
                    Toast.makeText(activity, "Sucess", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(activity, "Error ", Toast.LENGTH_LONG).show();
                    System.out.println("################\nError Message Body " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                Toast.makeText(activity, "Network Error ", Toast.LENGTH_LONG).show();
                try {
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }

            }
        });


    }
    public int peeding(int tin_number){
        tinNum = tin_number;
        return tinNum;
    }
    public void bookTicketUser(String token, String bspId, String routeId, String numberOfSeats){
        BookTicket bookTicket = new BookTicket(bspId,routeId,numberOfSeats);
        System.out.println("$$$$$$$ BSP Id" + bspId);
        System.out.println("$$$$$$$ Route ID " + routeId);
        System.out.println("$$$$$$$ Number Of Seat " + numberOfSeats);

//        userClient.login(loginModel);
        //Creating the retrofit server
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BOOK_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        UserClient userClient = retrofit.create(UserClient.class);
        token = "Bearer " + token;
        Call<BookTicket> call = userClient.bookTicket(token, bookTicket);
        call.enqueue(new Callback<BookTicket>() {
            @Override
            public void onResponse(Call<BookTicket> call, Response<BookTicket> response) {
                if(response.isSuccessful()){
                    System.out.println("Successfully Sent");
                    System.out.println("Body Message :- " + new Gson().toJson(response.body()));
                }else{
                    System.out.println("Successfully Sent");
                    System.out.println("Body Message :- " + response.body());

                }
            }

            @Override
            public void onFailure(Call<BookTicket> call, Throwable t) {
                System.out.println("Network ERROR");
            }
        });
    }
    public JSONObject payed() {
        PayedModel payedModel = new PayedModel();
        JSONObject pedd = payedModel.peeding(tinNum);
        System.out.println("this from controller " + tinNum);
        return pedd;
    }

    public void sendRequestLogin(final Context context, String userName, String password) {
        LoginModel loginModel = new LoginModel(userName, password);
        System.out.println("Cont User Name " + userName);
        System.out.println("Cont Password " + password);
//        userClient.login(loginModel);
        //Creating the retrofit server
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_LOGIN_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        UserLogin userLogin = new UserLogin();
        UserClient userClient = retrofit.create(UserClient.class);
        Call<UserLogin> call = userClient.login(loginModel);
//        final boolean[] sucess = new boolean[1];

        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(@NonNull Call<UserLogin> call, @NonNull Response<UserLogin> response) {
                String token = "";
                if (response.isSuccessful()) {
//                    Intent intentHome = new Intent(getApplicationContext(), Home.class);
//                    startActivity(intentHome);
                    Login login = new Login();
                    login.creatIntnet(true);


//                    Toast.makeText(activity, "This Works", Toast.LENGTH_SHORT).show();
                    assert response.body() != null;
                    try {
                        System.out.println("***********************\nBody" + new Gson().toJson(response.body()));
                        token = response.body().getToken().toString();
                        String message = response.body().getMessage().toString();

                        //tokenToBeStored = token;


                        response.body().setToken(token);
                        response.body().setMessage(message);
                        response.body().setSuccess(true);
//                        sucessfulEnter = response.body().getSuccess();
//                        System.out.println("The Boolean :- " + sucess);
//                        System.out.println("The Token :- " + token);
//                        System.out.println("The Message:- " + message);
//                        sucessfulEnter = true;



                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Ok");
//                    System.out.println("The Body of the Request Token\n " + new Gson().toJson(response.body().getToken()));
//                    sucessfulLogin(context, token, true);

                } else {
//                    Toast.makeText(activity, "Authentication Error", Toast.LENGTH_SHORT).show();
                    System.out.println("Authentication Error");
                    System.out.println(response.message());
                    //response.body().setSuccess(false);
//                    sucessfulLogin(context, "", false);
//                    sucessfulEnter = false;
                }

            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {

//                sucessfulLogin(context, "", false);
//                sucessfulEnter = false;

//                Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show();
                System.out.println("####################Network Error");
                try {
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });

//        System.out.println("This is the get Success Result"+ sucessfulEnter);
//        return sucessfulEnter;
    }
    public boolean succesLog(){
        return sucessfulEnter;
    }

    public void setToken(Context context, String token){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("token", token).apply();
    }
    public void getToken(Context context, String string){

    }
    public void sucessfulLogin( Context context, String token, boolean status) {

//        String tokenSaved = PreferenceManager.getDefaultSharedPreferences(context).getString("token", "nothing");
//
//        UserLogin userLogin = new UserLogin();

//        System.out.println("Hehehehe the saved token in sharedPreference \n" + sucess);
//
//        Login login = new Login();
//        login.successfulLogin(context);

//        Login login = new Login();
//        if(sucess){
//            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^SuccessTrue");
//            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^TOKEN"+ token);
////            login.successfulLogin(token);
//        }else{
//            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^SuccessFalse");
//            login.unscessfull();
//        }
    }



}
