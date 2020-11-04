package edu.upc.fib.pes_infovid19

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_health_menu.*
import java.util.*

private var erte = Erte()

class ErteActivity : AppCompatActivity() {
    private val STORAGE_CODE: Int = 100

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

    fun anterior(view: View) {
        val et = findViewById<TextView>(R.id.editView)
        val benvia = findViewById<Button>(R.id.button)
        val baccept = findViewById<Button>(R.id.button3)
        val torna = findViewById<Button>(R.id.button2)
        val guarda = findViewById<Button>(R.id.button4)
        et.visibility = View.INVISIBLE
        benvia.visibility = View.INVISIBLE
        baccept.visibility = View.VISIBLE
        torna.visibility = View.INVISIBLE
        guarda.visibility = View.INVISIBLE
    }

    @SuppressLint("SetTextI18n")
    fun guarda(view: View) {
        erte = rellenaErte()
        val calendar = Calendar.getInstance()
        val et = findViewById<TextView>(R.id.editView)
        val benvia = findViewById<Button>(R.id.button)
        val baccept = findViewById<Button>(R.id.button3)
        val torna = findViewById<Button>(R.id.button2)
        val guarda = findViewById<Button>(R.id.button4)
        var c = 0
        var mes = seisMeses(calendar.get(Calendar.MONTH))
        if (mes > 12) {
            mes %= 12
            ++c
            ++mes
        }
        et.text = "\n" + "Sol·licitud col·lectiva de prestacions d'atur per suspensió" + "\n" + "\n" +
                "Email: " + erte.email + "\n" +
                "Cognoms: " + erte.cognoms + "\n" +
                "Nom: " + erte.nom + "\n" +
                "Dni: " + erte.dni + "\n" +
                "Empresa: " + erte.empresa + "\n" +
                "Codi Postal: " + erte.codiPostal + "\n" +
                "Número de telefon: " + erte.num_telefon + "\n" +
                "Número de compte: " + erte.compte_bancari + "\n" +
                "Tipus d'erte: " + "suspensió" + "\n" +
                "Data d'inici: " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR) + "\n" +
                "Data final: " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + mes + "/" + (calendar.get(Calendar.YEAR) + c) + "\n" +
                "Base reguladora: " + erte.base_reguladora + "\n" + "\n" + "\n" + "\n" + "\n" +
                "-----------------------------------------------------------------Firma" + "\n"

        et.visibility = View.VISIBLE
        benvia.visibility = View.VISIBLE
        baccept.visibility = View.INVISIBLE
        torna.visibility = View.VISIBLE
        guarda.visibility = View.VISIBLE
    }

    fun transformaPdf(view: View) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                requestPermissions(permissions, STORAGE_CODE)
            } else {
                savePdf()
            }
        }

    }


    private fun savePdf() {
        try {
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.data = Uri.parse(erte.email)
            emailIntent.type = "text/plain"
            emailIntent.putExtra(Intent.EXTRA_EMAIL, "InfoVid-19")
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Paper de reclamació de Erte")
            emailIntent.putExtra(Intent.EXTRA_TEXT, findViewById<TextView>(R.id.editView).text)
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();

        } catch (e: Exception) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            STORAGE_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    savePdf()
                } else {
                    Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}