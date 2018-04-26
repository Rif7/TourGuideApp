package com.example.android.tourguideapp;

public class SavedPlacesListFragment extends AttractionsListFragment {
    @Override
    protected void setAttractionType() {
        attractionType = AttractionType.SAVED;
    }
}
