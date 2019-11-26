package com.example.hueapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.hueapp.Adapters.LampAdapter;
import com.example.hueapp.LampRequests.HueResponsesHandler;
import com.example.hueapp.LampRequests.Token;
import com.example.hueapp.LampRequests.VolleyRequestHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements HueResponsesHandler {

    private String TiesToken = "SpmILZFwnQhpVsCcUNWO0c5ObXXucxIHKzjNh5Lo";
    private String KirstenToken = "cRBAiU0YfOyx6dxOelEgngMYNVzyOw6yAJumocx1";

    private RecyclerView recyclerView;
    private LampAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<HueLamp> dataset;
    private VolleyRequestHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         helper = VolleyRequestHelper.getInstance(this, new Token(TiesToken), this);


        recyclerView = findViewById(R.id.reyclerview);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        this.dataset = new ArrayList<>();
        dataset.add(new HueLamp("1", true, 100, 0, 0, false));
        dataset.add(new HueLamp("2", true, 100, 0, 0, false));


        adapter = new LampAdapter(dataset, helper);

        recyclerView.setAdapter(adapter);

        helper.getGroup();

    }


    @Override
    public void lightInfoReceived(ArrayList<HueLamp> lamps) {
        this.dataset.clear();

        for(HueLamp lamp : lamps){
            this.dataset.add(lamp);
            adapter.notifyDataSetChanged();
        }

//        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnError() {

    }
}
