package edu.upc.fib.pes_infovid19.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_health_menu.toolbar
import kotlinx.android.synthetic.main.activity_vulnerable_test.*

class VulnerableTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vulnerable_test)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun GenerateResult(View: View) {
        var percSalut = 0
        var percEcon = 0
        var percSocial = 0
        if (switch1.isChecked) percEcon += 40
        if (switch2.isChecked) percSocial += 45
        if (!switch3.isChecked) percSocial += 35
        if (switch4.isChecked) percSalut += 30
        if (switch5.isChecked) percSalut += 10
        if (switch6.isChecked) percSalut += 30
        if (switch7.isChecked) percSalut += 10
        if (switch8.isChecked) percSalut += 20
        if (!switch9.isChecked) percEcon += 45
        if (!switch10.isChecked) percEcon += 15
        if (!switch11.isChecked) percSocial += 20
        val intent = Intent(this, ResultVulnerableTestActivity::class.java)
        intent.putExtra("percEcon", percEcon)
        intent.putExtra("percSocial", percSocial)
        intent.putExtra("percSalut", percSalut)
        startActivity(intent)
    }
}