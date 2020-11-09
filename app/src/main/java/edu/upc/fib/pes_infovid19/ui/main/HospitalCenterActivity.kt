package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_hospital_center.*


class HospitalCenterActivity : AppCompatActivity() {
    lateinit var googleMap: GoogleMap
    lateinit var mapview: MapView

    override fun onResume() {
        super.onResume()
        mapview.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapview.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        mapview.onPause()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_center)
        setSupportActionBar(toolbarCentreHospitalari)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mapview = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        googleMap = mapView.getMap()
        googleMap = setMapType(GoogleMap.MAP_TYPE_NORMAL)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun cerca(view: View) {

    }


}


