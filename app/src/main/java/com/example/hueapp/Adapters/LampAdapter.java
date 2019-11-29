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
import com.example.hueapp.LampRequests.HueResponsesHandler;
import com.example.hueapp.LampRequests.VolleyRequestHelper;
import com.example.hueapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LampAdapter extends RecyclerView.Adapter<LampAdapter.ImageViewHolder>
{
    ArrayList<HueLamp> dataset = new ArrayList<>();
    VolleyRequestHelper requestHelper;

    public LampAdapter(ArrayList<HueLamp> dateset, VolleyRequestHelper requestHelper)
    {
        this.dataset = dateset;
        this.requestHelper = requestHelper;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view, dataset.get(i));

        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position)
    {
//        float[] hsb = {holder.HueBar.getProgress(), holder.SatBar.getProgress()/254f, holder.BriBar.getProgress()/254f};
//        holder.lampColor.setBackgroundColor(Color.HSVToColor(hsb));
        holder.lamp = dataset.get(position);
        holder.lampIdText.setText("Lamp id: " + holder.lamp.getId());


        float hue = (holder.lamp.getHue()/65535f) * 360;
        float[] hsb2 = {hue, holder.lamp.getSaturation(), holder.lamp.getBrightness()};
        holder.lampColor.setBackgroundColor(Color.HSVToColor(hsb2));
        holder.HueBar.setProgress((int)hsb2[0]);
        holder.SatBar.setProgress((int)hsb2[1]);
        holder.BriBar.setProgress((int)hsb2[2]);
    }

    @Override
    public int getItemCount()
    {
        return dataset.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder
    {
        HueLamp lamp;
        Button arrowBttn;
        View lampColor;
        SeekBar HueBar;
        SeekBar SatBar;
        SeekBar BriBar;
        TextView HueText;
        TextView SatText;
        TextView BriText;
        TextView lampIdText;
        ConstraintLayout expandableView;
        CardView cardview;


        public ImageViewHolder(View view, final HueLamp lamp1)
        {
            super(view);
            lamp = lamp1;
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
            lampIdText = view.findViewById(R.id.LampIdView);



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
                    float[] hsb = {HueBar.getProgress(), SatBar.getProgress()/254f, BriBar.getProgress()/254f};
                    lampColor.setBackgroundColor(Color.HSVToColor(hsb));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar)
                {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar)
                {
                    //TODO FIX LAMP ID
                    int hueFromBar = HueBar.getProgress();
                    float percentage = hueFromBar/360f;
                    int hue = (int) (percentage * 65535);
                    requestHelper.putOnStateLight(lamp.getId(), true);
                    requestHelper.setHue(lamp.getId(), hue);
                }
            });

            SatBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
            {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b)
                {
                    SatText.setText(String.valueOf(progress));
                    float[] hsb = {HueBar.getProgress(), SatBar.getProgress()/254f, BriBar.getProgress()/254f};
                    lampColor.setBackgroundColor(Color.HSVToColor(hsb));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar)
                {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar)
                {
                    //TODO FIX LAMP ID
                    requestHelper.putOnStateLight(lamp.getId(), true);
                    requestHelper.setSaturation(lamp.getId(), SatBar.getProgress());
                }
            });

            BriBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
            {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b)
                {
                    BriText.setText(String.valueOf(progress));
                    float[] hsb = {HueBar.getProgress(), SatBar.getProgress()/254f, BriBar.getProgress()/254f};
                    lampColor.setBackgroundColor(Color.HSVToColor(hsb));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar)
                {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar)
                {
                    //TODO FIX LAMP ID
                    requestHelper.putOnStateLight(lamp.getId(), true);
                    requestHelper.setBrightness(lamp.getId(), BriBar.getProgress());
                }
            });
        }
    }

}
