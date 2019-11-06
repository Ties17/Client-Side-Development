package com.example.blindwallskirstenties;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity
{

    private Mural mural;
    private ImageView testimage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        testimage = findViewById(R.id.TestImage);

        mural = (Mural) getIntent().getSerializableExtra("Mural");

        Picasso.get().load("https://api.blindwalls.gallery/" + mural.getImages().get(0)).into(testimage);


    }
}
