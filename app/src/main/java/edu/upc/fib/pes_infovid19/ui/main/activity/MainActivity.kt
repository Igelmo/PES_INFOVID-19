package edu.upc.fib.pes_infovid19.ui.main.activity


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.ui.main.activity.economic.ErteActivity
import edu.upc.fib.pes_infovid19.ui.main.activity.health.HealthMenuActivity
import edu.upc.fib.pes_infovid19.ui.main.activity.health.hospitalcenter.HospitalCenterActivity
import edu.upc.fib.pes_infovid19.ui.main.activity.health.tests.InfectionProbabilityTestActivity
import edu.upc.fib.pes_infovid19.ui.main.activity.health.tests.VulnerableTestActivity
import edu.upc.fib.pes_infovid19.ui.main.activity.social.ChatListActivity
import edu.upc.fib.pes_infovid19.ui.main.activity.user.AdminMainActivity
import edu.upc.fib.pes_infovid19.ui.main.activity.user.UserProfileActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    var email: String? = ""
    var admin: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            // Name, email address, and profile photo Url
            email = user.email
            val mDatabase = FirebaseDatabase.getInstance().reference.child("User").orderByChild("email").equalTo(email)
            mDatabase.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var name: String = ""
                    for (snapshot in dataSnapshot.children) {
                        name = snapshot.child("name").getValue(String::class.java)!!
                        admin = snapshot.child("type").getValue(String::class.java)!!

                        var et = findViewById<TextView>(R.id.titleMainMenu)
                        et.text = "Benvingut/da " + name
                    }
                    if (admin == "Admin") {
                        buttonNewAdmin.visibility = View.VISIBLE
                    } else {
                        buttonNewAdmin.visibility = View.INVISIBLE
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println("The read failed: " + databaseError.code)
                }
            })
        }

        val prefs = getSharedPreferences("edu.upc.fib.pes_infovid19.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
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
            startActivity(intent)
        }

        buttonNewAdmin.setOnClickListener {
            val intent = Intent(this, AdminMainActivity::class.java)
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