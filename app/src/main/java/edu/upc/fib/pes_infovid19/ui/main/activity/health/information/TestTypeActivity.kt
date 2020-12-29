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
import edu.upc.fib.pes_infovid19.domain.viewmodel.TestTypeViewModel
import edu.upc.fib.pes_infovid19.ui.main.adapter.TestTypeAdapter
import kotlinx.android.synthetic.main.activity_test_type.*

class TestTypeActivity : AppCompatActivity() {
    var email: String? = ""
    var admin: String? = ""
    private val viewModel: TestTypeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_type)
        setSupportActionBar(toolbarTestType)
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
                        manageTestTypeButton.visibility = View.VISIBLE
                    } else {
                        manageTestTypeButton.visibility = View.INVISIBLE
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println("The read failed: " + databaseError.code)
                }
            })
        }
        manageTestTypeButton.setOnClickListener {
            val intent = Intent(this, ManageTestTypeActivity::class.java)
            startActivity(intent)
        }

        val adapter = TestTypeAdapter(false)
        recyclerViewTestType.adapter = adapter

        viewModel.testTypeLiveData.observe(this) { testTypeSnapshot ->
            adapter.updateTestType(testTypeSnapshot)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

