package edu.upc.fib.pes_infovid19.ui.main.activity.user

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.domain.structures.User
import edu.upc.fib.pes_infovid19.ui.main.activity.MainActivity
import kotlinx.android.synthetic.main.activity_signin.*

private var user = User()

class SigninActivity : AppCompatActivity() {

    var userName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        setSupportActionBar(toolbarsignin)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupsignin()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupsignin() {

        signinbutton.setOnClickListener {
            if (emailEditTextSignin.text.isNotEmpty() && contrasenyaEditTextSignin.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditTextSignin.text.toString(), contrasenyaEditTextSignin.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        guardaEnLaBaseDeDatos()
                    } else {
                        showAlert()
                    }
                }
            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun filluser(): User {
        var et = findViewById<EditText>(R.id.emailEditTextSignin)
        var email = et.text.toString()
        et = findViewById<EditText>(R.id.usernameEditTextSignin)
        val username = et.text.toString()
        et = findViewById<EditText>(R.id.nameEditTextSignin)
        val name = et.text.toString()
        var type = ""
        if (voluntariradioButton.isChecked) {
            type = "Voluntari"
        } else if (vulnerableradioButton.isChecked) {
            type = "Vulnerable"
        } else if (bothradioButton.isChecked) {
            type = "Voluntari/Vulnerable"
        }
        et = findViewById<EditText>(R.id.contrasenyaEditTextSignin)
        val password = et.text.toString()
        val user = User()
        userName = username
        user.addInfo(email, username, name, type, password)
        return user
    }

    fun guardaEnLaBaseDeDatos() {
        user = filluser()
        val database = FirebaseDatabase.getInstance().getReference()
        val id = FirebaseAuth.getInstance().currentUser?.uid
        if (id != null) {
            database.child("User").child(id).setValue(user).addOnCompleteListener {
                if (it.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("nombre", userName)
                    startActivity(intent)
                } else {
                    showAlert()
                }
            }
        }
    }
}