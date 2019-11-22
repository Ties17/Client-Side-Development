package com.example.hueapp.Adapters;

import android.graphics.Color;
import android.nfc.cardemulation.CardEmulation;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
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
        float[] hsb = {holder.HueBar.getProgress(), holder.SatBar.getProgress()/100f, holder.BriBar.getProgress()/100f};
        holder.lampColor.setBackgroundColor(Color.HSVToColor(hsb));
    }

    @Override
    public int getItemCount()
    {
        return dataset.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder
    {

        Button arrowBttn;
        View lampColor;
        SeekBar HueBar;
        SeekBar SatBar;
        SeekBar BriBar;
        TextView HueText;
        TextView SatText;
        TextView BriText;
        ConstraintLayout expandableView;
        CardView cardview;


        public ImageViewHolder(View view)
        {
            super(view);

            arrowBttn = view.findViewById(R.id.arrowBttn);
            lampColor = view.findViewById(R.id.lampColor);
            HueBar = view.findViewById(R.id.HBar);
            SatBar = view.findViewById(R.id.SBar);
            BriBar = view.findViewById(R.id.BBar);
            HueText = view.findViewById(R.id.HueText);
            SatText = view.findViewById(R.id.SatText);
            BriText = view.findViewById(R.id.BriText);
            expandableView = view.findViewById(R.id.expandableView);
            cardview = view.findViewById(R.id.CardView);


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
                    } else
                    {
                        TransitionManager.beginDelayedTransition(cardview, new AutoTransition());
                        expandableView.setVisibility(View.GONE);
                        arrowBttn.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    }
                }
            });

            HueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
            {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b)
                {
                    HueText.setText(String.valueOf(progress));
                    float[] hsb = {HueBar.getProgress(), SatBar.getProgress()/100f, BriBar.getProgress()/100f};
                    lampColor.setBackgroundColor(Color.HSVToColor(hsb));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar)
                {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar)
                {
                }
            });

            SatBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
            {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b)
                {
                    SatText.setText(String.valueOf(progress));
                    float[] hsb = {HueBar.getProgress(), SatBar.getProgress()/100f, BriBar.getProgress()/100f};
                    lampColor.setBackgroundColor(Color.HSVToColor(hsb));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar)
                {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar)
                {

                }
            });

            BriBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
            {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b)
                {
                    BriText.setText(String.valueOf(progress));
                    float[] hsb = {HueBar.getProgress(), SatBar.getProgress()/100f, BriBar.getProgress()/100f};
                    lampColor.setBackgroundColor(Color.HSVToColor(hsb));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar)
                {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar)
                {

                }
            });
        }
    }

}
