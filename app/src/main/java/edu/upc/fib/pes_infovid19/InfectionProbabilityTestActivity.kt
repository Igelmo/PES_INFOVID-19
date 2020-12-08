package edu.upc.fib.pes_infovid19

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import edu.upc.fib.pes_infovid19.ui.main.InfectionProbabilityTestAdapter
import kotlinx.android.synthetic.main.activity_infection_probability_test.*

class InfectionProbabilityTestActivity : AppCompatActivity() {
    private val viewModel: InfectionProbabilityTestViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infection_probability_test)
        setSupportActionBar(toolbarInfectionTest)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        buttonCheckProbability.setOnClickListener {
            val intent = Intent(this, ResultInfectionProbabilityTestActivity::class.java)
            startActivity(intent)
        }

        val adapter = InfectionProbabilityTestAdapter(false)
        recyclerViewInfectionTest.adapter = adapter

        viewModel.questionsProbabilityTestLiveData.observe(this) { questionsSnapshot ->
            adapter.updateQuestions(questionsSnapshot)
        }
    }

    private fun generateInfectionResult(View: View) {
        var percent = 0.0
        val intent = Intent(this, ResultInfectionProbabilityTestActivity::class.java)
        intent.putExtra("percent", percent)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}