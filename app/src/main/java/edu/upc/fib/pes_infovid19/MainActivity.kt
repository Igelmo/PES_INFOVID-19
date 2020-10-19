package edu.upc.fib.pes_infovid19


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.ui.main.CentreHospitalariActivity
import edu.upc.fib.pes_infovid19.ui.main.HealthMenuActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        buttonConsultarInfo.setOnClickListener {
            val intent = Intent(this, HealthMenuActivity::class.java)
            startActivity(intent)
        }

        buttonCentreHospitalari.setOnClickListener {
            val intent = Intent(this, CentreHospitalariActivity::class.java)
            startActivity(intent)
        }

        buttonObrirTestVulnerable.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    VulnerableTestActivity::class.java
                )
            )
        }

        fab.setOnClickListener {
        }
    }
}