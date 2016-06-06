package de.rwth_aachen.engel.beaconsforproduction;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class Activity_Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    public static final String INTENT_CLASS = "intent_class";
    public static final String INDEX = "intent_index";
    public static final String[] JSONKEYS = {"m_Item1","m_Item2","m_Item3"};
    //Beacon Specific
    private BeaconManager beaconManager;
    private String scanId;
    List<Beacon> machineList;
    List<Beacon> orderList;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    DrawerLayout drawer;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.coordinatorLayout);
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
            });
        }
        beaconManager = new BeaconManager(getApplicationContext());
        beaconManager.setNearableListener(new BeaconManager.NearableListener() {
            public void onNearablesDiscovered(List<Nearable> nearables) {
                Log.d("Nearable", "Nearby 'Nearable' beacons: " + nearables);
                Snackbar.make(view,"Nearby 'Nearable' beacons: " + nearables, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override public void onServiceReady() {
                scanId = beaconManager.startNearableDiscovery();
            }
        });
    }
    @Override
    public void onResume(){
        super.onResume();
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new BeaconApiDownloader(this).execute();
        }
        TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs);
        assert tabLayout != null;
        tabLayout.setVisibility(View.GONE);
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
        fm.beginTransaction().commit();
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
            setTitle(title);
        }
    }
    public List <Beacon> getMachineList(){
        if(machineList == null){
            try {
                setItems(new BeaconApiDownloader(this).execute().get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return machineList;
    }
    public List<Beacon> getOrderList(){
        if(orderList == null){
            try {
                setItems(new BeaconApiDownloader(this).execute().get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return orderList;
    }
    public void setItems(String result){
        if(result!=null){
            orderList = new ArrayList<>();
            machineList = new ArrayList<>();
            JSONArray jArray;
            JSONObject fullItem;
            try {
                jArray = new JSONArray(result);
                for(int i = 0; i < jArray.length(); i++){
                    fullItem = jArray.optJSONObject(i);
                    if(fullItem.optString(JSONKEYS[1]).equals("Machine") || fullItem.optString(JSONKEYS[1]).equals("Job")) {
                        Beacon beacon = parseBeacon(fullItem.optJSONObject(JSONKEYS[2]));
                        beacon.setBeacon(fullItem.optJSONObject(JSONKEYS[0]).optString("name"));
                        if (fullItem.optString(JSONKEYS[1]).equals("Machine")) {
                            beacon.setKind(false);
                            machineList.add(beacon);
                        } else {
                            beacon.setKind(true);
                            orderList.add(beacon);
                        }
                    }
                }
            }catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
    }
    public Beacon parseBeacon(JSONObject jsonString){
        Beacon item = new Beacon();
        item.setName(jsonString.optString("name"));
        item.setID(jsonString.optString("id"));
        item.setDescription(jsonString.optString("description"));
        return item;
    }
    public void addOrder(final Beacon order){
        orderList.add(order);
        Snackbar.make(findViewById(android.R.id.content),order.getName()+" "+getString(R.string.saved),Snackbar.LENGTH_LONG).setAction(R.string.undo,new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteOrder(order);
                Snackbar.make(view, R.string.undone, Snackbar.LENGTH_SHORT).show();
            }
        }).show();
    }
    public void deleteOrder(Beacon order){
        orderList.remove(order);
        fragment_orders frag = (fragment_orders)getSupportFragmentManager().findFragmentByTag("FRAGMENT_ORDERS");
        if (frag != null && frag.isVisible()) {
            frag.getAdapter().removeItem(order);
        }
    }
    public void addMachine(final Beacon machine){
        machineList.add(machine);
        Snackbar.make(findViewById(android.R.id.content),machine.getName()+" "+getString(R.string.saved),Snackbar.LENGTH_LONG).setAction(R.string.undo,new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMachine(machine);
                Snackbar.make(view, R.string.undone, Snackbar.LENGTH_SHORT).show();
            }
        }).show();
    }
    public void deleteMachine(Beacon machine){
        machineList.remove(machine);
        fragment_machines frag = (fragment_machines)getSupportFragmentManager().findFragmentByTag("FRAGMENT_MACHINES");
        if (frag != null && frag.isVisible()) {
            frag.getAdapter().removeItem(machine);
        }
    }
    public void newBeacon(View v){
        Fragment fragment = new fragment_newBeacon();
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentMainContainer,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
