package com.example.hueapp;

public class HueLamp {
    private String id;
    private boolean isOn;
    private int brightness;
    private int hue;
    private int saturation;
    private boolean isColorLooping;
    private int transitionTime;



    public HueLamp() {
    }

    public HueLamp(String id, boolean isOn, int brightness, int hue, int saturation, boolean isColorLooping) {
        this.id = id;
        this.isOn = isOn;
        this.brightness = brightness;
        this.hue = hue;
        this.saturation = saturation;
        this.isColorLooping = isColorLooping;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        this.isOn = on;
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
