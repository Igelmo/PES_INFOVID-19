package edu.upc.fib.pes_infovid19.ui.main.activity.health.information

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.domain.viewmodel.RiskPreventionViewModel
import edu.upc.fib.pes_infovid19.ui.main.adapter.RiskPreventionAdapter
import kotlinx.android.synthetic.main.activity_risk_prevention.*

class RiskPreventionActivity : AppCompatActivity() {
    private val viewModelRisk: RiskPreventionViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_risk_prevention)
        setSupportActionBar(toolbarRiskPrevention)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        3
        manageRiskPreventionButton.setOnClickListener {
            val intent = Intent(this, ManageRiskPreventionActivity::class.java)
            startActivity(intent)
        }

        val adapter = RiskPreventionAdapter(false)
        recyclerViewRiskPrevention.adapter = adapter
        viewModelRisk.preventionLiveData.observe(this) { riskPreventionSnapshot ->
            adapter.updateRiskPrevention(riskPreventionSnapshot)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}