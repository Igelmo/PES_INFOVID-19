package edu.upc.fib.pes_infovid19

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ErteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_erte)
    }

    //Método return
    fun anterior(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun guarda(view: View) {
        val et = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val email = et.text.toString()
        val erte = Erte()
        erte.addInfo(email, "a", "b", "c", "d", "e", "g", "0065")
    }
}