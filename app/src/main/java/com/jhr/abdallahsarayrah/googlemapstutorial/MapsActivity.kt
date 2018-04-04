package com.jhr.abdallahsarayrah.googlemapstutorial

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var marker: Marker? = null

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        button_search.setOnClickListener {
            val gc = Geocoder(this)
            val list = gc.getFromLocationName(editText.text.toString(), 1)
            var address: Address?
            if (list.isNotEmpty()) {
                address = list[0]

                val ll = LatLng(address?.latitude!!, address?.longitude)
                if (marker != null) marker!!.remove()
                marker = mMap.addMarker(MarkerOptions().position(ll).title(address.locality))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 8f))
            } else Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when {
            item?.itemId == R.id.normal -> mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            item?.itemId == R.id.terrain -> mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            item?.itemId == R.id.satellite -> mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            item?.itemId == R.id.hybrid -> mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

        mMap.isMyLocationEnabled = true
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
