package edu.upc.fib.pes_infovid19

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
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
        val erte = Erte()
        erte.addInfo(email, nom, cognom, dni, empresa, localitat, provincia, codiPostal)
        return erte

    }

    fun guarda(view: View) {
        val erte = rellenaErte()
        val et = findViewById<TextView>(R.id.textView)
        et.text =
            "El artículo 3.2 de la Ley Federal de Protección de Datos Personales del 10 de octubre de 2012, que se refiere a la obtención de información personal de clientes, usuarios y proveedores, denominado como Usuario en el futuro, determina que el uso de esta información debe de ser de manera exclusiva para  las operaciones y procesos que el Usuario ha contratado con el proveedor del servicio, denominado en el futuro como Proveedor." +

                    "En el cumplimiento del artículo 1° de dicha ley que establece que ninguna persona puede comercializar, trasladar, vender, intercambiar, reglar o publicar por ningún medio escrito o electrónico conocido o por conocerse, la información que los Usuarios de sus productos o servicios le proporcionen, se establece que:" +

                    "Artículo 1°: La información personal que se está solicitando es propiedad del Usuario, y el Proveedor únicamente la utiliza para dar un mejor servicio." +

                    "Artículo 2°: El proveedor garantiza que todos los datos proporcionados son almacenados en una base de datos que cuenta con los niveles de seguridad de acurdo a la norma oficial vigente." +

                    "Artículo 3°: En caso de que la información proporcionada al proveedor sea publicada o comercializada, el proveedor será acreedor a las sanciones que la ley estipula para este tipo de casaos en los artículos 34° fracción III, 51° Fracción II y 125° de la Ley federal de Protección de Datos Personales."
        et.visibility = View.VISIBLE

    }
}