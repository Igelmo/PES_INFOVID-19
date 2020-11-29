package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_create_prevention.*

class CreatePreventionActivity : AppCompatActivity() {
    private val viewModel: RiskPreventionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_prevention)
        setSupportActionBar(toolbarCreatePrevention)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        createNewPreventionButton.setOnClickListener {
            constructNewPrevention()
            onSupportNavigateUp()
        }
    }

    fun constructNewPrevention() {
        val prevention = Prevention()
        prevention.title = titleTextPrevention.text.toString()
        prevention.text = textPrevention.text.toString()
        prevention.image = urlimage.text.toString()
        viewModel.addPrevention(prevention)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}