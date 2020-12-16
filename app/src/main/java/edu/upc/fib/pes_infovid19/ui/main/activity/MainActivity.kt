package edu.upc.fib.pes_infovid19.ui.main.activity


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.ui.main.activity.economic.ErteActivity
import edu.upc.fib.pes_infovid19.ui.main.activity.health.HealthMenuActivity
import edu.upc.fib.pes_infovid19.ui.main.activity.health.hospitalcenter.HospitalCenterActivity
import edu.upc.fib.pes_infovid19.ui.main.activity.health.tests.InfectionProbabilityTestActivity
import edu.upc.fib.pes_infovid19.ui.main.activity.health.tests.VulnerableTestActivity
import edu.upc.fib.pes_infovid19.ui.main.activity.social.ChatListActivity
import edu.upc.fib.pes_infovid19.ui.main.activity.user.UserProfileActivity
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
            val intent = Intent(this, HospitalCenterActivity::class.java)
            startActivity(intent)
        }

        buttonTestContagi.setOnClickListener {
            val intent = Intent(this, InfectionProbabilityTestActivity::class.java)
            startActivity(intent)
        }

        buttonProfile.setOnClickListener {
            val intent = Intent(this, UserProfileActivity::class.java)
            startActivity(intent)
        }

        fab.setOnClickListener {
            val intent = Intent(this, ChatListActivity::class.java)
            startActivity(intent)
        }

        buttonERTE.setOnClickListener {
            val intent = Intent(this, ErteActivity::class.java)
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
    }
}