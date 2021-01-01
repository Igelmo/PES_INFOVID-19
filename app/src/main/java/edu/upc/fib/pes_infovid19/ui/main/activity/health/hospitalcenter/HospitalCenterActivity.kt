 package edu.upc.fib.pes_infovid19.ui.main.activity.health.hospitalcenter

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.GeoApiContext
import com.google.maps.PlacesApi
import com.google.maps.model.LatLng
import com.google.maps.model.PlaceType
import com.google.maps.model.PlacesSearchResponse
import com.google.maps.model.RankBy
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_hospital_center.*
import com.google.android.gms.maps.model.LatLng as GmsLatLng


 class HospitalCenterActivity : AppCompatActivity(), OnMapReadyCallback {

     private lateinit var currentLocation: Location
     private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
     private val permissionCode = 101

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_hospital_center)
         setSupportActionBar(toolbarCentreHospitalari)
         supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        fetchLocation()
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), permissionCode
            )
            return
        }
        val task = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener { location ->
            if (location != null) {
                currentLocation = location
                Toast.makeText(
                    applicationContext, currentLocation.latitude.toString() + "" +
                            currentLocation.longitude, Toast.LENGTH_SHORT
                ).show()
                val supportMapFragment = (supportFragmentManager.findFragmentById(R.id.map) as
                        SupportMapFragment?)!!
                supportMapFragment.getMapAsync(this)
            }
        }
    }

     private fun nearbySearch(): PlacesSearchResponse {

         val searchContext = GeoApiContext.Builder().apiKey("AIzaSyBrZKY5DQjUK8X6TjrOUKjtkZmrvikYUdk").build()
         val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)

         val request = PlacesApi.nearbySearchQuery(searchContext, latLng)
             .rankby(RankBy.DISTANCE)
             .type(PlaceType.HOSPITAL)
             .keyword("hospital mes proper")
             .await()
         return request
     }


     override fun onMapReady(googleMap: GoogleMap?) {
         val placesSearchResults = nearbySearch().results

         val localizationHospital: TextView = findViewById(R.id.hospitalLocation)
         val nameHospital: TextView = findViewById(R.id.hospitalName)
         localizationHospital.text = placesSearchResults[0].vicinity
         nameHospital.text = placesSearchResults[0].name
         val locationHospital = GmsLatLng(placesSearchResults[0].geometry.location.lat, placesSearchResults[0].geometry.location.lng)
         googleMap?.addMarker(MarkerOptions().position(locationHospital).title("Hospital més proper"))
         googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(GmsLatLng(placesSearchResults[0].geometry.location.lat, placesSearchResults[0].geometry.location.lng), 17.0f))
     }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            permissionCode -> if (grantResults.isNotEmpty() && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED
            ) {
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}