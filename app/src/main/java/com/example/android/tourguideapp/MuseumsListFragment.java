package com.example.android.tourguideapp;

import android.support.v7.app.ActionBar;

public class MuseumsListFragment extends AttractionsListFragment {

    @Override
    protected void setAttractionType() {
        attractionType = AttractionType.MUSEUM;
    }

    @Override
    public void changeActionBarText(ActionBar actionBar) {
        actionBar.setTitle(R.string.the_best_museums);
    }
}
