package edu.upc.fib.pes_infovid19.ui.main.activity.economic

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.itextpdf.text.Document
import com.itextpdf.text.PageSize
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.domain.structures.Erte
import kotlinx.android.synthetic.main.activity_health_menu.*
import java.io.File
import java.io.FileOutputStream
import java.util.*

private var erte = Erte()

@Suppress("DEPRECATION")
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
        val info = findViewById<Button>(R.id.button5)
        et.visibility = View.INVISIBLE
        info.visibility = View.VISIBLE
        benvia.visibility = View.INVISIBLE
        baccept.visibility = View.VISIBLE
        torna.visibility = View.INVISIBLE
        guarda.visibility = View.INVISIBLE
    }

    fun guardaEnLaBaseDeDatos() {

        val user = FirebaseAuth.getInstance().currentUser
        val mDatabase = FirebaseDatabase.getInstance().reference.child("User").orderByChild("email").equalTo(user?.email)
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val nom = snapshot.child("username").getValue(String::class.java)!!
                    fillErte(nom)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("The read failed: " + databaseError.code)
            }

        })


    }

    fun fillErte(name: String) {
        val database = FirebaseDatabase.getInstance().reference
        database.child("Erte").child(name).setValue(erte)
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
        val info = findViewById<Button>(R.id.button5)
        var c = 0
        var mes = seisMeses(calendar.get(Calendar.MONTH))
        if (mes > 12) {
            mes %= 12
            ++c
            ++mes
        }
        et.text = "Sol·licitud col·lectiva de prestacions d'atur per suspensió" + "\n" + "\n" +
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
                "Base reguladora: " + erte.base_reguladora + "\n" + "\n" + "\n" + "\n" +
                "------------------------------------------Firma" + "\n"
        guardaEnLaBaseDeDatos()
        et.visibility = View.VISIBLE
        info.visibility = View.INVISIBLE
        benvia.visibility = View.VISIBLE
        baccept.visibility = View.INVISIBLE
        torna.visibility = View.VISIBLE
        guarda.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    fun info(view: View) {
        val et = findViewById<TextView>(R.id.editView)
        val torna = findViewById<Button>(R.id.button2)
        et.text = "Hola perro"
        et.visibility = View.VISIBLE
        torna.visibility = View.VISIBLE
    }

    fun guardaPdf() {
        val documents = Document(PageSize.A6)
        try {
            val file: File? = crearFichero("Erte")
            val path = file?.absolutePath
            val ficheroPdf = FileOutputStream("$path.pdf")
            val writer = PdfWriter.getInstance(documents, ficheroPdf)
            documents.open()
            documents.add(Paragraph((findViewById<TextView>(R.id.editView).text.toString())))
            documents.close()
            writer.close()
            Toast.makeText(this, "Erte.pdf\nis saved to \n$path", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()

        }
    }


    fun crearFichero(nombre: String): File? {
        val ruta: File? = getRuta()
        var fichero: File? = null
        if (ruta != null) {
            fichero = File(ruta, nombre)
        }
        return fichero
    }


    fun getRuta(): File? {
        var ruta: File?
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            ruta = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "ErtePdfs")
            if (!ruta.mkdirs()) {
                if (!ruta.exists()) {
                    return null;
                }
            }
            return ruta;
        }
        return null
    }

    fun enviarErte(view: View) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                requestPermissions(permissions, STORAGE_CODE)
            } else {
                guardaPdf()

            }
        }

    }


    fun enviaCorreu(view: View) {
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.data = Uri.parse(erte.email)
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, "InfoVid-19")
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Paper de reclamació de Erte")
        emailIntent.putExtra(Intent.EXTRA_TEXT, findViewById<TextView>(R.id.editView).text)
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        finish();
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            STORAGE_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    guardaPdf()
                } else {
                    Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}