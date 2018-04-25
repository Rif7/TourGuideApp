package com.example.android.tourguideapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TouristAttractionDetailsActivity extends AppCompatActivity {
    TouristAttraction touristAttraction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_attraction_details);
        touristAttraction = TouristAttractionManager.getInstance().detailedTouristAttraction;

        Toolbar toolbar = findViewById(R.id.details_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(TouristAttractionManager.getInstance().detailedTouristAttraction.getName());
        actionbar.setDisplayHomeAsUpEnabled(true);

        TextView textDetails = (TextView) findViewById(R.id.details_description) ;
        textDetails.setText(touristAttraction.getDescription());


        TouristAttraction touristAttraction = TouristAttractionManager.getInstance().detailedTouristAttraction;
        if (touristAttraction.hasImageID()) {
            ImageView photo = (ImageView) findViewById(R.id.details_photo);
            photo.setImageResource(touristAttraction.getImageID());
            photo.setVisibility(View.VISIBLE);
        }
        if (touristAttraction.hasPhone()) {
            TextView text = (TextView) findViewById(R.id.details_phone_text) ;
            text.setText(touristAttraction.getPhone());
            View item = (View) findViewById(R.id.details_phone);
            item.setVisibility(View.VISIBLE);
        }

        if (touristAttraction.hasWebsite()) {
            TextView text = (TextView) findViewById(R.id.details_website_text) ;
            text.setText(touristAttraction.getWebsite());
            View item = (View) findViewById(R.id.details_website);
            item.setVisibility(View.VISIBLE);
        }

        if (touristAttraction.hasLocation()) {
            TextView text = (TextView) findViewById(R.id.details_location_text) ;
            text.setText(touristAttraction.getLocation());
            View item = (View) findViewById(R.id.details_location);
            item.setVisibility(View.VISIBLE);
        }

    }
}
