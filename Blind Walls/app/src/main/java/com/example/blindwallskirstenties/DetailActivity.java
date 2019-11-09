package com.example.blindwallskirstenties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity
{

    private Mural mural;
    private TextView title;
    private TextView artist;
    private TextView address;
    Button arrowBttn;
    ConstraintLayout expandableView;
    CardView cardview;
    private TextView description;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mural = (Mural) getIntent().getSerializableExtra("Mural");
        LinearLayout gallery = findViewById(R.id.gallery);
        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i = 0; i < mural.getImages().size(); i++)
        {
            View view = inflater.inflate(R.layout.item, gallery, false);

            ImageView galleryimage = view.findViewById(R.id.GalleryImage);
            Picasso.get().load("https://api.blindwalls.gallery/" + mural.getImages().get(i)).into(galleryimage);

            gallery.addView(view);
        }

        title = findViewById(R.id.TitleText);
        title.setText(mural.getTitle() + " (" + mural.getYear() + ")");
        artist = findViewById(R.id.ArtistText);
        artist.setText(mural.getArtist());
        address.findViewById(R.id.AddressText);
        address.setText(mural.getAddress());

        expandableView = findViewById(R.id.expandableView);
        arrowBttn = findViewById(R.id.ArrowBttn);
        cardview = findViewById(R.id.DescriptionCardview);
        description = findViewById(R.id.DescriptionText);
        description.setText(mural.getDescription());

        arrowBttn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (expandableView.getVisibility() == View.GONE)
                {
                    TransitionManager.beginDelayedTransition(cardview, new AutoTransition());
                    expandableView.setVisibility(View.VISIBLE);
                    arrowBttn.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                }
            }
        });




//        private String description;
//        private double rating;

    }
}
