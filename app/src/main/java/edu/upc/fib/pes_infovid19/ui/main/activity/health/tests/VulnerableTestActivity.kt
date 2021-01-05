package edu.upc.fib.pes_infovid19.ui.main.activity.health.tests

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
import edu.upc.fib.pes_infovid19.domain.viewmodel.VulnerableTestViewModel
import edu.upc.fib.pes_infovid19.ui.main.adapter.VulnerableTestAdapter
import kotlinx.android.synthetic.main.activity_vulnerable_test.*

const val PERCENT_HEALTH_EXTRA = "PERCENT_HEALTH_EXTRA"
const val PERCENT_ECONOMIC_EXTRA = "PERCENT_ECONOMIC_EXTRA"
const val PERCENT_SOCIAL_EXTRA = "PERCENT_SOCIAL_EXTRA"


class VulnerableTestActivity : AppCompatActivity() {
    var email: String? = ""
    var admin: String? = ""
    private val viewModel: VulnerableTestViewModel by viewModels()
    private val adapter = VulnerableTestAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vulnerable_test)
        setSupportActionBar(toolbarVulnerabilityTest)
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
                        buttonManageVulnerabilityTest.visibility = View.VISIBLE
                    } else {
                        buttonManageVulnerabilityTest.visibility = View.INVISIBLE
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println("The read failed: " + databaseError.code)
                }
            })
        }
        buttonCheckVulnerability.setOnClickListener {
            generateVulnerabilityResults()
        }

        buttonManageVulnerabilityTest.setOnClickListener {
            val intent = Intent(this, ManageQuestionsVulnerabilityTestActivity::class.java)
            startActivity(intent)
        }

        recyclerViewVulnerabilityTest.adapter = adapter

        viewModel.questionsVulnerabilityTestLiveData.observe(this) { questionsSnapshot ->
            adapter.updateQuestions(questionsSnapshot)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun generateVulnerabilityResults() {
        val questionsCheckedList = adapter.checkedQuestions
        val questionsNotCheckedList = adapter.notCheckedQuestions

        val percentHealth = viewModel.calculateVulnerabilityByType("salut", questionsCheckedList, questionsNotCheckedList)
        val percentEconomic = viewModel.calculateVulnerabilityByType("economica", questionsCheckedList, questionsNotCheckedList)
        val percentSocial = viewModel.calculateVulnerabilityByType("social", questionsCheckedList, questionsNotCheckedList)

        val intent = Intent(this, ResultVulnerableTestActivity::class.java)
        intent.putExtra(PERCENT_HEALTH_EXTRA, percentHealth)
        intent.putExtra(PERCENT_ECONOMIC_EXTRA, percentEconomic)
        intent.putExtra(PERCENT_SOCIAL_EXTRA, percentSocial)
        startActivity(intent)
    }
}