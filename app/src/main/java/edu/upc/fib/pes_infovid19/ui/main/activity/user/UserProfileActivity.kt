package edu.upc.fib.pes_infovid19.ui.main.activity.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfileActivity : AppCompatActivity() {
    var userName = ""
    var email = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        setSupportActionBar(toolbarUserProfile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setValues()

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
            saveProfileChanges()
        }
    }

    private fun saveProfileChanges() {
        var et = findViewById<EditText>(R.id.emailEditTextProfile)
        var email = et.text.toString()
        et = findViewById<EditText>(R.id.usernameEditTextProfile)
        var username = et.text.toString()
        et = findViewById<EditText>(R.id.nameEditTextProfile)
        var name = et.text.toString()
        var type = ""
        if (voluntariradioButtonProfile.isChecked) {
            type = "Voluntari"
        } else if (vulnerableradioButtonProfile.isChecked) {
            type = "Vulnerable"
        } else if (bothradioButtonProfile.isChecked) {
            type = "Voluntari/Vulnerable"
        }
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            // Name, email address, and profile photo Url
            user.updateEmail(email)
        }
        val database = FirebaseDatabase.getInstance().getReference()
        val id = FirebaseAuth.getInstance().currentUser?.uid
        if (id != null && !email.isNullOrEmpty() && !name.isNullOrEmpty() && !username.isNullOrEmpty()) {
            database.child("User").child(id).child("email").setValue(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    database.child("User").child(id).child("name").setValue(name).addOnCompleteListener {
                        if (it.isSuccessful) {
                            database.child("User").child(id).child("username").setValue(username).addOnCompleteListener {
                                if (it.isSuccessful) {
                                    database.child("User").child(id).child("type").setValue(type).addOnCompleteListener {
                                        if (it.isSuccessful) {
                                            onSupportNavigateUp()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            showAlert()
        }
    }


    private fun setValues() {
        var email: String? = ""
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            email = user.email
        }
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

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Algun dels camps està buit")
        builder.setPositiveButton("Acceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
}