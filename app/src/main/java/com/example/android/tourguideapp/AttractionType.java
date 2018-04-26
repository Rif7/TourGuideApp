package com.example.android.tourguideapp;

public enum AttractionType {
    SIGHTSEEING("SIGHTSEEING"),
    HOTEL("HOTEL"),
    RESTAURANT("RESTAURANT"),
    MUSEUM("MUSEUM"),
    SAVED("SAVED PLACE");

    private int defaultImageID;
    private int defaultColor;

    private final String name;

    private AttractionType(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }

    static {
        SIGHTSEEING.defaultImageID = R.drawable.ic_sightseeing;
        HOTEL.defaultImageID = R.drawable.ic_hotel;
        RESTAURANT.defaultImageID = R.drawable.ic_restaurant;
        MUSEUM.defaultImageID = R.drawable.ic_museum;
    }

    static {
        SIGHTSEEING.defaultColor = R.color.sightseeing_background;
        HOTEL.defaultColor = R.color.hotel_background;
        RESTAURANT.defaultColor = R.color.restaurant_background;
        MUSEUM.defaultColor = R.color.museum_background;
    }

    public int getDefaultColor() {
        return defaultColor;
    }

    public int getDefaultImageID() {
        return defaultImageID;
    }

}