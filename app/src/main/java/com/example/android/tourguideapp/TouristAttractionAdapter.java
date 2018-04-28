package com.example.android.tourguideapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TouristAttractionAdapter extends ArrayAdapter<TouristAttraction> {
    private Context context;

    TouristAttractionAdapter(Context context, ArrayList<TouristAttraction> touristAtracions) {
        super(context, 0, touristAtracions);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.tourist_attraction_item, parent, false);
        }

        TouristAttraction touristAttraction = getItem(position);

        ImageView picture = listItemView.findViewById(R.id.ta_item_image);
        picture.setImageResource(touristAttraction.getSmallImageID());

        TextView attractionName = listItemView.findViewById(R.id.ta_item_name);
        attractionName.setText(touristAttraction.getName());

        listItemView.setBackgroundColor(touristAttraction.getType().getDefaultColor());

        listItemView.setBackgroundColor(ContextCompat.getColor(context, touristAttraction.getType().getDefaultColor()));

        return listItemView;
    }

}
