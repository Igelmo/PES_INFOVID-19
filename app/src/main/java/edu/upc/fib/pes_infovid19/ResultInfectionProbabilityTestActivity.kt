package edu.upc.fib.pes_infovid19

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import edu.upc.fib.pes_infovid19.ui.main.HospitalCenterActivity
import kotlinx.android.synthetic.main.activity_result_infection_probability_test.*

class ResultInfectionProbabilityTestActivity : AppCompatActivity() {
    private val PHONE_CALL_REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_infection_probability_test)
        PrintInfectionResult()
        toolbarResultInfecion.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        buttonNearestCenter.setOnClickListener {
            val intent = Intent(this, HospitalCenterActivity::class.java)
            startActivity(intent)
        }
        buttonCall.setOnClickListener {
            makeCall()
        }
        buttonTestVulnerable.setOnClickListener {
            val intent = Intent(this, VulnerableTestActivity::class.java)
            startActivity(intent)
        }
    }

    private fun PrintInfectionResult() {
        val perc = intent.getDoubleExtra("PERCENT_EXTRA", 0.0)
        when (perc) {
            in 0.0..12.0 -> textResultInfection.text = getString(R.string.molt_baixa)
            in 12.0..27.5 -> textResultInfection.text = getString(R.string.baixa)
            in 27.5..35.0 -> textResultInfection.text = getString(R.string.normal)
            in 35.0..50.0 -> textResultInfection.text = getString(R.string.alta)
            in 50.0..100.0 -> textResultInfection.text = getString(R.string.molt_alta)
        }
    }

    fun makeCall() {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:061")
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), PHONE_CALL_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PHONE_CALL_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makeCall()
            } else {
                Toast.makeText(this, "Has de donar permís per fer la trucada", Toast.LENGTH_SHORT).show()
            }
        }
    }
}