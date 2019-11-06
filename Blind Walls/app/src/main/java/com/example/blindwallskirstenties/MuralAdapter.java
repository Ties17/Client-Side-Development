package com.example.blindwallskirstenties;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MuralAdapter extends RecyclerView.Adapter
{

    private ArrayList<Mural> dataset;

    public MuralAdapter(ArrayList<Mural> dataset)
    {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_recycler, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);

        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {

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
//                    Intent intent = new Intent(v.getContext(), MuralDetailActivity.class);
//                    Mural m = dataset.get(ImageViewHolder.super.getAdapterPosition());
//                    intent.putExtra("Mural", m);
//                    v.getContext().startActivity(intent);
                }
            });


        }


    }
}
