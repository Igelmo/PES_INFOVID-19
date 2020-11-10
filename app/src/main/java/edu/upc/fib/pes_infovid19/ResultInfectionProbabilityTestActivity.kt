package edu.upc.fib.pes_infovid19

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.ui.main.CenterActivity
import kotlinx.android.synthetic.main.activity_result_infection_probability_test.*

class ResultInfectionProbabilityTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_infection_probability_test)
        PrintInfectionResult()
        toolbarResultInfecion.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        buttonNearestCenter.setOnClickListener {
            val intent = Intent(this, CenterActivity::class.java)
            startActivity(intent)
        }
        buttonCall.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        buttonTestVulnerable.setOnClickListener {
            val intent = Intent(this, VulnerableTestActivity::class.java)
            startActivity(intent)
        }
    }

    private fun PrintInfectionResult() {
        val perc = intent.extras?.getDouble("percent")

        val textResultat = findViewById<TextView>(R.id.textResultInfection)

        if (perc != null) {
            if (perc in 0.0..12.0)
                textResultat.text = "Molt baixa"
        }
        if (perc != null) {
            if (perc > 12.0 && perc <= 27.5)
                textResultat.text = "Baixa"
        }
        if (perc != null) {
            if (perc > 27.5 && perc <= 35.0)
                textResultat.text = "Normal"
        }
        if (perc != null) {
            if (perc > 35.0 && perc <= 50.0)
                textResultat.text = "Alta"
        }
        if (perc != null) {
            if (perc > 50.0 && perc <= 100.0)
                textResultat.text = "Molt alta"
        }
    }
}