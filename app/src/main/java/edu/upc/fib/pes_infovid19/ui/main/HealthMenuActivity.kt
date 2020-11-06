package edu.upc.fib.pes_infovid19.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_health_menu.*

class HealthMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_menu)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mitesCovid.setOnClickListener {
            val intent = Intent(this, MythsActivity::class.java)
            startActivity(intent)
        }

        provesDeteccio.setOnClickListener {
            val intent = Intent(this, TestTypeActivity::class.java)
            startActivity(intent)
        }

        consultarPoblacioRisc.setOnClickListener {
            val intent = Intent(this, RiskPopulationActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}