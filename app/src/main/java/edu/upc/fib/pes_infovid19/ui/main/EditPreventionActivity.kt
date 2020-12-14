package edu.upc.fib.pes_infovid19.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_edit_prevention.*

const val PREVENTION_EXTRA = "PREVENTION_EXTRA"

class EditPreventionActivity : AppCompatActivity() {
    private val viewModel: RiskPreventionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_prevention)
        setSupportActionBar(toolbarEditPrevention)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val prevention = intent.getSerializableExtra(PREVENTION_EXTRA) as Prevention

        setInfo(prevention)
        editPreventionButton.setOnClickListener {
            returnChanges(prevention.id)
        }
    }

    private fun returnChanges(id: String) {
        val title = titleTextEditPrevention.text.toString()
        val text = textEditPrevention.text.toString()
        val image = imageEditPrevention.text.toString()
        val prevention = Prevention(id, title, text, image)
        val intent = Intent()
        intent.putExtra(EDIT_PREVENTION_EXTRA, prevention)
        setResult(RESULT_OK, intent)
        finish()
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