package com.jhr.abdallahsarayrah.googlemapstutorial;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

        Geocoder gc = new Geocoder(this);
        try {
            List<Address> list = gc.getFromLocationName("Amman", 1);
            Address address = list.get(0);
            String locality = address.getLocality();
            Toast.makeText(this, locality, Toast.LENGTH_SHORT).show();

            LatLng ll = new LatLng(address.getLatitude(), address.getLongitude());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
