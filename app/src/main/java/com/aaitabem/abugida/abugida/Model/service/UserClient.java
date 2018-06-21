package com.aaitabem.abugida.abugida.Model.service;

import com.aaitabem.abugida.abugida.Model.api.BookTicket;
import com.aaitabem.abugida.abugida.Model.api.LoginModel;
import com.aaitabem.abugida.abugida.Model.api.ProfileModel;
import com.aaitabem.abugida.abugida.Model.api.Search;
import com.aaitabem.abugida.abugida.Model.api.SearchQuery;
import com.aaitabem.abugida.abugida.Model.api.User;
import com.aaitabem.abugida.abugida.Model.api.UserLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import okhttp3.ResponseBody;

import static com.aaitabem.abugida.abugida.Controller.Controller.API_BOOK_BASE_URL;
import static com.aaitabem.abugida.abugida.Controller.Controller.API_LOGIN_BASE_URL;
import static com.aaitabem.abugida.abugida.Controller.Controller.API_PFROFILE_BASE_URL_PRIVATE;
import static com.aaitabem.abugida.abugida.Controller.Controller.API_SEARHC_BASE_URL_PRIVATE;
import static com.aaitabem.abugida.abugida.View.FinalInformation.API_BASE_URI_SIGN_UP;

/**
 * Created by ${Abel_Tilahun} on ${4/9/2018}.
 */
public interface UserClient {
    @POST(API_BASE_URI_SIGN_UP)
    Call<User> createAccount(@Body User user);

    //Creating a Token authentication
    @POST(API_LOGIN_BASE_URL)
    Call<UserLogin> login(@Body LoginModel loginModel );

    //If Authentication is successful we have a token n get info
    @GET("secreInfo")
    Call<ResponseBody> getSecret(@Header("Authorization") String authToken);

    //This is a basic Auth
    @POST(API_SEARHC_BASE_URL_PRIVATE)
    Call<List<Search>> searchRoute(@Header("Authorization") String authHeader, @Body SearchQuery searchQuery);

    @POST(API_SEARHC_BASE_URL_PRIVATE)
    Call<Search> getSearchResult(@Header("Authorization") String authHeader);

    @POST(API_SEARHC_BASE_URL_PRIVATE)
    @FormUrlEncoded
    Call<Search> inD(@Field("startingCity") String startCity, @Field("destinationCity") String desti);

    @GET(API_PFROFILE_BASE_URL_PRIVATE)
    Call<ProfileModel> getProflie(@Header("Authorization") String authHeaderToken);

    @POST(API_BOOK_BASE_URL)
    Call<BookTicket> bookTicket(@Header("Authorization") String authHeaderToken, @Body BookTicket bookTicket);

}
