package com.aaitabem.abugida.abugida;

import com.aaitabem.abugida.abugida.Model.api.SearchQuery;
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
public class SearchQueryUnitTesting {
    private String startingCity = "Addis Ababa";
    private String destinationCity = "Mekelle";
    @Test
    public void searchQueryTest() throws IOException {
        //Presidential
        JsonObject jsonObject = new JsonParser().parse(
                "{\"startingCity\": \"Addis Ababa\"," +
                "\n\"destinationCity\":\"Mekelle\""+
                "}").getAsJsonObject();

        SearchQuery searchQuery = new SearchQuery(startingCity, destinationCity);
        JsonObject jsonObjectToBesent = new JsonParser()
                .parse("{\"startingCity\": \""+searchQuery.getStartingCity()+"\"," +
                        "\n\"destinationCity\":\""+searchQuery.getDestinationCity()+"\""+
                        "}").getAsJsonObject();
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody(new Gson().toJson(jsonObjectToBesent)));
        mockWebServer.enqueue(new MockResponse().setHeader("Bearer", "dafjdhf.dahfldaadffh.dfdafwqiu9rnjkav"));
        mockWebServer.start();

        HttpUrl httpUrl = mockWebServer.url("api/bsu/travel/search/");
        String bodyOfRequest = sendGetRequest(new OkHttpClient(), httpUrl);
        Assert.assertEquals(new Gson().toJson(jsonObject), bodyOfRequest);

    }

    private String sendGetRequest(OkHttpClient okHttpClient, HttpUrl httpUrl) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), "searhc");
        Request request = new Request.Builder()
                .header("Bearer", "dafjdhf.dahfldafh.dfdafwqiu9rnjkav")
                .post(body)
                .url(httpUrl)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();

    }
}
