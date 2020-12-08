package edu.upc.fib.pes_infovid19

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_infection_probability_test.*

class InfectionProbabilityTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infection_probability_test)
        setSupportActionBar(toolbarInfectionTest)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        buttonCheckProbability.setOnClickListener {
            val intent = Intent(this, ResultInfectionProbabilityTestActivity::class.java)
            startActivity(intent)
        }
    }

    fun generateInfectionResult(View: View) {
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