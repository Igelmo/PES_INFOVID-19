package edu.upc.fib.pes_infovid19.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_manage_risk_prevention.*

class ManageRiskPreventionActivity : AppCompatActivity() {
    private val viewModel: RiskPreventionViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_risk_prevention)
        setSupportActionBar(toolbarManageRiskPrevention)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = RiskPreventionAdapter(true, { }, { })
        recyclerManageViewRiskPrevention.adapter = adapter

        viewModel.preventionLiveData.observe(this) { riskPreventionSnapshot ->
            adapter.updateRiskPrevention(riskPreventionSnapshot)
        }

        fabCreateNewRiskPrevention.setOnClickListener {
            val intent = Intent(this, CreateRiskPreventionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun editRiskPrevention(riskPrevention: RiskPrevention) {
        val intent = Intent(this, EditTestTypeActivity::class.java)
        intent.putExtra(TESTTYPE_EXTRA, riskPrevention)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}