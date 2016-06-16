package com.rs.house1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyMap extends AppCompatActivity {

    static final LatLng TutorialsPoint = new LatLng(21, 57);
    private GoogleMap googleMap;
    String latitude,longitude;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_my_map);
        latitude=getIntent().getExtras().getString("lati");
        longitude=getIntent().getExtras().getString("longi");

//        sharedPreferences=getSharedPreferences("location",MODE_PRIVATE);
//        String newlat=sharedPreferences.getString("lat","");
//        String newlong=sharedPreferences.getString("lng","");

//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        editor.putString("address",address);
//        editor.putString("lat",String.valueOf(latitude));
//        editor.putString("lng",String.valueOf(longitude));
//
//        editor.commit();



        FragmentManager fragmentManager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);
        googleMap = mapFragment.getMap();

        try {
            final LatLng TutorialsPoint = new LatLng(Double.valueOf(latitude), Double.valueOf(longitude));
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            googleMap.addMarker(new MarkerOptions().position(TutorialsPoint).title("Address")).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.drop));
            googleMap.getUiSettings().setZoomGesturesEnabled(true);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(TutorialsPoint, 15);
            googleMap.animateCamera(cameraUpdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

//    public void onBackPressed() {
//
//        if (llShowView.getVisibility() == View.VISIBLE) {
//            llShowView.setVisibility(View.GONE);
//        } else {
//            getActivity().finish();
//        }
}
