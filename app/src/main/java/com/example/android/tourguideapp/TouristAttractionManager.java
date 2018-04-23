package com.example.android.tourguideapp;

import java.util.ArrayList;
import java.util.HashMap;

public class TouristAttractionManager {
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
    }

    public ArrayList<TouristAttraction> getTouristsAttrations(AttractionType type) {
        return touristsAttractions.get(type);
    }

    public void addAttraction(AttractionType type, String name, String description) {
        addAttraction(type, name, description, TouristAttraction.NO_IMAGE,
        null, null, null);
    }

    private void addAttraction(AttractionType type, String name, String description,
                              int imageID, String phone, String website, String location) {
        touristsAttractions.get(type).add(
                new TouristAttraction(type, name, description, imageID,
                phone, website, location));
    }

    public ArrayList<TouristAttraction> getSavedAttractions() {
        ArrayList<TouristAttraction> saved = new ArrayList<>();
        for (AttractionType type : touristsAttractions.keySet()) {
            for (TouristAttraction ta: touristsAttractions.get(type)) {
                if (ta.isSaved()) saved.add(ta);
            }
        }
        return saved;
    }

    public void createAttrations() {
        // temp dummy data TODO
        addAttraction(AttractionType.SIGHTSEEING, "Main Square", "Biggest square in Europe");
    }
}
