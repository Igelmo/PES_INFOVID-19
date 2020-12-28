package edu.upc.fib.pes_infovid19.ui.main.activity.health.information

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.domain.viewmodel.RiskPopulationViewModel
import edu.upc.fib.pes_infovid19.ui.main.adapter.RiskPopulationAdapter
import kotlinx.android.synthetic.main.activity_risk_population.*

class RiskPopulationActivity : AppCompatActivity() {
    var email: String? = ""
    var admin: String? = ""
    private val viewModel: RiskPopulationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_risk_population)
        setSupportActionBar(toolbarRiskPopulation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            // Name, email address, and profile photo Url
            email = user.email
            val mDatabase = FirebaseDatabase.getInstance().reference.child("User").orderByChild("email").equalTo(email)
            mDatabase.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        admin = snapshot.child("type").getValue(String::class.java)!!
                    }
                    if (admin == "Admin") {
                        manageRiskPopulationButton.visibility = View.VISIBLE
                    } else {
                        manageRiskPopulationButton.visibility = View.INVISIBLE
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println("The read failed: " + databaseError.code)
                }
            })
        }
        manageRiskPopulationButton.setOnClickListener {
            val intent = Intent(this, ManageRiskPopulationActivity::class.java)
            startActivity(intent)
        }


        val adapter = RiskPopulationAdapter(false)
        recyclerViewRiskPopulation.adapter = adapter

        viewModel.riskPopulationLiveData.observe(this) { riskPopulationSnapshot ->
            adapter.updateRiskPopulation(riskPopulationSnapshot)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

