package com.example.quiroz.googlemaps2;

import android.app.FragmentManager;
import android.app.MediaRouteButton;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;

import java.util.List;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final LatLng centro = new LatLng(-23.193163, -45.878246);
    private GoogleMap googlemap;
    private GoogleMap googlemap2;
    private Connection con = new Connection();
    private ConnectionPol conp = new ConnectionPol();
    private List<MarkerCons> markers;
    private List<MarkerPol> markersp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.inc_pol).setVisibility(View.INVISIBLE);
        findViewById(R.id.inc_cons).setVisibility(View.INVISIBLE);
        gerarmarkerCons();
        gerarmarkerPol();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    public void gerarmarkerCons(){

        googlemap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        googlemap.setMapType((GoogleMap.MAP_TYPE_NORMAL));
        googlemap.moveCamera(CameraUpdateFactory.newLatLngZoom(centro, 15));
        googlemap.setMyLocationEnabled(true);
        googlemap.getUiSettings().setZoomControlsEnabled(true);
        googlemap.getUiSettings().setMyLocationButtonEnabled(true);
        googlemap.getUiSettings().setCompassEnabled(true);
        googlemap.getUiSettings().setRotateGesturesEnabled(true);
        googlemap.getUiSettings().setZoomGesturesEnabled(true);

        try {
            this.markers = con.getData();

            for(MarkerCons marker:this.markers){
                googlemap.addMarker(new MarkerOptions().position(new LatLng(marker.getLat(),marker.getLon())).title(marker.getDesc()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void gerarmarkerPol(){

        googlemap2 = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map2)).getMap();
        googlemap2.setMapType((GoogleMap.MAP_TYPE_NORMAL));
        googlemap2.moveCamera(CameraUpdateFactory.newLatLngZoom(centro, 15));
        googlemap2.setMyLocationEnabled(true);
        googlemap2.getUiSettings().setZoomControlsEnabled(true);
        googlemap2.getUiSettings().setMyLocationButtonEnabled(true);
        googlemap2.getUiSettings().setCompassEnabled(true);
        googlemap2.getUiSettings().setRotateGesturesEnabled(true);
        googlemap2.getUiSettings().setZoomGesturesEnabled(true);

        try {
            this.markersp = conp.getData();

            for(MarkerPol marker:this.markersp){
                googlemap2.addMarker(new MarkerOptions().position(new LatLng(marker.getLat(),marker.getLon())).title(marker.getDesc()));
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public void animateCamera(View view) {
        if (googlemap.getMyLocation() != null)
            googlemap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(googlemap.getMyLocation().getLatitude(), googlemap.getMyLocation().getLongitude()), 15));
    }

    public void animateCameraP(View view) {
        if (googlemap2.getMyLocation() != null)
            googlemap2.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(googlemap.getMyLocation().getLatitude(), googlemap.getMyLocation().getLongitude()), 15));
    }


    public void buttonCons(View view) {
        findViewById(R.id.inc_tela).setVisibility(View.INVISIBLE);
        findViewById(R.id.inc_pol).setVisibility(View.INVISIBLE);
        findViewById(R.id.inc_cons).setVisibility(View.VISIBLE);

    }

    public void buttonPol(View view) {
        findViewById(R.id.inc_tela).setVisibility(View.INVISIBLE);
        findViewById(R.id.inc_pol).setVisibility(View.VISIBLE);
        findViewById(R.id.inc_cons).setVisibility(View.INVISIBLE);
    }

    public void voltarTela(View view) {
        findViewById(R.id.inc_tela).setVisibility(View.VISIBLE);
        findViewById(R.id.inc_pol).setVisibility(View.INVISIBLE);
        findViewById(R.id.inc_cons).setVisibility(View.INVISIBLE);
    }

    public void voltarTelaP(View view) {
        findViewById(R.id.inc_tela).setVisibility(View.VISIBLE);
        findViewById(R.id.inc_pol).setVisibility(View.INVISIBLE);
        findViewById(R.id.inc_cons).setVisibility(View.INVISIBLE);

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_inicio) {
            findViewById(R.id.inc_tela).setVisibility(View.VISIBLE);
            findViewById(R.id.inc_pol).setVisibility(View.INVISIBLE);
            findViewById(R.id.inc_cons).setVisibility(View.INVISIBLE);

        } else if (id == R.id.nav_cons) {
            findViewById(R.id.inc_tela).setVisibility(View.INVISIBLE);
            findViewById(R.id.inc_pol).setVisibility(View.INVISIBLE);
            findViewById(R.id.inc_cons).setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_pol) {
            findViewById(R.id.inc_tela).setVisibility(View.INVISIBLE);
            findViewById(R.id.inc_pol).setVisibility(View.VISIBLE);
            findViewById(R.id.inc_cons).setVisibility(View.INVISIBLE);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
