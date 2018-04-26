package com.example.android.tourguideapp;

import android.support.v7.app.ActionBar;

public class SavedPlacesListFragment extends AttractionsListFragment {
    @Override
    protected void setAttractionType() {
        attractionType = AttractionType.SAVED;
    }

    @Override
    public void changeActionBarText(ActionBar actionBar) {
        actionBar.setTitle("Your saved places");
    }
}
