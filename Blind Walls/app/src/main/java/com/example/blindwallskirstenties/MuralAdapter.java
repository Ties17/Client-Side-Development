package com.example.blindwallskirstenties;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MuralAdapter extends RecyclerView.Adapter<MuralAdapter.ImageViewHolder>
{

    private ArrayList<Mural> dataset;

    public MuralAdapter(ArrayList<Mural> dataset)
    {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_recycler, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);

        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder viewHolder, int i)
    {
        viewHolder.title.setText(dataset.get(i).getTitle());
        Picasso.get().load(dataset.get(i).getImages().get(2)).into(viewHolder.picture);
    }

    @Override
    public int getItemCount()
    {
        return dataset.size();
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        ImageView picture;


        public ImageViewHolder(View itemview)
        {
            super(itemview);

            title = itemview.findViewById(R.id.MuralText);
            picture = itemview.findViewById(R.id.MuralImage);

            itemview.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(v.getContext(), DetailActivity.class);
                    Mural m = dataset.get(ImageViewHolder.super.getAdapterPosition());
                    intent.putExtra("Mural", m);
                    v.getContext().startActivity(intent);
                }
            });


        }


    }
}
