package edu.upc.fib.pes_infovid19.ui.main.activity.user

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_modify_permissions.*

class ModifyPermissionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_permissions)
        setSupportActionBar(toolbarModifyUser)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        hideButtons()
        buttonsearchUser.setOnClickListener {
            getUserByEmail()
        }
        saveProfileButtonModify.setOnClickListener {
            getUserPassword()
        }
    }

    private fun saveChanges(password: String) {
        var type = ""
        if (voluntariradioButtonModify.isChecked) {
            type = "Voluntari"
        } else if (vulnerableradioButtonModify.isChecked) {
            type = "Vulnerable"
        } else if (bothradioButtonModify.isChecked) {
            type = "Voluntari/Vulnerable"
        } else if (adminradioButtonModify.isChecked) {
            type = "Admin"
        }
        var et = findViewById<EditText>(R.id.emailEditTextModify)
        var email = et.text.toString()
        val database = FirebaseDatabase.getInstance().getReference()
        if (!email.isNullOrEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnSuccessListener { authResult ->
                val user = authResult.user
                if (user != null) {
                    database.child("User").child(user.uid).child("type").setValue(type).addOnCompleteListener {
                        if (it.isSuccessful) {
                            onSupportNavigateUp()
                        }
                    }
                }
            }
        } else {
            showAlert()
        }
    }

    private fun getUserPassword() {
        var et = findViewById<EditText>(R.id.emailEditTextModify)
        var email = et.text.toString()
        if (!email.isNullOrEmpty()) {
            val mDatabase = FirebaseDatabase.getInstance().reference.child("User").orderByChild("email").equalTo(email)
            mDatabase.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var password: String = ""
                    for (snapshot in dataSnapshot.children) {
                        password = snapshot.child("password").getValue(String::class.java)!!
                    }
                    saveChanges(password)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println("The read failed: " + databaseError.code)
                }
            })
        } else {
            showAlert()
        }
    }

    private fun getUserByEmail() {
        var et = findViewById<EditText>(R.id.emailEditTextModify)
        var email = et.text.toString()
        if (!email.isNullOrEmpty()) {
            val mDatabase = FirebaseDatabase.getInstance().reference.child("User").orderByChild("email").equalTo(email)
            mDatabase.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var type: String = ""
                    var Email: String = ""
                    for (snapshot in dataSnapshot.children) {
                        type = snapshot.child("type").getValue(String::class.java)!!
                        Email = snapshot.child("email").getValue(String::class.java)!!
                    }
                    if (email != Email) {
                        showAlert2()
                    } else {
                        showButtons()
                        if (type == "Voluntari") {
                            voluntariradioButtonModify.isChecked = true
                            vulnerableradioButtonModify.isChecked = false
                            bothradioButtonModify.isChecked = false
                            adminradioButtonModify.isChecked = false
                        } else if (type == "Vulnerable") {
                            voluntariradioButtonModify.isChecked = false
                            vulnerableradioButtonModify.isChecked = true
                            bothradioButtonModify.isChecked = false
                            adminradioButtonModify.isChecked = false
                        } else if (type == "Voluntari/Vulnerable") {
                            voluntariradioButtonModify.isChecked = false
                            vulnerableradioButtonModify.isChecked = false
                            bothradioButtonModify.isChecked = true
                            adminradioButtonModify.isChecked = false
                        } else if (type == "Admin") {
                            voluntariradioButtonModify.isChecked = false
                            vulnerableradioButtonModify.isChecked = false
                            bothradioButtonModify.isChecked = false
                            adminradioButtonModify.isChecked = true
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println("The read failed: " + databaseError.code)
                }
            })
        } else {
            showAlert()
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("El camp email està buit")
        builder.setPositiveButton("Acceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showAlert2() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("El email no existeix o és incorrecte")
        builder.setPositiveButton("Acceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun showButtons() {
        typeTextModify.visibility = View.VISIBLE
        radioGroupButtonModify.visibility = View.VISIBLE
        saveProfileButtonModify.visibility = View.VISIBLE
        buttonsearchUser.visibility = View.INVISIBLE
    }

    fun hideButtons() {
        buttonsearchUser.visibility = View.VISIBLE
        typeTextModify.visibility = View.INVISIBLE
        radioGroupButtonModify.visibility = View.INVISIBLE
        saveProfileButtonModify.visibility = View.INVISIBLE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}