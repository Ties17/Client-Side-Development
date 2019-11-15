package com.example.hueapp.LampRequests;

import com.example.hueapp.HueLamp;

public class HueLampController {

    private HueLamp lamp;

    public HueLampController(HueLamp lamp) {
        this.lamp = lamp;
    }

    public void ChangeLampData(){

    }



    public HueLamp getLamp() {
        return lamp;
    }

    public void setLamp(HueLamp lamp) {
        this.lamp = lamp;
    }
}
