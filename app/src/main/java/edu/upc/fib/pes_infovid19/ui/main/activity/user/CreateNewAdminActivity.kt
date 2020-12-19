package edu.upc.fib.pes_infovid19.ui.main.activity.user

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.domain.structures.User
import edu.upc.fib.pes_infovid19.ui.main.activity.MainActivity
import kotlinx.android.synthetic.main.activity_create_new_admin.*

private var newUser = User()

class CreateNewAdminActivity : AppCompatActivity() {
    val user = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_admin)
        setSupportActionBar(toolbarNewAdmin)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupAdminSignin()
    }

    private fun setupAdminSignin() {
        buttonCreateNewAdmin.setOnClickListener {
            if (emailEditTextAdmin.text.isNotEmpty() && contrasenyaEditTextAdmin.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditTextAdmin.text.toString(), contrasenyaEditTextAdmin.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        saveNewAdmin()
                    } else {
                        showAlert()
                    }
                }
            }
        }
    }

    private fun lastSession(user: FirebaseUser?) {
        if (user != null) {
            val mDatabase = FirebaseDatabase.getInstance().reference.child("User").orderByChild("email").equalTo(user.email)
            mDatabase.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var password: String = ""
                    for (snapshot in dataSnapshot.children) {
                        password = snapshot.child("password").getValue(String::class.java)!!
                    }
                    newfunction(password)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println("The read failed: " + databaseError.code)
                }
            })
        }
    }

    private fun newfunction(password: String) {
        val db = FirebaseAuth.getInstance()
        db.signInWithEmailAndPassword(user?.email.toString(), password).addOnCompleteListener {
            if (it.isSuccessful) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                showAlert()
            }
        }
    }


    fun fillAdmin(): User {
        var et = findViewById<EditText>(R.id.emailEditTextAdmin)
        var email = et.text.toString()
        et = findViewById<EditText>(R.id.usernameEditTextAdmin)
        val username = et.text.toString()
        et = findViewById<EditText>(R.id.nameEditTextAdmin)
        val name = et.text.toString()
        et = findViewById<EditText>(R.id.contrasenyaEditTextAdmin)
        val password = et.text.toString()
        val user = User()
        user.addInfo(email, username, name, "Admin", password)
        return user
    }

    fun saveNewAdmin() {
        newUser = fillAdmin()
        val database = FirebaseDatabase.getInstance().getReference()
        val id = FirebaseAuth.getInstance().currentUser?.uid
        if (id != null) {
            database.child("User").child(id).setValue(newUser).addOnCompleteListener {
                if (it.isSuccessful) {
                    lastSession(user)
                    val intent = Intent(this, AdminMainActivity::class.java)
                    startActivity(intent)
                } else {
                    showAlert()
                }
            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al administrador")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
