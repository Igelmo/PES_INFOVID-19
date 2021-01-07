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
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse
import com.google.android.libraries.places.api.net.PlacesClient
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_hospital_center.*
import com.google.android.gms.maps.model.LatLng as GmsLatLng


class HospitalCenterActivity : AppCompatActivity(), OnMapReadyCallback {
    private val PHONE_CALL_REQUEST_CODE = 1
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val permissionCode = 101
    private var hospitalPhone = ""

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

    private fun nearbySearch(listener: (Place) -> Unit) {
        Places.initialize(applicationContext, "AIzaSyBrZKY5DQjUK8X6TjrOUKjtkZmrvikYUdk")
        val placesClient = Places.createClient(this)
        val latLng = GmsLatLng(currentLocation.latitude, currentLocation.longitude)
        val bounds = RectangularBounds.newInstance(
            GmsLatLng(latLng.latitude - 0.01, latLng.longitude - 0.01),
            GmsLatLng(latLng.latitude + 0.01, latLng.longitude + 0.01)
        )
        val request = FindAutocompletePredictionsRequest.builder()
            .setOrigin(latLng)
            .setLocationBias(bounds)
            .setTypeFilter(TypeFilter.ESTABLISHMENT)
            .setQuery("hospital")
            .build()
        placesClient.findAutocompletePredictions(request)
            .addOnSuccessListener { response: FindAutocompletePredictionsResponse ->
                response.autocompletePredictions
                    .sortedBy { it.distanceMeters }
                    .firstOrNull()
                    ?.let { placesClient.getPlaceDetails(it, listener) }
            }
    }

    private fun PlacesClient.getPlaceDetails(prediction: AutocompletePrediction, listener: (Place) -> Unit) {
        val placeFields = listOf(Place.Field.LAT_LNG, Place.Field.ADDRESS, Place.Field.NAME, Place.Field.PHONE_NUMBER)
        val request = FetchPlaceRequest.newInstance(prediction.placeId, placeFields)
        fetchPlace(request).addOnSuccessListener {
            listener(it.place)
        }
    }


    override fun onMapReady(googleMap: GoogleMap?) {
        nearbySearch { place ->
            place.latLng?.let { locationHospital ->
                googleMap?.addMarker(MarkerOptions().position(locationHospital).title("Hospital més proper"))
                googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(locationHospital, 17.0f))
            }
            place.name?.let {
                hospitalNameText.text = it
            }
            place.address?.let {
                hospitalLocation.text = it
            }
            place.phoneNumber?.let {
                hospitalPhone = it
            }
        }
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