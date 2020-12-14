package edu.upc.fib.pes_infovid19.ui.main.activity.health.information

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.domain.structures.Prevention
import edu.upc.fib.pes_infovid19.domain.structures.RiskPrevention
import edu.upc.fib.pes_infovid19.domain.viewmodel.RiskPreventionViewModel
import edu.upc.fib.pes_infovid19.ui.main.adapter.PreventionAdapter
import kotlinx.android.synthetic.main.activity_edit_risk_prevention.*
import java.io.Serializable

const val RISKPREVENTION_EXTRA = "RISKPREVENTION_EXTRA"
const val LISTCREATEDPREVENTIONS_EXTRA = "LISTCREATEDPREVENTIONS_EXTRA"
const val EDIT_PREVENTION_EXTRA = "EDIT_PREVENTION_EXTRA"
const val CREATE_PREVENTION_REQUEST_CODE = 0
const val EDIT_PREVENTION_REQUEST_CODE = 1

class EditRiskPreventionActivity : AppCompatActivity() {
    private val viewModel: RiskPreventionViewModel by viewModels()
    private val riskPrevention by lazy { intent.getSerializableExtra(RISKPREVENTION_EXTRA) as RiskPrevention }
    private val listPreventions by lazy { riskPrevention.recomanacionsAsList }
    private val adapter by lazy { PreventionAdapter(listPreventions, true) { editPrevention(it) } }

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
            intent.putExtra(PREVENTION_EXTRA_CREATE, adapter.createdPreventions as Serializable)
            startActivityForResult(intent, CREATE_PREVENTION_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CREATE_PREVENTION_REQUEST_CODE -> {
                    adapter.createdPreventions = data?.getSerializableExtra(LISTCREATEDPREVENTIONS_EXTRA) as? MutableList<Prevention> ?: mutableListOf()
                    adapter.updateCreatedPreventions(adapter.createdPreventions)
                }
                EDIT_PREVENTION_REQUEST_CODE -> {
                    val prevention = data?.getSerializableExtra(EDIT_PREVENTION_EXTRA) as? Prevention ?: throw IllegalStateException("Edit prevention didn't return a prevention")
                    adapter.updatePrevention(prevention)
                }
            }
        }
    }

    private fun saveChanges(id: String?) {
        val title = editTitleTextRiskPrevention.text.toString()
        val date = editDateRiskPrevention.text.toString()
        val source = editSourceRiskPrevention.text.toString()
        val recomendationsAdapter = editRecyclerViewManageRiskPrevention.adapter as PreventionAdapter
        val recomendations = recomendationsAdapter.preventions.filterNot { it.id.isBlank() }
        val riskPrevention = RiskPrevention(id as String, date, "", recomendations.associateBy { it.id }, source, title)
        viewModel.modifyRiskPrevention(id, riskPrevention, adapter.createdPreventions)
    }

    private fun setInfo(riskPrevention: RiskPrevention) {
        editTitleTextRiskPrevention.setText(riskPrevention.title)
        editDateRiskPrevention.setText(riskPrevention.date)
        editSourceRiskPrevention.setText(riskPrevention.source)
    }

    private fun editPrevention(prevention: Prevention) {
        val intent = Intent(this, EditPreventionActivity::class.java)
        intent.putExtra(PREVENTION_EXTRA, prevention)
        startActivityForResult(intent, EDIT_PREVENTION_REQUEST_CODE)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
