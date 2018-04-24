package com.example.android.tourguideapp;

public enum AttractionType {
    SIGHTSEEING("SIGHTSEEING"),
    HOTEL("HOTEL"),
    RESTAURANT("RESTAURANT"),
    MUSEUM("MUSEUM");

    private int defaultImageID;

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

    public int getDefaultImageID() {
        return defaultImageID;
    }

}