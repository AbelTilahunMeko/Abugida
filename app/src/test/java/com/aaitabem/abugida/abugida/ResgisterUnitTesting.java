package com.aaitabem.abugida.abugida;

import com.aaitabem.abugida.abugida.Model.api.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
public class ResgisterUnitTesting {
    private String firstName = "Abel";
    private String lastName = "Tilahun";
    private String dateOfBirth ="12/12/98";
    private String gender = "male";
    private String phone = "0912682146";
    private String email = "at351291@gmail.com";
    private String password="123456";
    @Test
    public void Register() throws IOException {
        User user = new User(firstName, lastName,dateOfBirth,gender,phone,email,password);
        JsonObject jsonObjectToBeSent = new JsonParser()
                .parse("{\"firstName\": \""+user.getFirstName()+"\"," +
                        "\n\"lastName\":\""+user.getLastName()+"\","+
                        "\n\"dateOfBirth\":\""+user.getDateOfBirth()+"\","+
                        "\n\"gender\":\""+user.getGender()+"\","+
                        "\n\"phone\":\""+user.getPhone()+"\","+
                        "\n\"email\":\""+user.getEmail()+"\","+
                        "\n\"password\":\""+user.getPassword()+"\""+
                        "}").getAsJsonObject();
        //Making Sure the input and out put of the Register model class works correctly
        MockWebServer server = new MockWebServer();
        JsonObject jsonObject = new JsonParser()
                .parse("{\"firstName\": \""+firstName+"\"," +
                        "\n\"lastName\":\""+lastName+"\","+
                        "\n\"dateOfBirth\":\""+dateOfBirth+"\","+
                        "\n\"gender\":\""+gender+"\","+
                        "\n\"phone\":\""+phone+"\","+
                        "\n\"email\":\""+email+"\","+
                        "\n\"password\":\""+password+"\""+
                        "}").getAsJsonObject();
        server.enqueue(new MockResponse().setBody(new Gson().toJson(jsonObject)));
        server.start();
        HttpUrl httpUrl = server.url("api/bsu/");
        String bodyOfRequest = sendGetRequest(new OkHttpClient(), httpUrl);
        Assert.assertEquals(new Gson().toJson(jsonObject), bodyOfRequest);


    }
    private String sendGetRequest(OkHttpClient okHttpClient, HttpUrl base) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), "hi there");
        Request request = new Request.Builder()
                .post(body)
                .url(base)
                .build();

        Response response = okHttpClient.newCall(request).execute();
//        String header = response.header("Bearer");
//        System.out.println("The Header of the test " + header);
        String x = new Gson().toJson(response.body());
        return response.body().string();
    }
}
