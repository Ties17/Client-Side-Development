package com.example.blindwallskirstenties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MuralAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Mural> dataset;
    private MuralsFactory factory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.hasFixedSize();

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        factory = new MuralsFactory();
        dataset = new ArrayList<>();
        adapter = new MuralAdapter(dataset);

        recyclerView.setAdapter(adapter);




    }
}
