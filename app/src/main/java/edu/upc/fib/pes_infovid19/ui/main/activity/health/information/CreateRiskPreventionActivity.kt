package edu.upc.fib.pes_infovid19.ui.main.activity.health.information

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.domain.structures.RiskPrevention
import edu.upc.fib.pes_infovid19.domain.viewmodel.RiskPreventionViewModel
import kotlinx.android.synthetic.main.activity_create_risk_prevention.*

class CreateRiskPreventionActivity : AppCompatActivity() {
    private val viewModel: RiskPreventionViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_risk_prevention)
        setSupportActionBar(toolbarCreateRiskPrevention)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        createNewRiskPreventionButton.setOnClickListener {
            constructNewRiskPrevention()
            onSupportNavigateUp()
        }
    }

    fun constructNewRiskPrevention() {
        val riskPrevention = RiskPrevention()
        riskPrevention.title = titleTextRiskPrevention.text.toString()
        riskPrevention.date = createDateRiskPrevention.text.toString()
        riskPrevention.source = createSourceRiskPrevention.text.toString()
        viewModel.addRiskPrevention(riskPrevention)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}