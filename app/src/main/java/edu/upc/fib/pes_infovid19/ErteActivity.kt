package edu.upc.fib.pes_infovid19

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_health_menu.*

class ErteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_erte)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun guarda(view: View) {
        val et = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val email = et.text.toString()
        val erte = Erte()
        erte.addInfo(email, "a", "b", "c", "d", "e", "g", "0065")
    }
}