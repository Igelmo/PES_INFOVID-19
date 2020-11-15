package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_risk_population.*

class RiskPopulationActivity : AppCompatActivity() {
    private val viewModel: RiskPopulationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_risk_population)
        setSupportActionBar(toolbarRiskPopulation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = RiskPopulationAdapter(false)
        recyclerViewRiskPopulation.adapter = adapter

        viewModel.riskPopulationLiveData.observe(this) { riskPopulationSnapshot ->
            adapter.updateRiskPopulation(riskPopulationSnapshot)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

