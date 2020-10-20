package edu.upc.fib.pes_infovid19

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_health_menu.*
import java.util.*

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

    fun rellenaErte(): Erte {
        var et = findViewById<EditText>(R.id.editTextTextEmailAddress)
        var email = et.text.toString()
        et = findViewById<EditText>(R.id.editTextTextMultiLine)
        val nom = et.text.toString()
        et = findViewById<EditText>(R.id.editTextTextMultiLine3)
        val cognom = et.text.toString()
        et = findViewById<EditText>(R.id.editTextTextMultiLine4)
        val dni = et.text.toString()
        et = findViewById<EditText>(R.id.editTextTextMultiLine6)
        val empresa = et.text.toString()
        et = findViewById<EditText>(R.id.editTextTextMultiLine7)
        val localitat = et.text.toString()
        et = findViewById<EditText>(R.id.editTextTextMultiLine8)
        val provincia = et.text.toString()
        et = findViewById<EditText>(R.id.editTextTextMultiLine9)
        val codiPostal = et.text.toString()
        et = findViewById<EditText>(R.id.editTextTextMultiLine2)
        val base_reguladora = et.text.toString()
        val erte = Erte()
        erte.addInfo(email, nom, cognom, dni, empresa, localitat, provincia, codiPostal, base_reguladora)
        return erte

    }

    fun seisMeses(mes: Int): Int {
        return mes + 6
    }

    fun guarda(view: View) {
        val erte = rellenaErte()
        val calendar = Calendar.getInstance()
        val et = findViewById<TextView>(R.id.textView)
        var c = 0
        var mes = seisMeses(calendar.get(Calendar.MONTH))
        if (mes > 12) {
            mes %= 12
            ++c
        }
        et.text = "Sol·licitud col·lectiva de prestacions d'atur per suspensió" + "\n" + "\n" +
                "Cognoms: " + erte.cognoms + "\n" +
                "Nom: " + erte.nom + "\n" +
                "Dni: " + erte.dni + "\n" +
                "Codi Postal: " + erte.codiPostal + "\n" +
                "Número de telefon: " + erte.num_telefon + "\n" +
                "Número de compte: " + erte.compte_bancari + "\n" +
                "Tipus d'erte: " + "suspensió" + "\n" +
                "Data d'inici: " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR) + "\n" +
                "Data final: " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + mes + "/" + (calendar.get(Calendar.YEAR) + c) + "\n" +
                "Base reguladora: " + erte.base_reguladora + "\n" + "\n" + "\n" +
                "-----------------------------------------------------------------Firma" + "\n"

        et.visibility = View.VISIBLE

    }
}