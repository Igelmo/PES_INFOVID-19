package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_create_risk_prevention.*

class CreateRiskPreventionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_risk_prevention)
        setSupportActionBar(toolbarCreateRiskPrevention)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        createNewRiskPreventionButton.setOnClickListener {
            onSupportNavigateUp()
        }
    }

    fun constructNewTestType() {

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}