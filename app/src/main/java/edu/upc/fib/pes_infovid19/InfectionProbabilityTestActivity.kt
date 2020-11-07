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
        toolbarInfectionTest.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun generateInfectionResult(View: View) {
        var percent = 0.0
        if (switch1.isChecked) percent += 7.5
        if (switch2.isChecked) percent += 6
        if (switch3.isChecked) percent += 7.5
        if (switch4.isChecked) percent += 15
        if (switch5.isChecked) percent += 6.5
        if (switch6.isChecked) percent += 7
        if (switch7.isChecked) percent += 15
        if (switch8.isChecked) percent += 15
        if (switch9.isChecked) percent += 7.5
        if (switch10.isChecked) percent += 7
        if (switch11.isChecked) percent += 6
        val intent = Intent(this, ResultInfectionProbabilityTestActivity::class.java)
        intent.putExtra("percent", percent)
        startActivity(intent)
    }
}