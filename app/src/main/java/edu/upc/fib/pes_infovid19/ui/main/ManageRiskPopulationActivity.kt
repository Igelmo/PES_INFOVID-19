package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_manage_risk_population.*

class ManageRiskPopulationActivity() : AppCompatActivity() {
    private val viewModel: RiskPopulationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_risk_population)
        setSupportActionBar(toolbarManageRiskPopulation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = RiskPopulationAdapter(true, { editRiskPopulation(it) }, { })
        recyclerManageViewRiskPopulation.adapter = adapter

        viewModel.riskPopulationLiveData.observe(this) { RiskPopulationSnapshot ->
            adapter.updateRiskPopulation(RiskPopulationSnapshot)
        }

        fabCreateNewRiskPopulation.setOnClickListener {
        }
    }

    private fun editRiskPopulation(RiskPopulation: RiskPopulation) {
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}