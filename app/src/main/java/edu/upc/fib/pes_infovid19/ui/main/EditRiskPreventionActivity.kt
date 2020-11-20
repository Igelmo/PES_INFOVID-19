package edu.upc.fib.pes_infovid19.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_edit_risk_prevention.*

const val RISKPREVENTION_EXTRA = "RISKPREVENTION_EXTRA"

class EditRiskPreventionActivity : AppCompatActivity() {
    private val viewModel: RiskPreventionViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_risk_prevention)
        setSupportActionBar(toolbareditRiskPrevention)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val riskPrevention = intent.getSerializableExtra(RISKPREVENTION_EXTRA) as RiskPrevention
        setInfo(riskPrevention)
        editNewRiskPreventionButton.setOnClickListener {
            onSupportNavigateUp()
        }
        fabeditNewPrevention.setOnClickListener {
            val intent = Intent(this, CreateRiskPreventionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setInfo(riskPrevention: RiskPrevention) {
        editTitleTextRiskPrevention.setText(riskPrevention.title)
        editRecyclerViewManageRiskPrevention.adapter = PreventionAdapter(riskPrevention.recomanacions.values.toList(), true)
        editDateRiskPrevention.setText(riskPrevention.date)
        editSourceRiskPrevention.setText(riskPrevention.source)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}