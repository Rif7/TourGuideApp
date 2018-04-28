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

    public void createAttractions(Context context) throws XmlPullParserException, IOException {
        if (isLoaded) {
            return;
        } else {
            isLoaded = true;
        }
        final String LOCATION = context.getString(R.string.xml_location);
        final String WEBSITE = context.getString(R.string.xml_website);
        final String PHONE_NUMBER = context.getString(R.string.xml_phone_number);
        final String DRAWABLE = context.getString(R.string.drawable);
        final String SMALL_IMAGE_NAME = context.getString(R.string.xml_small_image_name);
        final String IMAGE_NAME = context.getString(R.string.xml_image_name);
        final String DESCRIPTION = context.getString(R.string.xml_description);
        final String NAME = context.getString(R.string.xml_name);
        final String TYPE = context.getString(R.string.xml_type);
        final String LIST = context.getString(R.string.xml_list);
        final String ENTRY = context.getString(R.string.xml_entry);
        

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
            if (eventType == XmlPullParser.START_TAG && parser.getName().equals(LIST)) {
                type = AttractionType.getAttractionType(context, parser.getAttributeValue(null, TYPE));
            }

            // Begin of the current attraction data
            if (eventType == XmlPullParser.START_TAG && parser.getName().equals(ENTRY)) {
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
                if (parser.getName().equals(NAME)){
                    name = parser.nextText();
                } else if (parser.getName().equals(DESCRIPTION)) {
                    description = parser.nextText();
                } else if(parser.getName().equals(IMAGE_NAME)) {
                    imageID = context.getResources().getIdentifier(parser.nextText(),
                            DRAWABLE, context.getPackageName());
                } else if(parser.getName().equals(SMALL_IMAGE_NAME)) {
                    smallImageID = context.getResources().getIdentifier(parser.nextText(),
                            DRAWABLE, context.getPackageName());
                } else if(parser.getName().equals(PHONE_NUMBER)) {
                    phone = parser.nextText();
                } else if(parser.getName().equals(WEBSITE)) {
                    website = parser.nextText();
                } else if(parser.getName().equals(LOCATION)) {
                    location = parser.nextText();
                }
            }

            // End of the current attraction data
            if (eventType == XmlPullParser.END_TAG && parser.getName().equals(ENTRY)) {
                addAttraction(type, name, description, imageID, smallImageID,
                        phone, website, location);
            }

            if (eventType == XmlPullParser.END_TAG && parser.getName().equals(LIST)) {
                // Begin of the list attractions for current type
                type = null;
            }

            eventType = parser.next();
        }
    }


}
