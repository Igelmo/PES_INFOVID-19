package edu.upc.fib.pes_infovid19.ui

import android.content.Intent
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

        val adapter = RiskPopulationAdapter(true, { editRiskPopulation(it) }, { viewModel.deleteRiskPopulation(it) })
        recyclerManageViewRiskPopulation.adapter = adapter

        viewModel.riskPopulationLiveData.observe(this) { RiskPopulationSnapshot ->
            adapter.updateRiskPopulation(RiskPopulationSnapshot)
        }

        fabCreateNewRiskPopulation.setOnClickListener {
            val intent = Intent(this, CreateRiskPopulationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun editRiskPopulation(riskPopulation: RiskPopulation) {
        val intent = Intent(this, EditRiskPopulationActivity::class.java)
        intent.putExtra(RISKPOPULATION_EXTRA, riskPopulation)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}