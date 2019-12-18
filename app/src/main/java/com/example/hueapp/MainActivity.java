package com.example.hueapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.hueapp.Adapters.SectionsPageAdapter;
import com.example.hueapp.Fragments.AllLampsFragment;
import com.example.hueapp.Fragments.IndividualLampsFragment;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionsPageAdapter sectionsPageAdapter;

    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager)
    {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFrament(new AllLampsFragment(), "ALL");
        adapter.addFrament(new IndividualLampsFragment(), "INDIVIDUAL");
        viewPager.setAdapter(adapter);
    }

}