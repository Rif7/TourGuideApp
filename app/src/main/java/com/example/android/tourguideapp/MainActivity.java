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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        // Setting Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle(R.string.app_name);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black); // TODO

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragment == null) {
            // Adding initial fragment
            fragmentManager.beginTransaction().add(R.id.main_fragment,
                    new GeneralInformationFragment()).commit();
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
                AttractionsListFragment.attractionType = AttractionType.SIGHTSEEING;
                fragmentClass = AttractionsListFragment.class;
                break;
            case R.id.mnu_hotels:
                AttractionsListFragment.attractionType = AttractionType.HOTEL;
                fragmentClass = AttractionsListFragment.class;
                break;
            case R.id.mnu_museums:
                AttractionsListFragment.attractionType = AttractionType.MUSEUM;
                fragmentClass = AttractionsListFragment.class;
                break;
            case R.id.mnu_restaurants:
                AttractionsListFragment.attractionType = AttractionType.RESTAURANT;
                fragmentClass = AttractionsListFragment.class;
                break;
            case R.id.mnu_saved_places:
                AttractionsListFragment.attractionType = AttractionType.SAVED;
                fragmentClass = AttractionsListFragment.class;
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
