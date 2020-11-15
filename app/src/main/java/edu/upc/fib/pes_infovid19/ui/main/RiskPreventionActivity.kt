package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_risk_prevention.*

class RiskPreventionActivity : AppCompatActivity() {
    private val viewModel: PrevencioViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_risk_prevention)
        setSupportActionBar(toolbarRiskPrevention)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
3
        val adapter = RiskPreventionAdapter()
        recyclerViewRiskPrevention.adapter = adapter
        viewModel.prevencioLiveData.observe(this) { riskPreventionSnapshot ->
            adapter.updateRiskPrevention(riskPreventionSnapshot)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}