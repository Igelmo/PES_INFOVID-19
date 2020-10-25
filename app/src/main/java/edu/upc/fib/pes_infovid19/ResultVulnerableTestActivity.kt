package edu.upc.fib.pes_infovid19

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.ui.main.ChatActivity
import edu.upc.fib.pes_infovid19.ui.main.InfectionProbabilityTestActivity
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
        val res1 = intent.extras?.getInt("percSalut")
        val res2 = intent.extras?.getInt("percEcon")
        val res3 = intent.extras?.getInt("percSocial")


        val progress1 = findViewById<ProgressBar>(R.id.progressBar)
        val progress2 = findViewById<ProgressBar>(R.id.progressBar2)
        val progress3 = findViewById<ProgressBar>(R.id.progressBar3)
        progress1.progress = 0
        progress2.progress = 0
        progress3.progress = 0
        val text1 = findViewById<TextView>(R.id.textResultSalut)
        val text2 = findViewById<TextView>(R.id.textResultEconomic)
        val text3 = findViewById<TextView>(R.id.textResultSocial)
        if (res1 != null) {
            progress1.progress = res1
        };
        if (res2 != null) {
            progress2.progress = res2
        };
        if (res3 != null) {
            progress3.progress = res3
        };
        text1.text = "Vulnerabilitat d'un " + res1.toString() + "%\nen salut."
        text2.text = "Vulnerabilitat d'un " + res2.toString() + "%\neconòmicament."
        text3.text = "Vulnerabilitat d'un " + res3.toString() + "%\nsocialment."
    }


}