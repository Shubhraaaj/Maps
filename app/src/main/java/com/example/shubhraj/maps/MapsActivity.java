package com.example.shubhraj.maps;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button plus, minus;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    //private String username;
    private MapsActivity mContext;
    //private Users user1, user2, user3, user4;
    private Marker marker1, marker2, marker3, marker4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
            }
        });

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");
        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                Users users = dataSnapshot.getValue(Users.class);
                double lat1 = users.getLatitude();
                double lng1 = Double.parseDouble(users.getLongitude());
                LatLng latLng = new LatLng(lat1, lng1);
                marker1 = mMap.addMarker(new MarkerOptions().position(latLng).title(users.getDescription()));
                marker1.setTag(0);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Users users = dataSnapshot.getValue(Users.class);
                double lat1 = users.getLatitude();
                double lng1 = Double.parseDouble(users.getLongitude());
                LatLng latLng = new LatLng(lat1, lng1);
                mMap.addMarker(new MarkerOptions().position(latLng).title(users.getDescription()));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        /*databaseReference = database.getReference("Sagar");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users users = dataSnapshot.getValue(Users.class);
                double lat1 = Double.parseDouble(users.getLatitude());
                double lng1 = Double.parseDouble(users.getLongitude());
                LatLng latLng = new LatLng(lat1, lng1);
                marker2 = mMap.addMarker(new MarkerOptions().position(latLng).title(users.getDescription()));
                marker2.setTag(0);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        databaseReference = database.getReference("Saket");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users users = dataSnapshot.getValue(Users.class);
                double lat1 = Double.parseDouble(users.getLatitude());
                double lng1 = Double.parseDouble(users.getLongitude());
                LatLng latLng = new LatLng(lat1, lng1);
                marker3 = mMap.addMarker(new MarkerOptions().position(latLng).title(users.getDescription()));
                marker3.setTag(0);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        databaseReference = database.getReference("Shubhraj");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users users = dataSnapshot.getValue(Users.class);
                double lat1 = Double.parseDouble(users.getLatitude());
                double lng1 = Double.parseDouble(users.getLongitude());
                LatLng latLng = new LatLng(lat1, lng1);
                marker4 = mMap.addMarker(new MarkerOptions().position(latLng).title(users.getDescription()));
                marker4.setTag(0);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        */
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

     @Override
    public void onMapReady(GoogleMap googleMap)
     {
         mMap = googleMap;
         mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
             @Override
             public boolean onMarkerClick(Marker marker) {
                 LatLng pos1 = marker.getPosition();
                 double lat = pos1.latitude;
                 double lng = pos1.longitude;
                 Intent intent = new Intent(MapsActivity.this,DetailsActivity.class);
                 Bundle b = new Bundle();
                 b.putDouble("LAT",lat);
                 b.putDouble("LNG", lng);
                 intent.putExtras(b);
                 startActivity(intent);
                 return true;
             }
         });
         if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"Please give Permissions",Toast.LENGTH_SHORT);
            return;
        }
        mMap.setMyLocationEnabled(true);
    }
}