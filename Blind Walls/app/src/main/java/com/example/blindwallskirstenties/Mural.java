package com.example.blindwallskirstenties;

import java.io.Serializable;

class Mural implements Serializable {

    private int id;
    private String title;
    private String artist;
    private String description;
    private String[] images;
    private int year;
    private String address;
    private double rating;

    public Mural(int id, String title, String artist, String description, String[] images, int year, String address, double rating) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.description = description;
        this.images = images;
        this.year = year;
        this.address = address;
        this.rating = rating;
    }

    public Mural() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
