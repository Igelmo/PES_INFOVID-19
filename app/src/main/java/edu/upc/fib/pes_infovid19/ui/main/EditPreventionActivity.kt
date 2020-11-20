package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_edit_prevention.*

const val PREVENTION_EXTRA = "PREVENTION_EXTRA"

class EditPreventionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_prevention)
        setSupportActionBar(toolbarEditPrevention)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val prevention = intent.getSerializableExtra(PREVENTION_EXTRA) as Prevention
        setInfo(prevention)
        editPreventionButton.setOnClickListener {
            onSupportNavigateUp()
        }
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