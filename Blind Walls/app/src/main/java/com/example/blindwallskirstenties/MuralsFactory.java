package com.example.blindwallskirstenties;

import android.app.Activity;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MuralsFactory {

    private Context context;
    private MuralListener muralListener;

    public MuralsFactory(Context context, MuralListener muralListener) {
        this.context = context;
        this.muralListener = muralListener;
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
