package com.example.hueapp.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LampAdapter extends RecyclerView.Adapter
{
    ArrayList<String> dataset = new ArrayList<>();

    public LampAdapter(ArrayList<String> dateset)
    {
        this.dataset = dateset;
    }
    
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return 0;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder
    {

        public ImageViewHolder(View view)
        {
            super(view);



        }
    }

}
