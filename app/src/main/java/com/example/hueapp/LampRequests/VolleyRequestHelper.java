package com.example.hueapp.LampRequests;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hueapp.HueLamp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VolleyRequestHelper {

    private static VolleyRequestHelper sInstantce = null;
    private Context context;
    private Token token;
    private String requestResponse;
    private RequestQueue queue;
    private HueResponsesHandler responsesHandler;
    private String baseURL = "192.168.1.179/api";

    private VolleyRequestHelper(Context context, Token token, HueResponsesHandler responsesHandler){
        this.context = context;
        baseURL += "/" + token.getToken();
        queue = Volley.newRequestQueue(context);
    }

    public static VolleyRequestHelper getInstance(Context context, Token token, HueResponsesHandler responsesHandler){
        if(sInstantce == null){
            sInstantce = new VolleyRequestHelper(context, token, responsesHandler);
        }
        return sInstantce;
    }

    public void putOnStateLight(String lampId, boolean isOn){
        try{
            HueJsonObjectRequest hueRequest = new HueJsonObjectRequest(Request.Method.PUT,
                    baseURL + "/lights/" + lampId + "/state",
                    new JSONObject("{ 'on': " + isOn + "}"),
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
            );

            queue.add(hueRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void getLights(final String[] lightNames){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                baseURL + "/lights",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ArrayList<HueLamp> lamps = new ArrayList<>();
                                for(String lightName : lightNames) {
                                    JSONObject light = response.getJSONObject(lightName);

                                    boolean isOn = light.getJSONObject("state").getBoolean("on");
                                    int brightness = light.getJSONObject("state").getInt("bri");
                                    int hue = light.getJSONObject("state").getInt("hue");
                                    int saturation = light.getJSONObject("state").getInt("sat");
                                    String effect = light.getJSONObject("state").getString("effect");
                                    boolean isColorLooping = false;
                                    if(effect.equals("colorloop")) isColorLooping = true;
                                    HueLamp lamp = new HueLamp(isOn, brightness, hue, saturation, isColorLooping);
                                    lamps.add(lamp);
                                }

                                responsesHandler.lightInfoReceived(lamps);
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

        queue.add(request);
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
                            String[] lightNames = new String[lights.length()];
                            for(int i = 0 ; i < lights.length(); i++){
                                lightNames[i] = lights.get(i).toString();
                            }
                            getLights(lightNames);

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

        queue.add(request);
    }

}
