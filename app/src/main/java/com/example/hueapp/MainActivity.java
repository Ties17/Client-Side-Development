package com.example.hueapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.hueapp.Adapters.LampAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String TiesToken = "SpmILZFwnQhpVsCcUNWO0c5ObXXucxIHKzjNh5Lo";
    private String KirstenToken = "cRBAiU0YfOyx6dxOelEgngMYNVzyOw6yAJumocx1";

    private RecyclerView recyclerView;
    private LampAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<HueLamp> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.reyclerview);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        this.dataset = new ArrayList<>();
        dataset.add(new HueLamp(true, 100, 0, 0, false));
        dataset.add(new HueLamp(true, 100, 0, 0, false));


        adapter = new LampAdapter(dataset);

        recyclerView.setAdapter(adapter);



    }


}
