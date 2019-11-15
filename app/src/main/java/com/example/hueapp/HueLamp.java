package com.example.hueapp;

public class HueLamp {
    private int id;
    private boolean on;
    private int brightness;
    private int hue;
    private int saturation;
    private boolean isColorLooping;
    private int transitionTime;



    public HueLamp() {
    }

    public HueLamp(boolean on, int brightness, int hue, int saturation, boolean isColorLooping) {
        this.on = on;
        this.brightness = brightness;
        this.hue = hue;
        this.saturation = saturation;
        this.isColorLooping = isColorLooping;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public int getHue() {
        return hue;
    }

    public void setHue(int hue) {
        this.hue = hue;
    }

    public int getSaturation() {
        return saturation;
    }

    public void setSaturation(int saturation) {
        this.saturation = saturation;
    }

    public boolean isColorLooping() {
        return isColorLooping;
    }

    public void setColorLooping(boolean colorLooping) {
        isColorLooping = colorLooping;
    }

    public int getTransitionTime() {
        return transitionTime;
    }

    public void setTransitionTime(int transitionTime) {
        this.transitionTime = transitionTime;
    }
}
