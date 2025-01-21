package com.jyotsna.googlemapsapi

import android.location.Geocoder
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.Locale

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var searchButton: Button
    private lateinit var locationEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        locationEditText = findViewById(R.id.etLocation)
        searchButton = findViewById(R.id.btnSearch)

        // Initialize map
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Set up search button click listener
        searchButton.setOnClickListener {
            val location = locationEditText.text.toString()
            searchLocation(location)
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Set default location to New York
        val newYork = LatLng(40.7128, -74.0060)
        googleMap.addMarker(MarkerOptions().position(newYork).title("New York"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newYork, 10f))
    }

    private fun searchLocation(location: String) {
        if (location.isBlank()) {
            Toast.makeText(this, "Please enter a valid location", Toast.LENGTH_SHORT).show()
            return
        }

        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val addressList = geocoder.getFromLocationName(location, 1)

            if (addressList.isNullOrEmpty()) {
                Toast.makeText(this, "Location not found. Please try again.", Toast.LENGTH_SHORT).show()
            } else {
                val address = addressList[0]
                val latLng = LatLng(address.latitude, address.longitude)

                // Add marker and move camera
                googleMap.clear() // Clear previous markers
                googleMap.addMarker(MarkerOptions().position(latLng).title(location))
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error fetching location: ${e.message}", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
}
