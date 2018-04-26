package com.example.android.tourguideapp;

import android.support.v7.app.ActionBar;

public class HotelsListFragment extends AttractionsListFragment {
    @Override
    protected void setAttractionType() {
        attractionType = AttractionType.HOTEL;
    }

    @Override
    public void changeActionBarText(ActionBar actionBar) {
        actionBar.setTitle("Finest places to sleep");
    }
}
