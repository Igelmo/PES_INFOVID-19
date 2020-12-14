package edu.upc.fib.pes_infovid19

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.ui.main.ChatActivity
import kotlinx.android.synthetic.main.activity_result_vulnerable_test.*

class ResultVulnerableTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_vulnerable_test)
        PrintResult()
        toolbarResultVulnerable.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        buttonChat.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }
        buttonTest.setOnClickListener {
            val intent = Intent(this, InfectionProbabilityTestActivity::class.java)
            startActivity(intent)
        }
    }

    private fun PrintResult() {
        val resultHealth = intent.getDoubleExtra(PERCENT_HEALTH_EXTRA, 0.0).toInt()
        val resultEconomic = intent.getDoubleExtra(PERCENT_ECONOMIC_EXTRA, 0.0).toInt()
        val resultSocial = intent.getDoubleExtra(PERCENT_SOCIAL_EXTRA, 0.0).toInt()

        progressBar.progress = resultHealth
        progressBar2.progress = resultEconomic
        progressBar3.progress = resultSocial

        textResultSalut.text = "Vulnerabilitat d'un $resultHealth%\nen salut."
        textResultEconomic.text = "Vulnerabilitat d'un $resultEconomic%\neconòmicament."
        textResultSocial.text = "Vulnerabilitat d'un $resultSocial%\nsocialment."
    }


}