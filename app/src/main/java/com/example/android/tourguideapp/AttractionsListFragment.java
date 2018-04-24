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


public class AttractionsListFragment extends android.support.v4.app.Fragment {
    public static AttractionType attractionType;
    private OnFragmentInteractionListener mListener;

    public AttractionsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_attractions, container, false);

        final TouristAttractionAdapter adapter = new TouristAttractionAdapter(getActivity(),
                TouristAttractionManager.getInstance().getTouristsAttrations(AttractionType.SIGHTSEEING));

        TextView listName = (TextView) rootView.findViewById(R.id.list_name);
        listName.setText(attractionType.toString());

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



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
