package com.example.android.tourguideapp;

import java.util.ArrayList;
import java.util.HashMap;

public class TouristAttractionManager {
    public TouristAttraction detailedTouristAttraction;
    private HashMap<AttractionType, ArrayList<TouristAttraction>> touristsAttractions;

    private static final TouristAttractionManager ourInstance = new TouristAttractionManager();

    public static TouristAttractionManager getInstance() {
        return ourInstance;
    }

    private TouristAttractionManager() {
        touristsAttractions = new HashMap<>();
        for (AttractionType at: AttractionType.values()) {
            touristsAttractions.put(at, new ArrayList<TouristAttraction>());
        }
        createAttractions();
    }

    public ArrayList<TouristAttraction> getTouristsAttractions(AttractionType type) {
        return touristsAttractions.get(type);
    }

    private void addAttraction(AttractionType type, String name, String description) {
        addAttraction(type, name, description, TouristAttraction.NO_IMAGE, TouristAttraction.NO_IMAGE,
        null, null, null);
    }

    private void addAttraction(AttractionType type, String name, String description,
                              int imageID, int smallImageID, String phone, String website, String location) {
        touristsAttractions.get(type).add(
                new TouristAttraction(type, name, description, imageID, smallImageID,
                phone, website, location));
    }

    public void addSavedTouristAttractions(TouristAttraction touristAttractionToSave) {
        touristAttractionToSave.setSaved(true);
        touristsAttractions.get(AttractionType.SAVED).add(touristAttractionToSave);
    }

    public void deleteSavedTouristAttractions(TouristAttraction touristAttractionToSave) {
        touristAttractionToSave.setSaved(false);
        touristsAttractions.get(AttractionType.SAVED).remove(touristAttractionToSave);
    }

    public void createAttractions() {
        // temp dummy data TODO
        addAttraction(AttractionType.SIGHTSEEING, "Main Square", "Biggest square in Europe",
                R.drawable.krakow_map, R.drawable.krakow_logo,  "444 555 666","www.rynek.pl" , "Krakow, Rynek  1");
        addAttraction(AttractionType.SIGHTSEEING, "Nowa Huta", "Post Industrial Area");
    }
}
