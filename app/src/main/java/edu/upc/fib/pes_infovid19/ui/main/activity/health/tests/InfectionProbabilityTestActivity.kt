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
import edu.upc.fib.pes_infovid19.domain.viewmodel.InfectionProbabilityTestViewModel
import edu.upc.fib.pes_infovid19.ui.main.adapter.InfectionProbabilityTestAdapter
import kotlinx.android.synthetic.main.activity_infection_probability_test.*

const val PERCENT_EXTRA = "PERCENT_EXTRA"

class InfectionProbabilityTestActivity : AppCompatActivity() {
    var email: String? = ""
    var admin: String? = ""
    private val viewModel: InfectionProbabilityTestViewModel by viewModels()
    private val adapter = InfectionProbabilityTestAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infection_probability_test)
        setSupportActionBar(toolbarInfectionTest)
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
                        buttonManageProbabilityQuestions.visibility = View.VISIBLE
                    } else {
                        buttonManageProbabilityQuestions.visibility = View.INVISIBLE
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println("The read failed: " + databaseError.code)
                }
            })
        }
        buttonCheckProbability.setOnClickListener {
            generateInfectionResult()
        }

        buttonManageProbabilityQuestions.setOnClickListener {
            val intent = Intent(this, ManageQuestionsProbabilityTestActivity::class.java)
            startActivity(intent)
        }

        recyclerViewInfectionTest.adapter = adapter

        viewModel.questionsProbabilityTestLiveData.observe(this) { questionsSnapshot ->
            adapter.updateQuestions(questionsSnapshot)
        }
    }

    private fun generateInfectionResult() {
        val questionsCheckedList = adapter.checkedQuestions
        val questionsNotCheckedList = adapter.notCheckedQuestions

        val percent = viewModel.calculateProbabilities(questionsCheckedList, questionsNotCheckedList)

        val intent = Intent(this, ResultInfectionProbabilityTestActivity::class.java)
        intent.putExtra("PERCENT_EXTRA", percent)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

