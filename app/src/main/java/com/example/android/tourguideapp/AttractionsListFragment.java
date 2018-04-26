package com.example.android.tourguideapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


public abstract class AttractionsListFragment extends android.support.v4.app.Fragment  implements ActionBarTextChangeable {
    protected AttractionType attractionType;
    private OnFragmentInteractionListener mListener;
    protected static String toolbarName;

    public AttractionsListFragment() {
        // Required empty public constructor
    }


    protected abstract void setAttractionType();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setAttractionType();
        View rootView = inflater.inflate(R.layout.fragment_attractions, container, false);

        final TouristAttractionAdapter adapter = new TouristAttractionAdapter(getActivity(),
                TouristAttractionManager.getInstance().getTouristsAttractions(attractionType));

        ListView listView = (ListView) rootView.findViewById(R.id.attractions_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TouristAttractionManager.getInstance().detailedTouristAttraction = adapter.getItem(position);
                Intent changeActivityIntent = new Intent(getActivity(), TouristAttractionDetailsActivity.class);
                getActivity().startActivity(changeActivityIntent);
            }
        });

        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
