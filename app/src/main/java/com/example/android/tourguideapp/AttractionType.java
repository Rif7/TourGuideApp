package com.example.android.tourguideapp;

import android.content.Context;

public enum AttractionType {
    SIGHTSEEING(R.string.SIGHTSEEING),
    HOTEL(R.string.HOTEL),
    RESTAURANT(R.string.RESTAURANT),
    MUSEUM(R.string.MUSEUM),
    SAVED(R.string.SAVED_PLACE);

    private int defaultSmallImageID;
    private int defaultColor;

    private final int name;

    AttractionType(int resID) {
        name = resID;
    }

    public String getName(Context context) {
        return context.getString(this.name);
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

    public static AttractionType getAttractionType(Context context, String name) {
        AttractionType result = AttractionType.SIGHTSEEING; // default
        for (AttractionType at: AttractionType.values()) {
            if (at.getName(context).equals(name)) {
                result = at;
            }
        }
        return result;
    }

}