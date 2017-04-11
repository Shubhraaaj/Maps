package com.example.shubhraj.maps;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity
{
    private TextView addr, cty, ste, ctry, pCode, name;
    private double latitude, longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Geocoder geocoder;
        Bundle b = getIntent().getExtras();
        latitude = b.getDouble("LAT");
        longitude = b.getDouble("LNG");
        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();
                ((TextView) findViewById(R.id.address)).setText(address);
                ((TextView) findViewById(R.id.city)).setText(city);
                ((TextView) findViewById(R.id.state)).setText(state);
                ((TextView)findViewById(R.id.latit)).setText(String.valueOf(latitude));
                ((TextView)findViewById(R.id.longig)).setText(String.valueOf(longitude));
                ((TextView) findViewById(R.id.country)).setText(country);
                ((TextView) findViewById(R.id.postal)).setText(postalCode);
                ((TextView) findViewById(R.id.name)).setText(knownName);
        }
        catch (Exception e)
            {
            e.printStackTrace();
        }
    }



}
