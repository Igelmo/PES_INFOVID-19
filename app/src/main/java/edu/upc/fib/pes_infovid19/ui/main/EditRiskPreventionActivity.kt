package edu.upc.fib.pes_infovid19.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_edit_risk_prevention.*
import java.io.Serializable

const val RISKPREVENTION_EXTRA = "RISKPREVENTION_EXTRA"
const val LISTCREATEDPREVENTIONS_EXTRA = "LISTCREATEDPREVENTIONS_EXTRA"

class EditRiskPreventionActivity : AppCompatActivity() {
    private val SECOND_ACTIVITY_REQUEST_CODE = 0 //TODO fer global
    private val viewModel: RiskPreventionViewModel by viewModels()
    private var listCreatedPreventions = emptyList<Prevention>()
    private val riskPrevention by lazy { intent.getSerializableExtra(RISKPREVENTION_EXTRA) as RiskPrevention }
    private val listPreventions by lazy { riskPrevention.recomanacionsAsList }
    private val adapter by lazy { PreventionAdapter(listPreventions, true) { editPrevention(it, riskPrevention.id) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_risk_prevention)
        setSupportActionBar(toolbareditRiskPrevention)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        editRecyclerViewManageRiskPrevention.adapter = adapter

        setInfo(riskPrevention)
        editNewRiskPreventionButton.setOnClickListener {
            saveChanges(riskPrevention.id)
            onSupportNavigateUp()
        }
        fabeditNewPrevention.setOnClickListener {
            val intent = Intent(this, CreatePreventionActivity::class.java)
            intent.putExtra(PREVENTION_EXTRA_CREATE, listCreatedPreventions as Serializable)
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                listCreatedPreventions = data?.getSerializableExtra(LISTCREATEDPREVENTIONS_EXTRA) as? List<Prevention> ?: emptyList()
                adapter.updateCreatedPreventions(listCreatedPreventions)
            }
        }
    }

    private fun saveChanges(id: String?) {
        val title = editTitleTextRiskPrevention.text.toString()
        val date = editDateRiskPrevention.text.toString()
        val source = editSourceRiskPrevention.text.toString()
        val recomendationsAdapter = editRecyclerViewManageRiskPrevention.adapter as PreventionAdapter
        val recomendations = recomendationsAdapter.preventions.filterNot { it.id.isNullOrBlank() }
        val riskPrevention = RiskPrevention(id as String, date, "", recomendations.associateBy { it.id }, source, title)
        viewModel.modifyRiskPrevention(id, riskPrevention, listCreatedPreventions)
    }

    private fun setInfo(riskPrevention: RiskPrevention) {
        editTitleTextRiskPrevention.setText(riskPrevention.title)
        editDateRiskPrevention.setText(riskPrevention.date)
        editSourceRiskPrevention.setText(riskPrevention.source)
    }

    private fun editPrevention(prevention: Prevention, idRiskPrevention: String) {
        val intent = Intent(this, EditPreventionActivity::class.java)
        intent.putExtra(PREVENTION_EXTRA, prevention)
        intent.putExtra(ID_RISK_PREVENTION_EXTRA, idRiskPrevention)
        startActivity(intent)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
