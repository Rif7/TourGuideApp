package com.example.android.tourguideapp;


public class TouristAttraction {
    public static final int NO_IMAGE = -1;

    // Tourist Attraction types
    public static final int NOT_DEFINED = 0;
    public static final int HIGHLIGHT = 1;
    public static final int HOTEL = 2;
    public static final int RESTAURANT = 3;
    public static final int MUSEUM = 4;

    // Obligatory
    private int type;
    private String name;
    private String descripion;
    private boolean isSaved;

    // Optional
    private int imageID;
    private String phone;
    private String website;
    private String location;

    TouristAttraction(int type, String name, String descripion) {
        this.type = type;
        this.name = name;
        this.descripion = descripion;
        this.isSaved = false;
    }

    TouristAttraction(int type, String name, String descripion,
                      int imageID, String phone, String website, String location) {
        this(type, name, descripion);
        this.imageID = imageID;
        this.phone = phone;
        this.website = website;
        this.location = location;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescripion() {
        return descripion;
    }

    public int getImageID() {
        return imageID;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getLocation() {
        return location;
    }

    public boolean hasImageID() {
        return imageID != NO_IMAGE;
    }

    public boolean hasPhone() {
        return phone != null;
    }

    public boolean hasWebsite() {
        return website != null;
    }

    public boolean hasLocation() {
        return location != null;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

}
