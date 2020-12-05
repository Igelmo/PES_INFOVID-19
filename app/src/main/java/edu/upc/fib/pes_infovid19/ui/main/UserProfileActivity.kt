package edu.upc.fib.pes_infovid19.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.upc.fib.pes_infovid19.LoginActivity
import edu.upc.fib.pes_infovid19.MainActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfileActivity : AppCompatActivity() {
    var userName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        setValues()
        toolbarUserProfile.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        changePasswordButton.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)
        }
        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val prefs = getSharedPreferences("edu.upc.fib.pes_infovid19.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        saveProfileButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setValues() {
        var email = intent.extras?.getString("email")
        val mDatabase = FirebaseDatabase.getInstance().reference.child("User").orderByChild("email").equalTo(email)
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var userN: String = ""
                var name: String = ""
                var type: String = ""
                for (snapshot in dataSnapshot.children) {
                    userN = snapshot.child("username").getValue(String::class.java)!!
                    name = snapshot.child("name").getValue(String::class.java)!!
                    type = snapshot.child("type").getValue(String::class.java)!!
                }
                var et = findViewById<EditText>(R.id.usernameEditTextProfile)
                et.text = userN.toEditable()

                et = findViewById<EditText>(R.id.emailEditTextProfile)
                et.text = email?.toEditable()

                et = findViewById<EditText>(R.id.nameEditTextProfile)
                et.text = name.toEditable()

                if (type == "Voluntari") {
                    voluntariradioButtonProfile.isChecked = true
                    vulnerableradioButtonProfile.isChecked = false
                    bothradioButtonProfile.isChecked = false
                } else if (type == "Vulnerable") {
                    voluntariradioButtonProfile.isChecked = false
                    vulnerableradioButtonProfile.isChecked = true
                    bothradioButtonProfile.isChecked = false
                } else {
                    voluntariradioButtonProfile.isChecked = false
                    vulnerableradioButtonProfile.isChecked = false
                    bothradioButtonProfile.isChecked = true
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("The read failed: " + databaseError.code)
            }
        })
    }

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
}