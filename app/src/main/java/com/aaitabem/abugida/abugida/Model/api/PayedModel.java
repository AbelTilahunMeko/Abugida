package com.aaitabem.abugida.abugida.Model.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ${Abel_Tilahun} on ${4/9/2018}.
 */
public class PayedModel {
    public JSONObject peeding(int tin_number){
        JSONObject payeds = new JSONObject();
        JSONArray rsArry = new JSONArray();
        JSONObject rss = new JSONObject();
        String[] id = new String[7];
        String[] names = {"Selam Bus","Abay Bus","Golden Bus","Sky Bus","Ethio Bus","Falcon Bus","Walya Bus"};
        String[] time = {"11:00","11:20","11:25","11:30","11:35","11:35","11:50"};
        String[] seat = {"12","18","20","4","30","35","27"};
        String[] state = {"payed T","payed T","payed T","payed T","payed T","payed T","payed T"};
        try {
            for (int i = 0; i < names.length; i++) {
                JSONObject rs = new JSONObject();
                rs.put("Name", names[i]);
                rs.put("Time",time[i]);
                rs.put("State",state[i]);
                rs.put("Seat",seat[i]);
                rsArry.put(rs);
            }
        }
        catch (JSONException e){e.printStackTrace();}
        try {
            rss.put("Result", rsArry);
        }
        catch (JSONException e){e.printStackTrace();}

        return rss;
    }
}
