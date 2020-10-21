package edu.upc.fib.pes_infovid19
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import kotlinx.android.synthetic.main.activity_erte.*
import kotlinx.android.synthetic.main.activity_health_menu.toolbar
import java.text.SimpleDateFormat
import java.util.*


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

    @SuppressLint("SetTextI18n")
    fun guarda(view: View) {
        val erte = rellenaErte()
        val calendar = Calendar.getInstance()
        val et = findViewById<TextView>(R.id.editView)
        var c = 0
        var mes = seisMeses(calendar.get(Calendar.MONTH))
        if (mes > 12) {
            mes %= 12
            ++c
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
                "Data d'inici: " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR) + "\n" +
                "Data final: " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + mes + "/" + (calendar.get(Calendar.YEAR) + c) + "\n" +
                "Base reguladora: " + erte.base_reguladora + "\n" + "\n" + "\n" + "\n" + "\n" +
                "-----------------------------------------------------------------Firma" + "\n"

        et.visibility = View.VISIBLE

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
        val mDoc = Document()
        val mFileName = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis())
        //getExternalStorage(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + mFileName + ".pdf"
        val mFilePath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + mFileName + ".pdf"
        try {
            // PdfWriter.getInstance(mDoc, FileOutputStream(mFilePath))
            mDoc.open()
            val mText = editView.text.toString()
            mDoc.addAuthor("Infovid-19")
            mDoc.add(Paragraph(mText))
            mDoc.close()
            Toast.makeText(this, "$mFileName.pdf\nis saved to\n$mFilePath", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
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