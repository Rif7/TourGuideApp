package com.example.android.tourguideapp;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class TouristAttractionManager {
    public TouristAttraction detailedTouristAttraction;
    private HashMap<AttractionType, ArrayList<TouristAttraction>> touristsAttractions;

    private static final TouristAttractionManager ourInstance = new TouristAttractionManager();
    private boolean isLoaded = false;

    public static TouristAttractionManager getInstance() {
        return ourInstance;
    }

    private TouristAttractionManager() {
        touristsAttractions = new HashMap<>();
        for (AttractionType at: AttractionType.values()) {
            touristsAttractions.put(at, new ArrayList<TouristAttraction>());
        }
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

    private void addAttraction(String attractionTypeName, String name, String description,
                               int imageID, int smallImageID, String phone, String website, String location) {
        AttractionType type = AttractionType.getAttractionType(attractionTypeName);
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

    public void createAttractions(Context context) throws XmlPullParserException, IOException {
        if (isLoaded) {
            return;
        } else {
            isLoaded = true;
        }

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser parser = factory.newPullParser();
        InputStream attractionsFile = context.getResources().openRawResource(R.raw.attractions);
        parser.setInput(attractionsFile, null);
        int eventType = parser.getEventType();

        // Parameters to constructor
        // Obligatory
        AttractionType type = null;
        String name = null;
        String description = null;

        // Optional
        int imageID = TouristAttraction.NO_IMAGE;
        int smallImageID = TouristAttraction.NO_IMAGE;
        String phone= null;
        String website= null;
        String location = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            // Begin of the list attractions for current type
            if (eventType == XmlPullParser.START_TAG && parser.getName().equals("list")) {
                type = AttractionType.getAttractionType(parser.getAttributeValue(null, "type"));
            }

            // Begin of the current attraction data
            if (eventType == XmlPullParser.START_TAG && parser.getName().equals("entry")) {
                name = null;
                description = null;
                imageID = TouristAttraction.NO_IMAGE;
                smallImageID = TouristAttraction.NO_IMAGE;
                phone= null;
                website= null;
                location = null;
            }

            // Data fields
            if (eventType == XmlPullParser.START_TAG) {
                switch (parser.getName()) {
                    case "name":
                        name = parser.nextText();
                        break;
                    case "description":
                        description = parser.nextText();
                        break;
                    case "image_name":
                        imageID = context.getResources().getIdentifier(parser.nextText(),
                                "drawable", context.getPackageName());
                        break;
                    case "small_image_name":
                        smallImageID = context.getResources().getIdentifier(parser.nextText(),
                                "drawable", context.getPackageName());
                        break;
                    case "phone_number":
                        phone = parser.nextText();
                        break;
                    case "website":
                        website = parser.nextText();
                        break;
                    case "location":
                        location = parser.nextText();
                        break;
                }
            }

            // End of the current attraction data
            if (eventType == XmlPullParser.END_TAG && parser.getName().equals("entry")) {
                addAttraction(type, name, description, imageID, smallImageID,
                        phone, website, location);
            }

            if (eventType == XmlPullParser.END_TAG && parser.getName().equals("list")) {
                // Begin of the list attractions for current type
                type = null;
            }

            eventType = parser.next();
        }
    }


}
