package com.example.android.tourguideapp;

public enum AttractionType {
    SIGHTSEEING, HOTEL, RESTAURANT, MUSEUM;

    private int defaultImageID;

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