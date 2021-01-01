package edu.upc.fib.pes_infovid19.ui.main.activity.health.hospitalcenter

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
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
import com.google.maps.PlaceDetailsRequest
import com.google.maps.PlacesApi
import com.google.maps.model.*
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_hospital_center.*
import com.google.android.gms.maps.model.LatLng as GmsLatLng


class HospitalCenterActivity : AppCompatActivity(), OnMapReadyCallback {
    private val PHONE_CALL_REQUEST_CODE = 1
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val permissionCode = 101
    private var hospitalPhone = ""
    private var hospitalAddress = ""
    private var hospitalName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_center)
        setSupportActionBar(toolbarCentreHospitalari)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        fetchLocation()

        buttonCallHospital.setOnClickListener {
            makeCall()
        }
    }

    private fun makeCall() {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:" + hospitalPhone)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), PHONE_CALL_REQUEST_CODE)
        }
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

    private fun nearbySearch(): List<PlaceDetails> {

        val searchContext = GeoApiContext.Builder().apiKey("AIzaSyBrZKY5DQjUK8X6TjrOUKjtkZmrvikYUdk").build()
        val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)

        val request = PlacesApi.nearbySearchQuery(searchContext, latLng)
            .rankby(RankBy.DISTANCE)
            .type(PlaceType.HOSPITAL)
            .openNow(true)
            .keyword("hospital public mes a prop")
            .await()
        return request.results.map {
            PlacesApi.placeDetails(searchContext, it.placeId).fields(
                PlaceDetailsRequest.FieldMask.INTERNATIONAL_PHONE_NUMBER,
                PlaceDetailsRequest.FieldMask.NAME,
                PlaceDetailsRequest.FieldMask.VICINITY,
                PlaceDetailsRequest.FieldMask.GEOMETRY_LOCATION_LAT,
                PlaceDetailsRequest.FieldMask.GEOMETRY_LOCATION_LNG
            ).await()
        }
    }


    override fun onMapReady(googleMap: GoogleMap?) {
        val placesSearchResults = nearbySearch()
        val locationHospital = GmsLatLng(placesSearchResults[0].geometry.location.lat, placesSearchResults[0].geometry.location.lng)

        googleMap?.addMarker(MarkerOptions().position(locationHospital).title("Hospital més proper"))
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(GmsLatLng(placesSearchResults[0].geometry.location.lat, placesSearchResults[0].geometry.location.lng), 17.0f))

        hospitalLocation.text = placesSearchResults[0].vicinity
        hospitalNameText.text = placesSearchResults[0].name
        hospitalPhone = placesSearchResults[0].internationalPhoneNumber
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            permissionCode -> if (grantResults.isNotEmpty() && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED
            ) {
                fetchLocation()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}