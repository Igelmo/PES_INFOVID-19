package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_hospital_center.*

class HospitalCenterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_center)
        setSupportActionBar(toolbarCentreHospitalari)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}