package com.example.android.tourguideapp;

public enum AttractionType {
    SIGHTSEEING("SIGHTSEEING"),
    HOTEL("HOTEL"),
    RESTAURANT("RESTAURANT"),
    MUSEUM("MUSEUM"),
    SAVED("SAVED PLACE");

    private int defaultSmallImageID;
    private int defaultColor;

    private final String name;

    private AttractionType(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }

    static {
        SIGHTSEEING.defaultSmallImageID = R.drawable.ic_sightseeing;
        HOTEL.defaultSmallImageID = R.drawable.ic_hotel;
        RESTAURANT.defaultSmallImageID = R.drawable.ic_restaurant;
        MUSEUM.defaultSmallImageID = R.drawable.ic_museum;
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

    public int getDefaultSmallImageID() {
        return defaultSmallImageID;
    }

    public static AttractionType getAttractionType(String name) {
        AttractionType result = AttractionType.SIGHTSEEING; // default
        for (AttractionType at: AttractionType.values()) {
            if (at.name.equals(name)) {
                result = at;
            }
        }
        return result;
    }

}