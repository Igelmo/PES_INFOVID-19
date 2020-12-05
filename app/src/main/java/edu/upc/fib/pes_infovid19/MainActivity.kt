package edu.upc.fib.pes_infovid19


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.ui.main.ChatActivity
import edu.upc.fib.pes_infovid19.ui.main.HealthMenuActivity
import edu.upc.fib.pes_infovid19.ui.main.HospitalCenterActivity
import edu.upc.fib.pes_infovid19.ui.main.UserProfileActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        var emailSignin = intent.extras?.getString("emailSignin")
        var emailLogin = intent.extras?.getString("emailLogin")

        val prefs = getSharedPreferences("edu.upc.fib.pes_infovid19.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE).edit()
        if (emailLogin.isNullOrBlank()) {
            prefs.putString("email", emailSignin)
        } else {
            prefs.putString("email", emailLogin)
        }
        prefs.apply()

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
            if (emailLogin.isNullOrBlank())
                intent.putExtra("email", emailSignin)
            else
                intent.putExtra("email", emailLogin)
            startActivity(intent)
        }

        fab.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
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