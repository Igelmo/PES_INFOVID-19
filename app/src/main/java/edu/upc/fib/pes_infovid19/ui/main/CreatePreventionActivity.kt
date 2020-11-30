package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_create_prevention.*

const val ID_RISK_PREVENTION_EXTRA_CREATE = "ID_RISK_PREVENTION_EXTRA_CREATE"

class CreatePreventionActivity : AppCompatActivity() {
    private val viewModel: RiskPreventionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_prevention)
        setSupportActionBar(toolbarCreatePrevention)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val idRiskPrevention = intent.getStringExtra(ID_RISK_PREVENTION_EXTRA_CREATE) ?: throw IllegalStateException("Missing Risk Prevention id while editing a prevention")
        createNewPreventionButton.setOnClickListener {
            constructNewPrevention(idRiskPrevention)
            onSupportNavigateUp()
        }
    }

    fun constructNewPrevention(idRiskPrevention: String) {
        val prevention = Prevention()
        prevention.title = titleTextPrevention.text.toString()
        prevention.text = textPrevention.text.toString()
        prevention.image = urlimage.text.toString()
        viewModel.addPrevention(idRiskPrevention, prevention)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}