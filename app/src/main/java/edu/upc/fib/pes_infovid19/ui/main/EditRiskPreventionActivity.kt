package edu.upc.fib.pes_infovid19.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_edit_myth.*
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
        val listPreventions = riskPrevention.recomanacions.values.toList()

        val adapter = PreventionAdapter(listPreventions, true, { editPrevention(it) }, { })
        editRecyclerViewManageRiskPrevention.adapter = adapter

        setInfo(riskPrevention)
        editNewRiskPreventionButton.setOnClickListener {

            onSupportNavigateUp()
        }
        fabeditNewPrevention.setOnClickListener {
            val intent = Intent(this, CreateRiskPreventionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveChanges(id: String?) {
        val title = editTitleTextRiskPrevention.text.toString()
        val date = dateEditMyth.text.toString()
        val source = editSourceRiskPrevention.text.toString()
        val recomendationsAdapter = editRecyclerViewManageRiskPrevention.adapter as PreventionAdapter
        val recomendations = recomendationsAdapter.preventions
        val riskPrevention = RiskPrevention(id as String, date, "", recomendations.associate { Pair(it.id, it) }, source, title)
        viewModel.modifyRiskPrevention(id, riskPrevention)
    }

    private fun setInfo(riskPrevention: RiskPrevention) {
        editTitleTextRiskPrevention.setText(riskPrevention.title)
        editDateRiskPrevention.setText(riskPrevention.date)
        editSourceRiskPrevention.setText(riskPrevention.source)
    }

    private fun editPrevention(prevention: Prevention) {
        val intent = Intent(this, EditPreventionActivity::class.java)
        intent.putExtra(PREVENTION_EXTRA, prevention)
        startActivity(intent)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}