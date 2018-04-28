package com.example.android.tourguideapp;

import android.support.v7.app.ActionBar;

public class RestaurantsListFragment extends AttractionsListFragment {
    @Override
    protected void setAttractionType() {
        attractionType = AttractionType.RESTAURANT;
    }

    @Override
    public void changeActionBarText(ActionBar actionBar) {
        actionBar.setTitle(R.string.fancy_restaurants);
    }
}
