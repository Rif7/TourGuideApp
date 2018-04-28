package com.example.android.tourguideapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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


        final TouristAttraction touristAttraction = TouristAttractionManager.getInstance().detailedTouristAttraction;
        if (touristAttraction.hasSmallImageID()) {
            ImageView photo = (ImageView) findViewById(R.id.details_photo);
            photo.setImageResource(touristAttraction.getImageID());
            photo.setVisibility(View.VISIBLE);
        }
        if (touristAttraction.hasPhone()) {
            TextView text = (TextView) findViewById(R.id.details_phone_text) ;
            text.setText(touristAttraction.getPhone());
            View item = (View) findViewById(R.id.details_phone);
            item.setVisibility(View.VISIBLE);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Prepare to dial
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + touristAttraction.getPhone()));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });
        }

        if (touristAttraction.hasWebsite()) {
            TextView text = (TextView) findViewById(R.id.details_website_text) ;
            text.setText(touristAttraction.getWebsite());
            View item = (View) findViewById(R.id.details_website);
            item.setVisibility(View.VISIBLE);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    String link = touristAttraction.getWebsite();
                    if (!link.startsWith("http://")) link = "http://" + link;
                    intent.setData(Uri.parse(link));
                    startActivity(intent);
                }
            });
        }

        if (touristAttraction.hasLocation()) {
            TextView text = (TextView) findViewById(R.id.details_location_text) ;
            text.setText(touristAttraction.getLocation());
            View item = (View) findViewById(R.id.details_location);
            item.setVisibility(View.VISIBLE);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://maps.google.com/?q=" + touristAttraction.getLocation()));
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_action_bar, menu);
        MenuItem save = menu.findItem(R.id.action_save);
        if (TouristAttractionManager.getInstance().detailedTouristAttraction.isSaved()) {
            save.setIcon(R.drawable.ic_star_saved);
        } else {
            save.setIcon(R.drawable.ic_star_unsaved);
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                if (touristAttraction.isSaved()) {
                    item.setIcon(R.drawable.ic_star_unsaved);
                    TouristAttractionManager.getInstance().deleteSavedTouristAttractions(touristAttraction);
                } else {
                    item.setIcon(R.drawable.ic_star_saved);
                    TouristAttractionManager.getInstance().addSavedTouristAttractions(touristAttraction);
                }
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
