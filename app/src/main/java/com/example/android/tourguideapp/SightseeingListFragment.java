package com.example.android.tourguideapp;

import android.support.v7.app.ActionBar;

public class SightseeingListFragment extends AttractionsListFragment {
    @Override
    protected void setAttractionType() {
        attractionType = AttractionType.SIGHTSEEING;
    }

    @Override
    public void changeActionBarText(ActionBar actionBar) {
        actionBar.setTitle(R.string.amazing_spots);
    }
}
