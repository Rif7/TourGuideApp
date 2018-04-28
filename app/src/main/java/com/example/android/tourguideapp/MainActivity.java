package com.example.android.tourguideapp;


import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements AttractionsListFragment.OnFragmentInteractionListener,
        GeneralInformationFragment.OnFragmentInteractionListener {
    private DrawerLayout mDrawerLayout;
    private static Fragment fragment = null;
    private ActionBar actionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        try {
             TouristAttractionManager.getInstance().createAttractions(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Setting Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black); // TODO

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragment == null) {
            // Adding initial fragment
            fragment = new GeneralInformationFragment();
            fragmentManager.beginTransaction().add(R.id.main_fragment, fragment).commit();
        } else {
            //Restore previous fragment
            Fragment initFragment = null;
            try {
                initFragment = (Fragment) fragment.getClass().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            fragmentManager.beginTransaction().add(R.id.main_fragment,
                    initFragment).commit();
        }
        ((ActionBarTextChangeable) fragment).changeActionBarText(actionbar);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    selectDrawerItem(menuItem);
                    return true;
                }
            });

    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.mnu_general_information:
                fragmentClass = GeneralInformationFragment.class;
                break;
            case R.id.mnu_sightseeing:
                fragmentClass = SightseeingListFragment.class;
                break;
            case R.id.mnu_hotels:
                fragmentClass = HotelsListFragment.class;
                break;
            case R.id.mnu_museums:
                fragmentClass = MuseumsListFragment.class;
                break;
            case R.id.mnu_restaurants:
                fragmentClass = RestaurantsListFragment.class;
                break;
            case R.id.mnu_saved_places:
                fragmentClass = SavedPlacesListFragment.class;
                break;
            default:
                fragmentClass = GeneralInformationFragment.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, fragment).commit();
        ((ActionBarTextChangeable) fragment).changeActionBarText(actionbar);


        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawerLayout.closeDrawers();
        getApplicationContext();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
