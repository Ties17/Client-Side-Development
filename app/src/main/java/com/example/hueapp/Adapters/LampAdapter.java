package com.example.hueapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hueapp.HueLamp;
import com.example.hueapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LampAdapter extends RecyclerView.Adapter<LampAdapter.ImageViewHolder>
{
    ArrayList<HueLamp> dataset = new ArrayList<>();

    public LampAdapter(ArrayList<HueLamp> dateset)
    {
        this.dataset = dateset;
    }
    
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);

        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return dataset.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder
    {

        public ImageViewHolder(View view)
        {
            super(view);



        }
    }

}
