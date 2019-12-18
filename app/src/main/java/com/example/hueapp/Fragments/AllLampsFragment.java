package com.example.hueapp.Fragments;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.android.volley.VolleyError;
import com.example.hueapp.HueLamp;
import com.example.hueapp.LampRequests.HueResponsesHandler;
import com.example.hueapp.LampRequests.Token;
import com.example.hueapp.LampRequests.VolleyRequestHelper;
import com.example.hueapp.R;
import com.madrapps.pikolo.ColorPicker;
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener;

import java.util.ArrayList;


public class AllLampsFragment extends Fragment implements HueResponsesHandler {
    ColorPicker colorPicker;
    ImageView imageView;
    Button select;
    Switch aSwitch;
    Switch colorLoopSwitch;
    private String TiesToken = "SpmILZFwnQhpVsCcUNWO0c5ObXXucxIHKzjNh5Lo";
    private String KirstenToken = "cRBAiU0YfOyx6dxOelEgngMYNVzyOw6yAJumocx1";

    private HueResponsesHandler responsesHandler = this;

    public float hue = 0;
    public float sat = 0;
    public float bri = 0;

    private boolean state = true;
    private boolean colorloop = false;

    private VolleyRequestHelper requestHelper;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_lamps, container, false);

        imageView = view.findViewById(R.id.imageView);
        colorPicker = view.findViewById(R.id.colorPicker);
        select = view.findViewById(R.id.selectBtn);

        colorPicker.setColorSelectionListener(new SimpleColorSelectionListener() {
            @Override
            public void onColorSelected(int color) {
                imageView.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
                //final String hexColor = String.format("#%06X", (0xFFFFFF & color));
                float[] hsv = new float[3];

                Color.colorToHSV(color,hsv);
                System.out.println(hsv[0]);
                System.out.println(hsv[1]);
                System.out.println(hsv[2]);

                hue = hsv[0];
                sat = hsv[1];
                bri = hsv[2];
                //TODO set hue bri sat so requestHelper.setHSB() request works.
            }
        });

        this.requestHelper = VolleyRequestHelper.getInstance(getActivity(), new Token(TiesToken), this);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestHelper = VolleyRequestHelper.getInstance(getActivity(), new Token(TiesToken), responsesHandler );
                requestHelper.getGroup();
            }
        });

        aSwitch = view.findViewById(R.id.switchAll);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                requestHelper = VolleyRequestHelper.getInstance(getActivity(), new Token(TiesToken), responsesHandler );
                state = b;
                requestHelper.getGroup();
            }
        });

        colorLoopSwitch = view.findViewById(R.id.colorloopSwitch);
        colorLoopSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                requestHelper = VolleyRequestHelper.getInstance(getActivity(), new Token(TiesToken), responsesHandler);
                colorloop = b;
                requestHelper.getGroup();
            }
        });

        return view;
    }

    @Override
    public void lightInfoReceived(ArrayList<HueLamp> lamps)
    {
        for(HueLamp l : lamps)
        {
            System.out.println( hue + " "+sat +" "+ bri);
            System.out.println( hue*182 +" "+ sat *255 + " "+bri*255);
            requestHelper.setHSB(l.getId(), (int) (hue * 182), (int) (sat*254),(int)(bri*254));
            requestHelper.putOnStateLight(l.getId(), state);
            if(state) requestHelper.setColorLoop(l.getId(), colorloop);
        }

    }

    @Override
    public void OnError() {

    }
}
