package com.example.blindwallskirstenties;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailActivity extends AppCompatActivity
{

    private Mural mural;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mural = (Mural) getIntent().getSerializableExtra("Mural");

    }
}
