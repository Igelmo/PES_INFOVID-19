package edu.upc.fib.pes_infovid19.ui.main.activity.health.tests

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.ui.main.activity.MainActivity
import edu.upc.fib.pes_infovid19.ui.main.activity.social.ChatActivity
import kotlinx.android.synthetic.main.activity_result_vulnerable_test.*

class ResultVulnerableTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_vulnerable_test)
        printResult()

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

    private fun printResult() {
        val resultHealth = intent.getDoubleExtra(PERCENT_HEALTH_EXTRA, 0.0).toInt()
        val resultEconomic = intent.getDoubleExtra(PERCENT_ECONOMIC_EXTRA, 0.0).toInt()
        val resultSocial = intent.getDoubleExtra(PERCENT_SOCIAL_EXTRA, 0.0).toInt()

        ObjectAnimator.ofInt(progressBar, "progress", resultHealth).setDuration(800).start()
        ObjectAnimator.ofInt(progressBar2, "progress", resultEconomic).setDuration(800).start()
        ObjectAnimator.ofInt(progressBar3, "progress", resultSocial).setDuration(800).start()

        textResultSalut.text = "Vulnerabilitat d'un $resultHealth%\nen salut."
        textResultEconomic.text = "Vulnerabilitat d'un $resultEconomic%\neconòmicament."
        textResultSocial.text = "Vulnerabilitat d'un $resultSocial%\nsocialment."
    }


}
