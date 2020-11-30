package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_edit_prevention.*

const val PREVENTION_EXTRA = "PREVENTION_EXTRA"
const val ID_RISK_PREVENTION_EXTRA = "ID_RISK_PREVENTION_EXTRA"

class EditPreventionActivity : AppCompatActivity() {
    private val viewModel: RiskPreventionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_prevention)
        setSupportActionBar(toolbarEditPrevention)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val prevention = intent.getSerializableExtra(PREVENTION_EXTRA) as Prevention
        val idRiskPrevention = intent.getStringExtra(ID_RISK_PREVENTION_EXTRA) ?: throw IllegalStateException("Missing Risk Prevention id while editing a prevention")

        setInfo(prevention)
        editPreventionButton.setOnClickListener {
            saveChanges(prevention.id, idRiskPrevention)
            onSupportNavigateUp()
        }
    }

    private fun saveChanges(id: String?, idRiskPrevention: String) {
        val title = titleTextEditPrevention.text.toString()
        val text = textEditPrevention.text.toString()
        val image = imageEditPrevention.text.toString()
        val prevention = Prevention(id as String, title, text, image)
        viewModel.modifyPrevention(idRiskPrevention, id, prevention)
    }

    private fun setInfo(prevention: Prevention) {
        titleTextEditPrevention.setText(prevention.title)
        textEditPrevention.setText(prevention.text)
        imageEditPrevention.setText(prevention.image)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}