package com.aaitabem.abugida.abugida;

import com.aaitabem.abugida.abugida.Model.api.LoginModel;
import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

/**
 * Created by ${Abel_Tilahun} on ${4/9/2018}.
 */
public class LoginUnitTesting {
    @Test
    public void loginTest() throws IOException, InterruptedException {

        String password = "0911880645";
        String phoneNumber = "1234";
        MockWebServer server = new MockWebServer();
        LoginModel loginModel = new LoginModel(password, phoneNumber);

//
//        server.enqueue(new MockResponse().setResponseCode(200));
        server.enqueue(new MockResponse().setBody("\"phone\": \"" + loginModel.getPhone() + "\"\n" + "\"password\":\"" + loginModel.getPassword() + "\""));
        //server.enqueue(new MockResponse().setHeader("Bearer", "hadjhafkjhdakfhdafkhdaf"));
//        server.enqueue(new MockResponse().setBody("Hey there you found me"));
        server.start();

        HttpUrl httpUrl = server.url("/api/login/bsu/");
        String bodyOfRequest = sendGetRequest(new OkHttpClient(), httpUrl);
        Assert.assertEquals("\"phone\": \"0911880645\"\n" + "\"password\":\"1234\"", bodyOfRequest);
    }

    private String sendGetRequest(OkHttpClient okHttpClient, HttpUrl base) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), "hi there");
        Request request = new Request.Builder()
                .header("Bearer" , "dafhdafhlh.dahfdla.hdafhdafdjhfdhae.daeafeaf")
                .post(body)
                .url(base)
                .build();

        Response response = okHttpClient.newCall(request).execute();
//        String header = response.header("Bearer");
//        System.out.println("The Header of the test " + header);
        return response.body().string();
    }
}
