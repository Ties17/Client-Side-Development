package com.example.hueapp.LampRequests;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VolleyRequestHelper {

    private static VolleyRequestHelper sInstantce = null;
    private Context context;
    private Token token;
    private String requestResponse;
    private String baseURL = "192.168.1.179/api";

    private VolleyRequestHelper(Context context, Token token){
        this.context = context;
        baseURL += "/" + token.getToken();
    }

    public static VolleyRequestHelper getInstance(Context context, Token token){
        if(sInstantce == null){
            sInstantce = new VolleyRequestHelper(context, token);
        }
        return sInstantce;
    }

    public void getLights(String[] lampNames){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                baseURL + "/lights",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
        });
    }

    public void getGroup(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                baseURL + "/group/4",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray lights = response.getJSONArray("lights");

                            for(int i = 0 ; i < lights.length(); i++){
                                
                            }
                            getLights();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
        });
    }

}
