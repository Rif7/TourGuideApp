package com.example.android.tourguideapp;


public class TouristAttraction {
    public static final int NO_IMAGE = -1;

    // Obligatory
    private AttractionType type;
    private String name;
    private String description;
    private boolean isSaved;

    // Optional
    private int imageID;
    private String phone;
    private String website;
    private String location;

    TouristAttraction(AttractionType type, String name, String description,
                      int imageID, String phone, String website, String location) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.isSaved = false;
        this.imageID = imageID;
        this.phone = phone;
        this.website = website;
        this.location = location;
    }

    public AttractionType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageID() {
        if (imageID != NO_IMAGE) {
            return imageID;
        } else {
            return type.getDefaultImageID();
        }

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
