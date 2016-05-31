package de.rwth_aachen.engel.beaconsforproduction;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.estimote.sdk.*;
import com.estimote.sdk.eddystone.Eddystone;

import java.util.List;

public class Activity_Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    //Beacon Specific
    private BeaconManager beaconManager;
    private String scanId;




    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment mainFragment = new fragment_main();
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentMainContainer,mainFragment);
        fragmentTransaction.commit();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if(toolbar != null) {
            toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            toggle.syncState();
            assert drawer != null;
            drawer.addDrawerListener(toggle);
            final android.support.design.widget.NavigationView mDrawerLayout = (NavigationView) findViewById(R.id.nav_view);
            assert mDrawerLayout != null;
            mDrawerLayout.setNavigationItemSelectedListener(this);
            getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    if (getSupportFragmentManager().getBackStackEntryCount() > 0 && getSupportActionBar()!=null) {
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // show back button
                        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onBackPressed();
                            }
                        });
                    } else {
                        //show hamburger
                        if(getSupportActionBar()!=null) {
                            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                            toggle.syncState();
                            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    drawer.openDrawer(GravityCompat.START);
                                }
                            });
                        }
                    }
                }
            });}
        beaconManager = new BeaconManager(getApplicationContext());
        beaconManager.setEddystoneListener(new BeaconManager.EddystoneListener() {
            public void onEddystonesFound(List<Eddystone> eddystones) {
                Log.d("Eddystone", "Nearby Eddystone beacons: " + eddystones);
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override public void onServiceReady() {
                scanId = beaconManager.startEddystoneScanning();
            }
        });
    }
    @Override
    public void onResume(){
        super.onResume();
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new BeaconApiDownloader().execute();
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(getSupportFragmentManager().getBackStackEntryCount()>0)
                getSupportFragmentManager().popBackStack();
            if(getSupportFragmentManager().getBackStackEntryCount()==0){
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Intent intent = null;
        switch (id){
            case R.id.nav_settings:
                intent=new Intent(this, Activity_Settings.class);
                break;
            case R.id.nav_user_settings:
                intent=new Intent(this, Activity_UserSettings.class);
                break;
            case R.id.nav_logout:
                onBackPressed();
                break;
            default:
                break;
        }
        if(intent!=null)
            startActivity(intent);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void setUpArrow(String title){
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
