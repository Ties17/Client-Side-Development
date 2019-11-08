package com.example.blindwallskirstenties;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MuralsFactory {

    private Context context;
    private MuralListener muralListener;
    private final String APIUrl = "https://api.blindwalls.gallery/apiv2/murals";
    private RequestQueue queue;

    public MuralsFactory(Context context, MuralListener muralListener) {
        this.context = context;
        this.muralListener = muralListener;
        queue = Volley.newRequestQueue(context);
    }

    public void fillDataSetVolley(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                APIUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray responses) {
                        Log.d("LENGTH", "length = " + responses.length());
                        try {
                            for (int i = 0; i < responses.length(); i++) {
                                JSONObject response = responses.getJSONObject(i);
                                int id = response.getInt("id");
                                String title = response.getJSONObject("title").getString("nl");
                                String artist = response.getString("author");
                                String description = response.getJSONObject("description").getString("nl");
                                ArrayList<String> images = new ArrayList<>();
                                JSONArray imagesArray = response.getJSONArray("images");
                                for (int j = 0; j < imagesArray.length(); j++)
                                    images.add(imagesArray.getJSONObject(j).getString("url"));
                                int year = response.getInt("year");
                                String address = response.getString("address");
                                double rating;
                                if (!response.isNull("rating")){
                                     rating = response.getDouble("rating");
                                } else {
                                    rating = 0;
                                }

                                Mural mural = new Mural(id, title, artist, description, images, year, address, rating);

                                muralListener.OnMuralAvailable(mural);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("ERROR", "Message: " + e.getMessage());
                            muralListener.OnMuralError(new Error());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        muralListener.OnMuralError(new Error());
                    }
                }
        );

        queue.add(jsonArrayRequest);
    }



    public void fillDataSet(Activity activity){
        JSONArray wallsArray = parseJson(ReadJSONFromFile(activity));

        for(int i = 0; i < wallsArray.length(); i++){
            try {
                JSONObject wall = wallsArray.getJSONObject(i);

                int id = wall.getInt("id");
                String title = wall.getJSONObject("title").getString("nl");
                String artist = wall.getString("author");
                String description = wall.getJSONObject("description").getString("nl");
                ArrayList<String> images = new ArrayList<>();
                JSONArray imagesArray = wall.getJSONArray("images");
                for(int j = 0 ; j < imagesArray.length(); j++) images.add(imagesArray.getJSONObject(j).getString("url"));
                int year = wall.getInt("year");
                String address = wall.getString("address");
                double rating = wall.getDouble("rating");

                Mural mural = new Mural(id, title, artist, description, images, year, address, rating);
                muralListener.OnMuralAvailable(mural);

            } catch (JSONException e) {
                e.printStackTrace();
                muralListener.OnMuralError(new Error());
            }
        }
    }

    private JSONArray parseJson(String jsonString){
        JSONArray json;
        try {
            json = new JSONArray(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
            json = null;
        }

        return json;
    }

    public static String ReadJSONFromFile(Activity activity){
        String json;
        try{
            InputStream is = activity.getResources().openRawResource(R.raw.walls);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            json = null;
        }
        return json;
    }
}
