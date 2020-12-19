package edu.upc.fib.pes_infovid19.ui.main.activity.health.information

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.domain.structures.RiskPopulation
import edu.upc.fib.pes_infovid19.domain.viewmodel.RiskPopulationViewModel
import kotlinx.android.synthetic.main.activity_create_risk_population.*

class CreateRiskPopulationActivity : AppCompatActivity() {
    private val viewModel: RiskPopulationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_risk_population)
        setSupportActionBar(toolbarCreateRiskPopulation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        createNewRiskPopulationButton.setOnClickListener {
            constructNewRiskPopulation()
            onSupportNavigateUp()
        }
    }

    fun constructNewRiskPopulation() {
        val riskPopulation = RiskPopulation()
        riskPopulation.risk = titleCreateTextRiskPopulation.text.toString()
        viewModel.addRiskPopulation(riskPopulation)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}