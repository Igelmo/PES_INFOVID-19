package edu.upc.fib.pes_infovid19.ui.main.activity.health.information

import android.content.Intent
import android.net.Uri
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
import edu.upc.fib.pes_infovid19.domain.viewmodel.MythsViewModel
import edu.upc.fib.pes_infovid19.ui.main.adapter.MythsAdapter
import kotlinx.android.synthetic.main.activity_myths.*

class MythsActivity : AppCompatActivity() {
    var email: String? = ""
    var admin: String? = ""
    private val viewModel: MythsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myths)
        setSupportActionBar(toolbarMyths)
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
                        manageMythsButton.visibility = View.VISIBLE
                    } else {
                        manageMythsButton.visibility = View.INVISIBLE
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println("The read failed: " + databaseError.code)
                }
            })
        }
        manageMythsButton.setOnClickListener {
            //val intent = Intent(this, ManageMythsActivity::class.java)
            //startActivity(intent)
            val tweet = Intent(Intent.ACTION_VIEW)
            val message = "Pruebas"
            tweet.data = Uri.parse("https://twitter.com/intent/tweet?text=! Warning new Covid mith ! + $message")
            startActivity(tweet)

        }

        val adapter = MythsAdapter(false)
        recyclerViewMyths.adapter = adapter

        viewModel.mythsLiveData.observe(this) { mythSnapshot ->
            adapter.updateMyths(mythSnapshot)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}




