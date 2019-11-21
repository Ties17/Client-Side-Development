package com.example.hueapp.LampRequests;

import com.example.hueapp.HueLamp;

import java.util.ArrayList;

public interface HueResponsesHandler {
    void lightInfoReceived(ArrayList<HueLamp> lamps);
    void OnError();
}
