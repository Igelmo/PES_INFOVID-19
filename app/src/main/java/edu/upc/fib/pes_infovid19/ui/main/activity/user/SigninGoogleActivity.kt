package edu.upc.fib.pes_infovid19.ui.main.activity.user

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
import edu.upc.fib.pes_infovid19.domain.structures.User
import edu.upc.fib.pes_infovid19.ui.main.activity.MainActivity
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.android.synthetic.main.activity_signin_google.*

private var user = User()

class SigninGoogleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin_google)
        setSupportActionBar(toolbarsignin)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        filldata()
        setupsignin()
    }

    private fun filldata() {
        val Email = FirebaseAuth.getInstance().currentUser!!.email
        val mDatabase = FirebaseDatabase.getInstance().reference.child("User").orderByChild("email").equalTo(Email)
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var correo: String = ""
                for (snapshot in dataSnapshot.children) {
                    correo = snapshot.child("email").getValue(String::class.java)!!
                }
                if (correo == Email) {
                    goToMain()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("The read failed: " + databaseError.code)
            }
        })
        var et = findViewById<EditText>(R.id.emailEditTextSigninGoogle)
        et.text = FirebaseAuth.getInstance().currentUser!!.email!!.toEditable()
        et = findViewById<EditText>(R.id.nameEditTextSigninGoogle)
        et.text = FirebaseAuth.getInstance().currentUser!!.displayName!!.toEditable()
    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupsignin() {

        signinbuttonGoogle.setOnClickListener {
            guardaEnLaBaseDeDatosGoogle()
        }
    }

    fun filluserGoogle(): User {
        var et = findViewById<EditText>(R.id.emailEditTextSigninGoogle)
        var email = et.text.toString()
        et = findViewById<EditText>(R.id.usernameEditTextSigninGoogle)
        var username = et.text.toString()
        et = findViewById<EditText>(R.id.nameEditTextSigninGoogle)
        var name = et.text.toString()
        var type = ""
        if (voluntariradioButtonGoogle.isChecked) {
            type = "Voluntari"
        } else if (vulnerableradioButtonGoogle.isChecked) {
            type = "Vulnerable"
        } else if (bothradioButtonGoogle.isChecked) {
            type = "Voluntari/Vulnerable"
        }
        val user = User()
        user.addInfoGoogle(email, username, name, type)
        return user
    }

    fun guardaEnLaBaseDeDatosGoogle() {
        user = filluserGoogle()
        val database = FirebaseDatabase.getInstance().getReference()
        val id = FirebaseAuth.getInstance().currentUser?.uid
        if (id != null) {
            database.child("User").child(id).setValue(user).addOnCompleteListener {
                if (it.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
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
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
}