package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_edit_risk_population.*

const val RISKPOPULATION_EXTRA = "RISKPOPULATION_EXTRA"

class EditRiskPopulationActivity : AppCompatActivity() {
    private val viewModel: RiskPopulationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_risk_population)
        setSupportActionBar(toolbarEditRiskPopulation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val riskPopulation = intent.getSerializableExtra(RISKPOPULATION_EXTRA) as RiskPopulation
        setInfo(riskPopulation)
        editRiskPopulationButton.setOnClickListener {
            saveChanges(riskPopulation.id)
            onSupportNavigateUp()
        }
    }

    private fun saveChanges(id: String?) {
        val risk = titleTextEditRiskPopulation.text.toString()
        val riskPopulation = RiskPopulation(id as String, risk)
        viewModel.modifyRiskPopulation(id, riskPopulation)
    }

    private fun setInfo(riskPopulation: RiskPopulation) {
        titleTextEditRiskPopulation.setText(riskPopulation.risk)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}