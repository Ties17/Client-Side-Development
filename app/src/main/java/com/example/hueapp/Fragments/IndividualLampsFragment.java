package com.example.hueapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hueapp.Adapters.LampAdapter;
import com.example.hueapp.HueLamp;
import com.example.hueapp.LampRequests.HueResponsesHandler;
import com.example.hueapp.LampRequests.Token;
import com.example.hueapp.LampRequests.VolleyRequestHelper;
import com.example.hueapp.R;

import java.util.ArrayList;


public class IndividualLampsFragment extends Fragment implements HueResponsesHandler
{
    private String TiesToken = "SpmILZFwnQhpVsCcUNWO0c5ObXXucxIHKzjNh5Lo";
    private String KirstenToken = "cRBAiU0YfOyx6dxOelEgngMYNVzyOw6yAJumocx1";

    VolleyRequestHelper requestHelper;

    private RecyclerView recyclerView;
    private LampAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<HueLamp> dataset;

    SwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_individual_lamps,container,false);

        this.requestHelper = VolleyRequestHelper.getInstance(getActivity(), new Token(TiesToken), this );

        recyclerView = view.findViewById(R.id.reyclerview);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        this.dataset = new ArrayList<>();
        dataset.add(new HueLamp("1", true, 50, 100, 69, false));
        dataset.add(new HueLamp("2", true, 200, 69, 210, false));
        dataset.add(new HueLamp("3", true, 200, 69, 210, false));
        dataset.add(new HueLamp("4", true, 200, 69, 210, false));
        dataset.add(new HueLamp("5", true, 200, 69, 210, false));
        dataset.add(new HueLamp("6", true, 200, 69, 210, false));
        dataset.add(new HueLamp("7", true, 200, 69, 210, false));



        adapter = new LampAdapter(dataset, requestHelper);

        recyclerView.setAdapter(adapter);

        requestHelper.getGroup();

        refreshLayout = view.findViewById(R.id.refresh);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestHelper.getGroup();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        return view;
    }

    @Override
    public void lightInfoReceived(ArrayList<HueLamp> lamps) {
        this.dataset.clear();

        for(HueLamp lamp : lamps){
            this.dataset.add(lamp);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void OnError() {

    }
}
